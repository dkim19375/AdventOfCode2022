package me.dkim19375.adventofcode2022.day

import me.dkim19375.adventofcode2022.AdventOfCodeDay

object Day6 : AdventOfCodeDay() {

    @JvmStatic
    fun main(args: Array<String>) = solve()

    override val day: Int = 6

    override fun solve() = getInputString().let { input ->
        println(
            """
                Part 1: ${input.indices.first { i -> input.substring(i, i + 4).toSet().size == 4 } + 4}
                Part 2: ${input.indices.first { i -> input.substring(i, i + 14).toSet().size == 14 } + 14}
            """.trimIndent()
        )
    }
}