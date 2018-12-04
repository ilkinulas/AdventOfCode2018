package net.ilkinulas.aoc2018

import java.io.File

val freqs = File("src/main/resources/day_1_input.txt")
    .bufferedReader()
    .readLines()
    .map { Integer.valueOf(it) }

fun day1Part1() = freqs.sum()

fun day1Part2(): Int {
    val seq = generateSequence { freqs }.flatten()
    val seen = mutableSetOf<Int>()
    var sum = 0
    return seq.map {
        sum += it
        sum
    }.first { !seen.add(it) }
}