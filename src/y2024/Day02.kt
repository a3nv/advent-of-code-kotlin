package y2024

import println
import readInput
import kotlin.math.abs

fun main() {

    fun isStrictlyValid(list: List<Int>):Boolean {
        if (list.size < 2) return true
        val asc = list[1] > list[0]
        for (i in 1 until list.size) {
            val diff = list[i] - list[i - 1]
            if (abs(diff) < 1 || abs(diff) > 3) return false
            if (asc && diff <= 0) return false
            if (!asc && diff >= 0) return false
        }
        return true
    }
    fun isValid(it: String): Boolean {
        val list = it.split(" ").filter { it.isNotBlank() }.map { it.toInt() }
        if (list.size < 2) return true
        if (isStrictlyValid(list)) return true
        for (i in list.indices) {
            val modified = list.toMutableList().apply { removeAt(i) }
            if (isStrictlyValid(modified)) return true
        }
        return false
    }

    fun part1(input: List<String>): Int {
        val count = input.count {
            val list = it.split(" ").filter { it.isNotBlank() }.map { it.toInt() }
            isStrictlyValid(list)
        }
        return count
    }

    fun part2(input: List<String>): Int {
        return input.count(::isValid)
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    var testInput = readInput("y2024", "Day02_test_part1")
    check(part1(testInput) == 2)

    testInput = readInput("y2024", "Day02_part1")
    part1(testInput).println()

//    // Read the input from the `src/Day01.txt` file.
    var input = readInput("y2024", "Day02_test_part1")
    check(part2(input) == 4)

    input = readInput("y2024", "Day02_part1")
    part2(input).println()
}