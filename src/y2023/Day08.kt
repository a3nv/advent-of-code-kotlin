package y2023

import utils.readInput


fun main() {

    fun part1(input: List<String>): Int {
        val rules = input.first().split("").filter { it.isNotBlank() }
        println(rules)
        val map = input.drop(1).filter { it.isNotBlank() }
            .associate {
                val key = it.substringBefore(" =")
                val (left, right) = it.substringAfter("= (").substringBefore(")").split(", ")
                key to Pair(left, right)
            }
        var current = 0
        var point = "AAA"
        while (point != "ZZZ") {
            val xr = map.get(point)
            println(xr)
            var deceison = current
            if (current > rules.size - 1) {
                deceison = current % rules.size
            }
            val dir = rules.get(deceison)
            if (dir == "L") {
                point = xr!!.first
            } else {
                point = xr!!.second
            }
            println("$point ($current - $deceison) = $dir => $point")
            current++
        }
        return current
    }


    fun part2(input: List<String>): Long {

        return 0
    }


    // test if implementation meets criteria from the description, like:
    var testInput = readInput("y2023", "Day08_test_part1")
    println(part1(testInput))
//    check(part1(testInput) == 6440L)
//    println(part2(testInput))
//    check(part2(testInput) == 251927063)

    val input = readInput("y2023", "Day08")
    println(part1(input))
//    check(part1(input) == 251927063L)
//    println(part2(input))
//    check(part2(input) == 255632664)
}