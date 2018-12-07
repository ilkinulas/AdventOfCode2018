package net.ilkinulas.aoc2018


data class Point(val x: Int, val y: Int) {
    override fun toString(): String {
        return "($x,$y)"
    }
}

fun manhattanDistance(p1: Point, p2: Point): Int {
    val distance = Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y)
//    println("d $p1 $p2 = $distance")
    return distance
}