package me.dkim19375.adventofcode2022.day

import me.dkim19375.adventofcode2022.AdventOfCodeDay

object Day8 : AdventOfCodeDay() {

    @JvmStatic
    fun main(args: Array<String>) = solve()

    override val day: Int = 8

    override fun solve() {
        val input = getInputString().lines()
        val trees = input.map(String::toCharArray).map { it.map(Char::digitToInt).toIntArray() }.toTypedArray()
        var visible = 0
        var maxScenicScore = 0
        for ((rowI, row) in trees.withIndex()) {
            for ((columnI, column) in row.withIndex()) {
                fun isVisible(next: (count: Int) -> Int?): Boolean {
                    var count = 0
                    while (true) {
                        if ((next(++count) ?: return true) >= column) {
                            return false
                        }
                    }
                }

                fun getTreeCount(next: (count: Int) -> Int?): Int {
                    var count = 0
                    while (true) {
                        if ((next(++count) ?: return count - 1) >= column) {
                            return count
                        }
                    }
                }

                val rightVisible = isVisible { count -> row.getOrNull(columnI + count) }
                val leftVisible = isVisible { count -> row.getOrNull(columnI - count) }
                val upVisible = isVisible { count -> trees.getOrNull(rowI + count)?.getOrNull(columnI) }
                val downVisible = isVisible { count -> trees.getOrNull(rowI - count)?.getOrNull(columnI) }
                if (rightVisible || leftVisible || upVisible || downVisible) {
                    visible++
                }
                val rightCount = getTreeCount { count -> row.getOrNull(columnI + count) }
                val leftCount = getTreeCount { count -> row.getOrNull(columnI - count) }
                val upCount = getTreeCount { count -> trees.getOrNull(rowI + count)?.getOrNull(columnI) }
                val downCount = getTreeCount { count -> trees.getOrNull(rowI - count)?.getOrNull(columnI) }
                val scenicScore = rightCount * leftCount * upCount * downCount
                maxScenicScore = maxOf(maxScenicScore, scenicScore)
            }
        }
        println(
            """
                Part 1: $visible
                Part 2: $maxScenicScore
            """.trimIndent()
        )
    }
}