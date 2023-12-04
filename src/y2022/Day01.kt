package y2022

import utils.readInput

fun main() {

    fun foldIt(input: List<String>): List<Int> {
        return input.fold(mutableListOf(0)) { acc, next ->
            if (next.isNotBlank())  acc[acc.lastIndex] = next.toInt() + acc.last() else acc.add(0) // this is the first argument - operation
            acc // this is the second argument - accumulator
        }
    }

    fun part1(input: List<String>): Int {
        return foldIt(input).max()
    }

    fun part2(input: List<String>): Int {
        return foldIt(input).sorted().takeLast(3).sum()
    }



    // test if implementation meets criteria from the description, like:
    val testInput = readInput("y2022","Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("y2022","Day01")
    check(part1(input) == 68787)
    check(part2(input) == 198041)
    println(part1(input))
    println(part2(input))
}
