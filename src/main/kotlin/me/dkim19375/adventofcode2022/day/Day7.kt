package me.dkim19375.adventofcode2022.day

import me.dkim19375.adventofcode2022.AdventOfCodeDay

object Day7 : AdventOfCodeDay() {

    @JvmStatic
    fun main(args: Array<String>) = solve()

    override val day: Int = 7

    override fun solve() {
        val input = getInputString().lines()
        val files = FolderData()
        var directory = ""
        for (inputLine in input) {
            val split = inputLine.split(' ')
            if (split[0] == "$") {
                if (split[1] != "cd") {
                    continue
                }
                val cdTo = split[2]
                if (cdTo == "..") {
                    directory = directory.split("/").dropLast(1).joinToString("/")
                    continue
                }
                if (cdTo == "/") {
                    directory = ""
                    continue
                }
                directory += "/$cdTo"
                directory = directory.removePrefix("/")
                continue
            }
            val size = split[0].toIntOrNull()
            val name = split[1]
            var newFiles = files
            if (directory.isNotBlank()) {
                for (folder in directory.split("/")) {
                    val subFolder = newFiles.subFolders[folder]
                    if (subFolder != null) {
                        newFiles = subFolder
                        continue
                    }
                    val newFolder = FolderData()
                    newFiles.subFolders[folder] = newFolder
                    newFiles = newFolder
                }
            }
            if (size != null) {
                newFiles.files.add(FileData(name, size))
                continue
            }
            newFiles.subFolders[name] = FolderData()
        }

        fun addSum(folder: FolderData): Int =
            folder.subFolders.values.sumOf(::addSum) + (folder.getSize().takeIf { it <= 100000 } ?: 0)

        val sum = addSum(files)
        var smallest = Int.MAX_VALUE
        val sizeNeeded = 30000000 - (70000000 - files.getSize())

        fun calculateSmallest(folder: FolderData) {
            val size = folder.getSize()
            if (size >= sizeNeeded) {
                smallest = minOf(smallest, size)
            }
            folder.subFolders.values.forEach(::calculateSmallest)
        }
        calculateSmallest(files)

        println(
            """
            Part 1: $sum
            Part 2: $smallest
            """.trimIndent()
        )
    }
}

private data class FileData(
    val name: String,
    val size: Int,
)

private data class FolderData(
    val files: MutableSet<FileData> = mutableSetOf(),
    val subFolders: MutableMap<String, FolderData> = mutableMapOf(),
) {
    fun getSize(): Int = files.sumOf(FileData::size) + subFolders.values.sumOf(FolderData::getSize)
}