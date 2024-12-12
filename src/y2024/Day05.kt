package y2024

import println
import readInput

fun main() {

    fun parseRules(input: List<String>): List<Pair<Int, Int>> =
        input.takeWhile { it.isNotBlank() }
            .map {
                val (a, b) = it.split("|").map { it.toInt() }
                a to b
            }

    fun parseUpdates(input: List<String>): List<List<Int>> =
        input.dropWhile { it.isNotBlank() }
            .drop(1)
            .map {
                it.split(",").map { it.toInt() }
            }

    fun isValidOrder(update: List<Int>, rules: List<Pair<Int, Int>>): Boolean {
        val indexMap = update.withIndex().associate { it.value to it.index }

        for ((from, to) in rules) {
            if (from in indexMap && to in indexMap) {
                if (indexMap[from]!! > indexMap[to]!!) {
                    return false
                }
            }
        }
        return true
    }

    fun part1(input: List<String>): Int {
        val rules = parseRules(input)
        val updates = parseUpdates(input)
        val validMiddlePages = updates.mapNotNull { update ->
            if (isValidOrder(update, rules)) {
                update[update.size / 2]
            } else {
                null
            }
        }

        val result = validMiddlePages.sum()
        println("The sum of the middle page numbers is: $result")
        return result
    }

    fun correctOrder(update: List<Int>, rules: List<Pair<Int, Int>>): List<Int> {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        val inDegree = mutableMapOf<Int, Int>()
        update.forEach { page ->
            graph[page] = mutableListOf()
            inDegree[page] = 0
        }
        for ((from, to) in rules) {
            if (from in graph && to in graph) {
                graph[from]!!.add(to)
                inDegree[to] = inDegree[to]!! + 1
            }
        }
        val sortedList = mutableListOf<Int>()
        val queue = ArrayDeque<Int>()
        inDegree.filterValues { it == 0 }.keys.forEach { queue.add(it) }
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            sortedList.add(current)
            graph[current]?.forEach { neighbor ->
                inDegree[neighbor] = inDegree[neighbor]!! - 1
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor)
                }
            }
        }

        return sortedList
    }


    fun part2(input: List<String>): Int {
        val rules = parseRules(input)
        val updates = parseUpdates(input)
        val validMiddlePages = updates.mapNotNull { update ->
            if (isValidOrder(update, rules)) {
                update[update.size / 2]
            } else {
                null
            }
        }

        val result = validMiddlePages.sum()
        println("The sum of the middle page numbers is: $result")

        val invalidUpdates = updates.filterNot { isValidOrder(it, rules) }
        val correctedMiddlePages = invalidUpdates.mapNotNull { update ->
            val correctedUpdate = correctOrder(update, rules)
            correctedUpdate[correctedUpdate.size / 2]
        }

        val correctedResult = correctedMiddlePages.sum()
        println("The sum of the middle page numbers after correcting the updates is: $correctedResult")
        return correctedResult
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    var testInput = readInput("y2024", "Day05_test_part1")
    check(part1(testInput) == 143)
    testInput = readInput("y2024", "Day05_part1")
    part1(testInput).println()

//    // Read the input from the `src/Day01.txt` file.
    var input = readInput("y2024", "Day05_test_part1")
    check(part2(input) == 123)

    input = readInput("y2024", "Day05_part1")
    part2(input).println()
}