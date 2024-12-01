package y2023

import readInput


fun main() {

    fun part1(input: List<String>): Int {
        val map: List<List<Int>> = input.map { line -> line.split(" ").map { it.toInt() } }
        val sumOf = map.sumOf { list ->
            val lists = mutableListOf<List<Int>>()
            lists.add(list)
            var cur = list
            while (!cur.all { it == 0 }) {
                cur = cur.zipWithNext { a, b -> b - a }
                lists.add(cur)
            }
            val fold: Int = lists.reversed().fold(0) { acc, cl -> acc + cl.last() }
            fold
        }

        return sumOf
    }


    fun part2(input: List<String>): Int {
        val map: List<List<Int>> = input.map { line -> line.split(" ").map { it.toInt() } }
        val sumOf = map.sumOf { list ->
            val lists = mutableListOf<List<Int>>()
            lists.add(list)
            var cur = list
            while (!cur.all { it == 0 }) {
                cur = cur.zipWithNext { a, b -> b - a }
                lists.add(cur)
            }
            val fold: Int = lists.reversed().fold(0) { acc, cl ->
                cl.first() - acc
            }
            fold
        }

        return sumOf
    }


    // test if implementation meets criteria from the description, like:
    var testInput = readInput("y2023", "Day09_test_part1")
    println(part1(testInput))
    check(part1(testInput) == 114)
    println(part2(testInput))
    check(part2(testInput) == 2)

    val input = readInput("y2023", "Day09")
    println(part1(input))
    check(part1(input) == 1938800261)
    println(part2(input))
    check(part2(input) == 1112)
}