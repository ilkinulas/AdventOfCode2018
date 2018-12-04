package net.ilkinulas.aoc2018

import java.io.File

val lines = File("src/main/resources/day_2_input.txt")
    .bufferedReader()
    .readLines()

fun day2Part1(): Int {
    var twos = 0
    var threes = 0
    lines.forEach {
        val m = freqMap(it)
        if (m.filterValues { x -> x == 2 }.isNotEmpty()) {
            twos++
        }
        if (m.filterValues { x -> x == 3 }.isNotEmpty()) {
            threes++
        }
    }
    return twos * threes
}

fun day2Part2(): String {
    val correctIds = findCorrectBoxIds()
    val commonLetters = correctIds.first.zip(correctIds.second).filter { it.first == it.second }
    return commonLetters.map { it.first }.joinToString(separator = "")
}

private fun findCorrectBoxIds(): Pair<String, String> {
    lines.forEach { first ->
        lines.forEach { second ->
            if (distance(first, second) == 1) {
                return Pair(first, second)
            }
        }
    }
    return Pair("", "")
}

fun freqMap(line: String): Map<Char, Int> {
    val m = mutableMapOf<Char, Int>()
    line.toCharArray().forEach {
        val current = m.getOrDefault(it, 0)
        m[it] = current + 1
    }
    return m
}

fun distance(input1: String, input2: String): Int {
    var d = 0
    input1.toCharArray().zip(input2.toCharArray()) { x, y ->
        if (x != y) d++
    }
    return d
}