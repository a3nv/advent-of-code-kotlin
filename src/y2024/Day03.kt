package y2024

import println
import readInput
import kotlin.math.abs

fun main() {



    fun part1(input: List<String>): Int {
        val regex = Regex("""mul\((\d{1,3}),(\d{1,3})\)""")
        val matchers = regex.findAll(input.joinToString("\n"))
        return matchers.sumOf {
            val (a, b) = it.destructured
            a.toInt() * b.toInt()
        }
    }

    fun part2(input: List<String>): Int {
        val regex = Regex("""mul\((\d{1,3}),(\d{1,3})\)|don't\(\)|do\(\)""")
        val matchers = regex.findAll(input.joinToString("\n"))
        var sum = 0;
        var enabled = true;
        for (it in matchers) {
            when (it.value) {
                "don't()" -> enabled = false
                "do()" -> enabled = true
                else -> {
                    if (enabled && it.value.startsWith("mul")) {
                        val (a, b) = it.destructured
                        sum += a.toInt() * b.toInt()
                    }
                }
            }
        }
        return sum
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    var testInput = readInput("y2024", "Day03_test_part1")
    check(part1(testInput) == 161)
    testInput = readInput("y2024", "Day03_part1")
    part1(testInput).println()

//    // Read the input from the `src/Day01.txt` file.
    var input = readInput("y2024", "Day03_test_part2")
    check(part2(input) == 48)

    input = readInput("y2024", "Day03_part1")
    part2(input).println()
}