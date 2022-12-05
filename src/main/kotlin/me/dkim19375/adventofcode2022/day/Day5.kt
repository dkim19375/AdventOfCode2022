package me.dkim19375.adventofcode2022.day

import me.dkim19375.adventofcode2022.AdventOfCodeDay
import java.util.LinkedList

object Day5 : AdventOfCodeDay() {

    @JvmStatic
    fun main(args: Array<String>) = solve()

    override val day: Int = 5

    override fun solve() {
        val input = getInputString().lines()
        val crateInputs = input.takeWhile { !it.trimStart().startsWith('1') }
        val moveInputs = input.drop(crateInputs.size + 2).map {
            val split = it.split(' ')
            val amount = split[1].toInt()
            val from = split[3].toInt() - 1
            val to = split[5].toInt() - 1
            amount to (from to to)
        }
        val crateAmount = input[crateInputs.size].last().digitToInt()
        val originalCrates = Array<LinkedList<Char>>(crateAmount) { LinkedList() }
        for (crateInput in crateInputs) {
            for (i in 0 until crateAmount) {
                originalCrates[i].add(crateInput.getOrElse(4 * i + 1) { ' ' })
            }
        }
        val part1Crates = originalCrates.clone().map(::LinkedList)
        val part2Crates = originalCrates.clone().map(::LinkedList)
        for ((amount, fromToPair) in moveInputs) {
            val (from, to) = fromToPair
            repeat(amount) {
                part1Crates[to].addFirst(part1Crates[from].set(part1Crates[from].indexOfFirst(Char::isLetter), ' '))
            }
            val toAdd = LinkedList<Char>()
            repeat(amount) {
                toAdd.addFirst(part2Crates[from].set(part2Crates[from].indexOfFirst(Char::isLetter), ' '))
            }
            toAdd.forEach(part2Crates[to]::addFirst)
        }
        println(
            """
                Part 1: ${part1Crates.joinToString("") { it.firstOrNull(Char::isLetter)?.toString() ?: "" }}
                Part 2: ${part2Crates.joinToString("") { it.firstOrNull(Char::isLetter)?.toString() ?: "" }}
            """.trimIndent()
        )
    }
}