package net.ilkinulas.aoc2018

import java.util.*

class Marbles(
    val numPlayers: Int,
    val lastMarbleWorth: Int,
    val marbles: LinkedList<Int> = LinkedList(),
    val scores: LongArray = LongArray(numPlayers)
) {
    fun play(): Long {
        for (i in 0..lastMarbleWorth) {
            insert(i)
        }
        return scores.max() ?: 0
    }

    private fun insert(marble: Int) {
        when {
            marble == 0 -> {
                marbles.add(marble)
                return
            }
            marble % 23L == 0L -> {
                for (i in 0 until 6) {
                    marbles.addLast(marbles.removeFirst())
                }
                val playerIndex = marble % numPlayers
                scores[playerIndex] = scores[playerIndex] + marbles.removeFirst() + marble
            }
            else -> {
                marbles.addFirst(marbles.removeLast())
                marbles.addFirst(marbles.removeLast())
                marbles.addLast(marble)
            }
        }
    }
}

object Day09 {
    fun part1(): Long {
        val marbles = Marbles(numPlayers = 416, lastMarbleWorth = 71975)
        return marbles.play()
    }

    fun part2(): Long {
        val marbles = Marbles(numPlayers = 416, lastMarbleWorth = 7197500)
        return marbles.play()
    }
}