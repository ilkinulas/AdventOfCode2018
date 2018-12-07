package net.ilkinulas.aoc2018

import java.io.File


fun day7Part1(): String {
    val edges = File("src/main/resources/day_7_input.txt")
        .bufferedReader()
        .readLines()
        .map {
            val parts = it.split(" ")
            Pair(parts[1], parts[7])
        }
    // allNodes = [A, B, C, D, E, F] for sample data
    val allNodes = edges.flatMap { listOf(it.first, it.second) }.sorted().distinct()

    //maps node to its dependencies
    val dependencies = edges.groupBy { it.second }.map { it.key to it.value.map { it.first } }.toMap()
    val result = mutableListOf<String>()
    val numberOfNodes = allNodes.size

    while (result.size < numberOfNodes) {
        val node = allNodes.filter {
            !result.contains(it)
        }.first { node ->
            val isRoot = !dependencies.containsKey(node)
            val allDependenciesAreDone = dependencies[node]?.all { result.contains(it) }
            isRoot || allDependenciesAreDone!!
        }
        result.add(node)
    }
    return result.joinToString("")
}

fun day7Part2(): Int {
    return 0
}