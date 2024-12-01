package y2024

import println
import readInput
import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val (list1, list2) = input.fold(Pair(mutableListOf<Int>(), mutableListOf<Int>())) { (firstList, secondList), item ->
            val (first, second) = item.split("   ").map { it.toInt() }
            firstList.add(first)
            secondList.add(second)
            Pair(firstList, secondList)
        }
        val sorted1 = list1.sorted()
        val sorted2 = list2.sorted()
        return sorted1.zip(sorted2).sumOf { (a, b) -> abs(a -b) }
    }

    fun part2(input: List<String>): Int {
        val (list, map) = input.fold(Pair(mutableListOf<Int>(), mutableMapOf<Int, Int>())) { (list, map), item ->
            val (first, second) = item.split("   ").map { it.toInt() }
            list.add(first)
            map[second] = map.getOrDefault(second, 0) + 1
            Pair(list, map)
        }
        return list.sumOf { it * map.getOrDefault(it, 0) }
    }

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    var testInput = readInput("y2024", "Day01_test_part1")
    check(part1(testInput) == 11)

    testInput = readInput("y2024", "Day01_part1")
    part1(testInput).println()
    // Read the input from the `src/Day01.txt` file.
    var input = readInput("y2024", "Day01_test_part1")
    check(part2(input) == 31)

    input = readInput("y2024", "Day01_part1")
    part2(input).println()
}