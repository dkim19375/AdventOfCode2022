package me.dkim19375.adventofcode2022

import java.io.FileNotFoundException

abstract class AdventOfCodeDay {

    abstract fun solve()

    abstract val day: Int

    fun getInputString(): String = javaClass.getResource("day$day.txt")?.readText()
        ?: throw FileNotFoundException(
            "File day$day.txt not found! (${javaClass.packageName.replace('.', '/')}/day$day.txt)"
        )
}