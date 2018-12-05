package net.ilkinulas.aoc2018

import java.io.File


fun day5Part1(): Int {
    val input = File("src/main/resources/day_5_input.txt").bufferedReader().readText()
    return processAll(input).length
}

fun day5Part2(): Int {
    val input = File("src/main/resources/day_5_input.txt").bufferedReader().readText()
    var minLength = Integer.MAX_VALUE;
    for (i in 65..90) {
        val polymer = input.filter { it.toInt() != i && it.toInt() != i + 32 }
        val len = processAll(polymer).length
        if (len < minLength) {
            minLength = len
        }
    }
    return minLength
}

private fun processAll(input: String): String {
    var raw = input
    var processed = processSingle(input)
    while (raw != processed) {
        raw = processed
        processed = processSingle(raw)
    }
    return processed
}

fun processSingle(s: String): String {
    if (s.isEmpty()) {
        return ""
    }
    for (i in 0 until s.length - 1) {
        if (Math.abs(s[i] - s[i + 1]) == 32) {
            val head = s.substring(0..(i - 1))
            val tail = s.substring((i + 2)..(s.length - 1))
            return head + tail
        }
    }
    return s
}
