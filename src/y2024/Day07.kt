package y2024

import println
import readInput

fun main() {

    fun solve(list: List<Long>, index: Int, res: Long, target: Long): Long {
        if (index == list.size) {
            return if (res == target) {
        //                println("Found target: $target with full list processed")
                target
            } else {
        //                println("End of list reached but target not found. Index: $index, Result: $res")
                0
            }
        }
        if (res > target) {
//            println("Exceeded target. Index: $index, Result: $res, Target: $target")
            return 0
        }
        val addResult = res + list[index]
//        println("Trying addition: $res + ${list[index]} = $addResult")
        if (solve(list, index + 1, addResult, target) == target) {
//            println("Addition successful: $res + ${list[index]} = $addResult")
            return target
        }
        val multiplyResult = res * list[index]
//        println("Trying multiplication: $res * ${list[index]} = $multiplyResult")
        if (solve(list, index + 1, multiplyResult, target) == target) {
//            println("Multiplication successful: $res * ${list[index]} = $multiplyResult")
            return target
        }
        val comb = ("" + res + list[index]).toLong()
//        println("Trying concatenation: $res || ${list[index]} = $comb")
        if (solve(list, index + 1, comb, target) == target) {
//            println("Concatenation successful: $res || ${list[index]} = $comb")
            return target
        }
//        println("No valid operation found at index: $index for result: $res")
        return 0
    }



    fun part1(input: List<String>): Long {
        val filter = input.map { line ->
            val target = line.substringBefore(":").trim().toLong()
            val list = line.substringAfter(":").split(" ").filter { it.isNotBlank() }.map { it.toLong() }
            val solve = solve(list, 0, 0, target)
            solve
        }.filter { it != 0L }
//        filter.forEach{ line -> println("Line: $line, Solve Result: $line") }
        return filter.sumOf { it }
    }


//    fun part2(input: List<String>): Long {
//        return input.sumOf { line ->
//            val target = line.substringBefore(":").trim().toLong()
//            val list = line.substringAfter(":").split(" ").filter { it.isNotBlank() }.map { it.toLong() }
//            solve(list, 0, 0, target)
//        }
//    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    var testInput = readInput("y2024", "Day07_test_part1")
//    val testInputRes = part1(testInput)
//    println(testInputRes)
//    check(testInputRes == 3749L)
    testInput = readInput("y2024", "Day07_part1")
    part1(testInput).println()

    // Read the input from the `src/Day01.txt` file.
//    var input = readInput("y2024", "Day07_test_part1")
//    val res = part2(input)
//    println(res)
//    check(res == 11387)
//    input = readInput("y2024", "Day07_part1")
//    part2(input).println()
}