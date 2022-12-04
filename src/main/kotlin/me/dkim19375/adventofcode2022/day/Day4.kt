package me.dkim19375.adventofcode2022.day

import me.dkim19375.adventofcode2022.AdventOfCodeDay

object Day4 : AdventOfCodeDay() {

    @JvmStatic
    fun main(args: Array<String>) = solve()

    override val day: Int = 4

    override fun solve() {
        val input = getInputString().lines()
            .map { it.split(',') }
            .map { inner -> inner.map { it.split('-') } }
            .map { inner -> inner.map { range -> range[0].toInt()..range[1].toInt() } }
            .map { inner -> inner[0] to inner[1] }
        val fullyContains = input.count { (a, b) ->
            (a.contains(b.first) && a.contains(b.last)) || (b.contains(a.first) && b.contains(a.last))
        }
        val overlap = input.count { (a, b) ->
            a.contains(b.first) || a.contains(b.last) || b.contains(a.first) || b.contains(a.last)
        }
        println(
            """
                Part 1: $fullyContains
                Part 2: $overlap
            """.trimIndent()
        )
    }
}