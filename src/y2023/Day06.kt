package y2023

import utils.readInput

fun main() {


    fun part1(input: List<String>): Long {
        val times = input.first().substringAfter("Time:").split(" ")
            .filter { it.isNotBlank() }.map { it.trim().toLong() }
        val distances = input.last().substringAfter("Distance:").split(" ")
            .filter { it.isNotBlank() }.map { it.trim().toLong() }
        val pairs = times.zip(distances)
        println(times)
        println(distances)
        println(pairs)
        var number = 1L
        pairs.forEach {
            var counter = 0L
            for (i in 1 .. it.first) {
                val speed = i
                val distance = speed * (it.first - i)
                println("with speed $i it will go for ${it.first - i} and will cover $distance")
                if (distance > it.second) {
                    counter++
                }
            }
            number *= counter
        }
        return number
    }


    fun part2(input: List<String>): Long {

        return 0
    }


    // test if implementation meets criteria from the description, like:
    var testInput = readInput("y2023", "Day06_test_part1")
        println(part1(testInput))
//    check(part1(testInput) == 35)
//    println(part2(testInput))
//    check(part2(testInput) == 2286)

    val input = readInput("y2023", "Day06")
        println(part1(input))
//    check(part1(input) == 2563)
//    check(part2(input) == 70768)
//    println(part2(input))
}
