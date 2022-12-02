package me.dkim19375.adventofcode2022.day

import me.dkim19375.adventofcode2022.AdventOfCodeDay

object Day2 : AdventOfCodeDay() {

    @JvmStatic
    fun main(args: Array<String>) = solve()

    override val day: Int = 2

    private val rpsChoices = mapOf(
        "A" to 1,
        "B" to 2,
        "C" to 3,
        "X" to 1,
        "Y" to 2,
        "Z" to 3,
    )

    override fun solve() {
        val input = getInputString().lines().map {
            it.split(' ').let { list ->
                (rpsChoices[list[0]] ?: 0) to (rpsChoices[list[1]] ?: 0)
            }
        }
        val part1Score = input.sumOf { (opponent, player) ->
            calculateScore(opponent, player)
        }
        val part2Score = input.sumOf { (opponent, action) ->
            val player = when (action) {
                1 -> if (opponent == 1) 3 else opponent - 1
                2 -> opponent
                else -> if (opponent == 3) 1 else opponent + 1
            }
            calculateScore(opponent, player)
        }
        println(
            """
                Part 1: $part1Score
                Part 2: $part2Score
            """.trimIndent()
        )
    }

    private fun calculateScore(opponent: Int, player: Int): Int = when {
        opponent == player -> 3
        ((opponent + 1) == player) || ((opponent - 2) == player) -> 6
        else -> 0
    } + player
}