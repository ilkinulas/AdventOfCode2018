package net.ilkinulas.aoc2018

import java.io.File
import java.util.*

fun day6Part1(): Int {
    val coordinates = File("src/main/resources/day_6_input.txt")
        .bufferedReader()
        .readLines()
        .map {
            val parts = it.split(", ")
            Point(parts[0].toInt(), parts[1].toInt())
        }

    val maxX = (coordinates.maxBy { it.x }?.x ?: 0) + 1
    val maxY = (coordinates.maxBy { it.y }?.y ?: 0) + 1

    val grid = Array(maxX) {
        Array(maxY) {
            Point(-1, -1)
        }
    }

    for (y in 0 until maxY) {
        for (x in 0 until maxX) {
            val nearestPoints = Stack<Point>()
            var minDistance = Integer.MAX_VALUE
            coordinates.forEach {
                val distance = manhattanDistance(it, Point(x, y))
                if (distance < minDistance) {
                    minDistance = distance
                    nearestPoints.clear()
                    nearestPoints.push(it)
                } else if (distance == minDistance) {
                    nearestPoints.push(it)
                }
            }
            if (nearestPoints.size == 1) {
                grid[x][y] = nearestPoints.first()
            } else {
                grid[x][y] = Point(-1, -1) // this means null, no point at this coordinate
            }
        }
    }
    //printGrid(maxY, maxX, grid)
    val areaSizeMap = mutableMapOf<Point, Int>()
    coordinates.forEach {
        var count = 0
        for (y in 0 until maxY) {
            for (x in 0 until maxX) {
                if (it == grid[x][y]) {
                    count++
                }
            }
        }
        areaSizeMap[it] = count
    }
    //search edges to detect infinite areas
    for (x in 0 until maxX) {
        areaSizeMap.remove(grid[x][0])
    }
    for (x in 0 until maxX) {
        areaSizeMap.remove(grid[x][maxY - 1])
    }
    for (y in 0 until maxY) {
        areaSizeMap.remove(grid[0][y])
    }
    for (y in 0 until maxY) {
        areaSizeMap.remove(grid[maxX - 1][y])
    }

    return areaSizeMap.maxBy { it.value }?.value ?: 0
}

fun day6Part2(): Int {
    val coordinates = File("src/main/resources/day_6_input.txt")
        .bufferedReader()
        .readLines()
        .map {
            val parts = it.split(", ")
            Point(parts[0].toInt(), parts[1].toInt())
        }

    val maxX = (coordinates.maxBy { it.x }?.x ?: 0) + 1
    val maxY = (coordinates.maxBy { it.y }?.y ?: 0) + 1

    val grid = Array(maxX) {
        Array(maxY) {
            Point(-1, -1)
        }
    }
    val maxDistance = 10000
    for (y in 0 until maxY) {
        for (x in 0 until maxX) {
            var sumDistance = coordinates.sumBy { manhattanDistance(it, Point(x, y)) }
            if (sumDistance >= maxDistance) {
                grid[x][y] = Point(-1, -1)
            } else {
                grid[x][y] = Point(x, y)
            }
        }
    }
    var areaSize = 0
    for (y in 0 until maxY) {
        for (x in 0 until maxX) {
            if (grid[x][y] != Point(-1, -1)) {
                areaSize++
            }
        }
    }

    return areaSize
}

private fun printGrid(
    maxY: Int,
    maxX: Int,
    grid: Array<Array<Point>>
) {
    for (y in 0 until maxY) {
        for (x in 0 until maxX) {
            print("${grid[x][y]}\t")
        }
        println()
    }
}

