package net.ilkinulas.aoc2018

import java.io.File


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

fun day7Part1(): String {
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

fun main(args: Array<String>) {
    val result = day7Part2()
    println("RESULT $result")
}

class NodeTask(val value: String, var remaining: Int) {
    override fun toString(): String {
        return "$value $remaining"
    }
}

fun day7Part2(): Int {
    val tasksDone = mutableListOf<String>()
    val numberOfNodes = allNodes.size
    var numAvailableWorkers = 5
    var seconds = 0
    val allTasks = allNodes.map { NodeTask(it, 60 + it[0].toInt() - 64) }
    var tasksInProgress = mutableListOf<NodeTask>()

    while (tasksDone.size < numberOfNodes) {
        val nextBatchOfTasks = allTasks.filter {
            !tasksDone.contains(it.value)
        }.filter { node ->
            val isRoot = !dependencies.containsKey(node.value)
            val allDependenciesAreDone = dependencies[node.value]?.all { tasksDone.contains(it) }
            val notInProgress = !tasksInProgress.contains(node)
            (isRoot || allDependenciesAreDone!!) && notInProgress
        }

        if (nextBatchOfTasks.isNotEmpty()) {
            val wip = nextBatchOfTasks.take(numAvailableWorkers)
            tasksInProgress.addAll(wip)
            numAvailableWorkers -= wip.size
        }

        tasksInProgress.forEach { it.remaining-- }

        val doneTasks = tasksInProgress.filter { it.remaining == 0 }
        numAvailableWorkers += doneTasks.size
        tasksDone.addAll(doneTasks.map { it.value })
        tasksInProgress.removeAll(doneTasks)
        seconds++
    }
    return seconds
}