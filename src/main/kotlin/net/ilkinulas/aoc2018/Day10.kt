package net.ilkinulas.aoc2018

import java.io.File


object Day10 {
    val REGEX = "position=<(.*?), (.*?)> velocity=<(.*?), (.*?)>".toRegex()

    data class PosVel(val pos: Point, val vel: Point)

    fun part1(): Int {
        var input = File("src/main/resources/day_10_input.txt")
            .bufferedReader()
            .readLines()
            .map { line ->
                val parts = REGEX.find(line)?.groupValues!!
                PosVel(
                    Point(parts[1].trim().toInt(), parts[2].trim().toInt()),
                    Point(parts[3].trim().toInt(), parts[4].trim().toInt())
                )
            }
        var minX = Integer.MAX_VALUE
        var minY = Integer.MAX_VALUE
        input.forEach {
            if (it.pos.x < minX) {
                minX = it.pos.x
            }
            if (it.pos.y < minY) {
                minY = it.pos.y
            }
        }

        for (i in 1..100000) {
            input = step(input)
            printPoints(input, i)
        }
        return 0
    }

    fun step(input: List<PosVel>): List<PosVel> = input.map {
        val newPos = Point(it.pos.x + it.vel.x, it.pos.y + it.vel.y)
        PosVel(newPos, it.vel)
    }

    fun printPoints(input: List<PosVel>, iteration: Int) {
        var minX = Integer.MAX_VALUE
        var minY = Integer.MAX_VALUE
        var maxX = Integer.MIN_VALUE
        var maxY = Integer.MIN_VALUE
        input.forEach {
            if (it.pos.x < minX) {
                minX = it.pos.x
            }
            if (it.pos.y < minY) {
                minY = it.pos.y
            }
            if (it.pos.x > maxX) {
                maxX = it.pos.x
            }
            if (it.pos.y > maxY) {
                maxY = it.pos.y
            }
        }
        val height = maxY - minY
        val width = maxX - minX
        if (height > 15 && width > 10) {
            return
        }
        val out = StringBuilder()

        for (x in minX..maxX) {
            for (y in minY..maxY) {
                if (input.filter { it.pos.x == x && it.pos.y == y }.any()) {
                    out.append('#')
                } else {
                    out.append(' ')
                }
            }
            out.append('\n')
        }
        println(out.toString())
        println(iteration)
        Thread.sleep(1000L)

    }

    fun part2(): Int {
        return 0
    }
}

fun main(args: Array<String>) {
    Day10.part1()
}