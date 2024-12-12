package y2024

import println
import readInput

fun main() {

    fun part1(input: List<String>): Int {
        return 0
    }


    fun part2(input: List<String>): Int {
        return 0
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    var testInput = readInput("y2024", "Day07_test_part1")
    val testInputRes = part1(testInput)
    println(testInputRes)
    check(testInputRes == 41)
    testInput = readInput("y2024", "Day07_part1")
    part1(testInput).println()

    // Read the input from the `src/Day01.txt` file.
//    var input = readInput("y2024", "Day07_test_part1")
//    res = part2(input)
//    println(res)
//    check(res == 6)
//    input = readInput("y2024", "Day07_part1")
//    part2(input).println()
}