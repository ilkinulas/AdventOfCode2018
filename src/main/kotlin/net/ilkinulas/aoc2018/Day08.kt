package net.ilkinulas.aoc2018

import java.io.File

fun main(args: Array<String>) {
    day8Part2()
}

fun day8Part1() = sum(parseInputForDay8())

fun day8Part2() = value(parseInputForDay8())

private fun parseInputForDay8(): MutableList<Int> {
    val input = File("src/main/resources/day_8_input.txt")
        .bufferedReader()
        .readText().split(" ").map { it.toInt() }
    return input.toMutableList()
}

fun sum(input: MutableList<Int>): Int {
    val childCount = input.removeAt(0)
    val metadataLength = input.removeAt(0)

    var sum = 0

    // calculate sum for all children
    for (c in 0 until childCount) {
        sum += sum(input)
    }

    // calculate sum for self
    for (m in 0 until metadataLength) {
        sum += input.removeAt(0)
    }

    return sum
}

fun value(input: MutableList<Int>): Int {
    val childCount = input.removeAt(0)
    val metadataLength = input.removeAt(0)

    if (childCount == 0) {
        // if node has no child its value is sum of metadata
        var sum = 0
        for (m in 0 until metadataLength) {
            sum += input.removeAt(0)
        }
        return sum
    } else {
        // calculate value of each child
        val childrenValues = mutableListOf<Int>()
        for (c in 0 until childCount) {
            childrenValues.add(value(input))
        }

        val metas = mutableListOf<Int>()
        for (m in 0 until metadataLength) {
            metas.add(input.removeAt(0))
        }

        var sum = 0
        for (i in 0 until metadataLength) {
            sum += childrenValues.getOrElse(metas[i] - 1) { 0 }
        }
        return sum

    }
}

