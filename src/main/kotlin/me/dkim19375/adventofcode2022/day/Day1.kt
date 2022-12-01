package me.dkim19375.adventofcode2022.day

import me.dkim19375.adventofcode2022.AdventOfCodeDay

object Day1 : AdventOfCodeDay() {

    @JvmStatic
    fun main(args: Array<String>) = solve()

    override val day: Int = 1

    override fun solve() {
        val input = getInputString().lines()
        var currentCalories = 0
        var firstCalories = 0
        var secondCalories = 0
        var thirdCalories = 0
        fun updateCalories() {
            if (currentCalories >= firstCalories) {
                thirdCalories = secondCalories
                secondCalories = firstCalories
                firstCalories = currentCalories
                return
            }
            if (currentCalories >= secondCalories) {
                thirdCalories = secondCalories
                secondCalories = currentCalories
                return
            }
            if (currentCalories > thirdCalories) {
                thirdCalories = currentCalories
            }
        }
        for (line in input) {
            if (line.isBlank()) {
                updateCalories()
                currentCalories = 0
                continue
            }
            currentCalories += line.toInt()
        }
        updateCalories()
        println("""
            Part 1: $firstCalories
            Part 2: ${firstCalories + secondCalories + thirdCalories}
        """.trimIndent())
    }
}