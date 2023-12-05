package y2023

import utils.readInput

fun main() {

    val days: List<Pair<String, Char>> = mapOf(
        "one" to '1',
        "two" to '2',
        "three" to '3',
        "four" to '4',
        "five" to '5',
        "six" to '6',
        "seven" to '7',
        "eight" to '8',
        "nine" to '9',
    ).toList()

    fun sumCalibrations(input: List<List<Char>>): Int {
        return input.sumOf { ("" + it[0] + it[it.size - 1]).toInt() }
    }

    fun part1(input: List<String>): Int {
        return sumCalibrations(input.map { line -> line.filter { char -> char.isDigit() }.toList() })
    }

    fun part2(input: List<String>): Int {
        val digitized: List<List<Char>> = input.map { line ->
            line.indices.mapNotNull { index ->
                if (line[index].isDigit()) {
                    line[index]
                } else {
                    days.filter { (dayName, _) -> line.substring(index).startsWith(dayName) }.map { it.second }.firstOrNull()
                }
            }
        }.toList()
        return sumCalibrations(digitized)
    }


    // test if implementation meets criteria from the description, like:
    var testInput = readInput("y2023", "Day01_test_part1")
    check(part1(testInput) == 142)
    println(part1(testInput))
    testInput = readInput("y2023", "Day01_test_part2")
    check(part2(testInput) == 281)
    println(part2(testInput))

    val input = readInput("y2023", "Day01")
    check(part1(input) == 53651)
    println(part1(input))
    check(part2(input) == 53894)
    println(part2(input))
}
