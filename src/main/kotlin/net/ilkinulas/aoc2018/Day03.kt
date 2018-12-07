package net.ilkinulas.aoc2018

import java.io.File

val CLAIM_REGEX = "#([0-9]+) @ ([0-9]+),([0-9]+): ([0-9]+)x([0-9]+)".toRegex()

data class Claim(
    val id: Int,
    val left: Int,
    val top: Int,
    val width: Int,
    val height: Int
) {
    companion object {
        fun fromString(s: String): Claim {
            val match = CLAIM_REGEX.find(s)
            val id = match?.groupValues?.get(1)?.toInt() ?: 0
            val left = match?.groupValues?.get(2)?.toInt() ?: 0
            val top = match?.groupValues?.get(3)?.toInt() ?: 0
            val width = match?.groupValues?.get(4)?.toInt() ?: 0
            val height = match?.groupValues?.get(5)?.toInt() ?: 0
            return Claim(id, left, top, width, height)
        }

        val EMPTY = Claim(0, 0, 0, 0, 0)
    }

    fun contains(p: Point) =
        p.x >= left && p.x < left + width && p.y >= top && p.y < top + height
}

fun main(args: Array<String>) {
    println(day3Part1())
    println(day3Part2())
}

val claims = File("src/main/resources/day_3_input.txt")
    .bufferedReader()
    .readLines()
    .map { Claim.fromString(it) }

fun day3Part1(): Int {
    val intersections = mutableSetOf<Point>()
    claims.forEach { first ->
        claims.forEach { second ->
            if (first != second) {
                intersections.addAll(intersection(first, second))
            }
        }
    }
    return intersections.size
}

fun day3Part2(): Int {
    for (i in 0 until claims.size) {
        var intersected = false
        val c1 = claims[i]
        for (j in 0 until claims.size) {
            val c2 = claims[j]
            if (c1 != c2) {
                if (intersection(c1, c2).isNotEmpty()) {
                    intersected = true
                    break
                }
            }
        }
        if (!intersected) {
            return c1.id
        }
    }
    return Claim.EMPTY.id
}

fun intersection(c1: Claim, c2: Claim): List<Point> {
    val l = mutableListOf<Point>()
    for (i in c1.left until (c1.width + c1.left)) {
        for (j in c1.top until (c1.top + c1.height)) {
            val p = Point(i, j)
            if (c2.contains(p)) {
                l.add(p)
            }
        }
    }
    return l
}