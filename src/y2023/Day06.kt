package y2023

import readInput
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.sqrt

fun main() {


    /**
     * Equation speed * (time - speed) represents parabola. In this case upside-down U shaped because time - speed is a negative value.
     */
    fun part1(input: List<String>): Long {
        val times = input.first().substringAfter("Time:").split(" ")
            .filter { it.isNotBlank() }.map { it.trim().toLong() }
        val distances = input.last().substringAfter("Distance:").split(" ")
            .filter { it.isNotBlank() }.map { it.trim().toLong() }
        val pairs = times.zip(distances)
        var number = 1L
        pairs.forEach {
            val t = it.first.toDouble()
            val d = it.second
            val maxSpeed = t / 2 // since it's a parabola maxSpeed will be reached at the middle of time
            val maxDistance = (maxSpeed * (t - maxSpeed)).toLong() // max distance, which is y-coordinate.
            if (maxDistance > d) { // if it's greater than distance d then there must be lower and higher speeds that their corresponding distances are exactly d.
                val sqrtPart = sqrt((t * t / 4) - d) // this is the root of a standard quadratic equation 'speed * (time - speed) = d' for speed
                val lowSpeed = ceil(t / 2 - sqrtPart).toLong() // finding roots of the equation
                val highSpeed = floor(t / 2 + sqrtPart).toLong()
                val count = (highSpeed - lowSpeed + 1).coerceAtLeast(0L)
                number *= count
            } else {
                number = 0L
            }
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
