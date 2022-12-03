package me.dkim19375.adventofcode2022.day

import me.dkim19375.adventofcode2022.AdventOfCodeDay

object Day3 : AdventOfCodeDay() {

    @JvmStatic
    fun main(args: Array<String>) = solve()

    override val day: Int = 3

    override fun solve() {
        val input = getInputString().lines()
        val priorities = ('a'..'z').associateWith { it - 'a' + 1 } +
                ('A'..'Z').associateWith { it - 'A' + 27 }
        val compartments = input.map { str ->
            str.substring(0 until (str.length / 2)) to str.substring((str.length / 2) until str.length)
        }
        val part1Priorities = compartments.map { (first, second) ->
            priorities[first.toCharArray().toSet().first { it in second.toCharArray() }] ?: 0
        }
        val groups = input.chunked(3)
        val part2Priorities = groups.map { elves ->
            priorities[elves.first().toCharArray().toSet().first { it in elves[1] && it in elves[2] }] ?: 0
        }
        println(
            """
                Part 1: ${part1Priorities.sum()}
                Part 2 ${part2Priorities.sum()}
            """.trimIndent()
        )
    }
}