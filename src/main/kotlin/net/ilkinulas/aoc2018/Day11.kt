package net.ilkinulas.aoc2018


object Day11 {
    private const val gridSerialNumber = 2187

    fun part1(): String {
        val grid = prepareGrid(gridSerialNumber)
        return maxSum(grid, 3).first
    }

    private fun maxSum(grid: Array<IntArray>, size: Int): Pair<String, Int> {
        var maxSum = Integer.MIN_VALUE
        var maxX = 0
        var maxY = 0

        for (x in 0..(300 - size)) {
            for (y in 0..(300 - size)) {
                var sum = grid.sum(x, y, size)
                if (sum > maxSum) {
                    maxSum = sum
                    maxX = x
                    maxY = y
                }
            }
        }

        return Pair("$maxX,$maxY", maxSum)
    }

    private fun prepareGrid(gridSerialNumber: Int): Array<IntArray> {
        val grid = Array(300) { IntArray(300) }
        for (i in 0 until 300) {
            for (j in 0 until 300) {
                val rackId = i + 10
                val powerLevel = ((rackId * j) + gridSerialNumber) * rackId
                val sVal = powerLevel.toString()
                val hundredDigit = if (sVal.length > 2) {
                    Integer.parseInt(sVal[sVal.length - 3].toString())
                } else {
                    0
                }
                grid[i][j] = hundredDigit - 5
            }
        }
        return grid
    }

    fun part2(): Pair<String, Int> {
        val grid = prepareGrid(gridSerialNumber)
        var max = 0
        var maxXY = ""
        var size = 0
        for (i in 2 until 300) {
            val sum = maxSum(grid, i)
            if (sum.second > max) {
                max = sum.second
                maxXY = sum.first
                size = i
            }
        }
        return Pair(maxXY, size)
    }
}

private fun Array<IntArray>.sum(x: Int, y: Int, size: Int): Int {
    var sum = 0
    for (x in x until x + size) {
        for (y in y until y + size) {
            sum += this[x][y]
        }
    }
    return sum
}
