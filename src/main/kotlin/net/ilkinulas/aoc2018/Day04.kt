package net.ilkinulas.aoc2018

import java.io.File
import java.text.SimpleDateFormat
import java.util.*

val DATE_REGEX = "([0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2})".toRegex()
val GUARD_ID_REGEX = "#([0-9]+)".toRegex()
val DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd HH:mm")

data class Guard(val id: String, val sleeping: MutableMap<Int, MutableList<Range>>) {
    fun addRange(day: Int, range: Range) {
        val l = this.sleeping.getOrDefault(day, mutableListOf())
        l.add(range)
        this.sleeping[day] = l
    }

    fun sleepDuration() = sleeping.flatMap { it.value }.sumBy { it.end - it.start }

    fun isAsleep(day: Int, minute: Int): Boolean {
        val l = sleeping.getOrDefault(day, emptyList<Range>())
        for (range in l) {
            if (minute in range.start..range.end) {
                return true
            }
        }
        return false
    }

}

data class Range(val start: Int, val end: Int)

fun day4Part1(): Int {
    val guards = parseInput()
    val maxSleepingGuard = guards.map { it.value }.maxBy { it.sleepDuration() }!!
    val minutes = sleepFrequencies(maxSleepingGuard)
    return maxSleepingGuard.id.toInt() * minutes.maxBy { it.value }!!.key
}

fun day4Part2(): Int {
    val guards = parseInput()
    val mostFreqAsleep = guards.mapValues { entry ->
        val guard = entry.value
        val minutes = sleepFrequencies(guard)
        Pair(guard.id, minutes.maxBy { it.value })
    }.map { it.value }.maxBy { it.second?.value ?: 0 } ?: return 0
    return mostFreqAsleep.first.toInt() * mostFreqAsleep.second!!.key
}

private fun parseInput(): MutableMap<String, Guard> {
    val guards = mutableMapOf<String, Guard>()
    lateinit var guardId: String
    var sleepStart = 0
    var sleepEnd = 0
    File("src/main/resources/day_4_input.txt")
        .bufferedReader()
        .readLines()
        .sorted()
        .forEach { line ->
            //println(line)
            val date = extractDate(line)
            val cal = Calendar.getInstance()
            cal.timeInMillis = date.time
            when {
                line.contains("begins shift") -> {
                    guardId = GUARD_ID_REGEX.find(line)?.groupValues?.get(1)!!
                    val guard = guards[guardId]
                    if (guard == null) {
                        guards.put(guardId, Guard(guardId, mutableMapOf()))
                    }
                }
                line.contains("falls asleep") -> {
                    sleepStart = cal.get(Calendar.MINUTE)
                }
                line.contains("wakes up") -> {
                    sleepEnd = cal.get(Calendar.MINUTE) - 1
                    val day = cal.get(Calendar.DAY_OF_MONTH)
                    val newRange = Range(sleepStart, sleepEnd)
                    val currentGuard = guards[guardId]!!
                    currentGuard.addRange(day, newRange)
                    guards.put(currentGuard.id, currentGuard)
                }
            }
        }
    return guards
}

private fun sleepFrequencies(maxSleepingGuard: Guard): MutableMap<Int, Int> {
    val minutes = mutableMapOf<Int, Int>()
    for (day in 1..31) {
        for (minute in 0..59) {
            if (maxSleepingGuard.isAsleep(day, minute)) {
                var current = minutes.getOrDefault(minute, 0)
                current++
                minutes[minute] = current
            }
        }
    }
    return minutes
}

fun extractDate(line: String) = DATE_FORMAT.parse(DATE_REGEX.find(line)?.groupValues?.get(0))