package y2023

import utils.readInput

fun main() {

    fun isSymbol(c: Char): Boolean {
        return !c.isLetterOrDigit()
    }

    fun getNeighbours(row: Int, col: Int, size: Int, length: Int): List<Pair<Int, Int>> {
        TODO("Not yet implemented")
    }

    fun isNumber(c: Char): Boolean {
        return c.isDigit()
    }

    fun collectCompleteNumber(input: List<String>, adjRow: Any, adjCol: Any, visited: java.util.HashSet<Pair<Int, Int>>): String {
        TODO("Not yet implemented")
    }

    fun part1(input: List<String>): Int {
        val visited = HashSet<Pair<Int, Int>>()
        val queue = ArrayDeque<Pair<Int, Int>>()
        var sum = 0

        for (i in input.indices) {
            for (j in input[0].indices) {
                if (isSymbol(input[i][j])) {
                    queue.addLast(i to j)
                }
            }
        }

        if (queue.isNotEmpty()) {
            val (row, col) = queue.removeFirst()
            for ((adjRow, adjCol) in getNeighbours(row, col, input.size, input[0].length)) {
                if (!visited.contains(adjRow to adjCol)) {
                    visited.add(adjRow to adjCol)
                    if (isNumber(input[adjRow][adjCol])) {
                        val completeNumber = collectCompleteNumber(input, adjRow, adjCol, visited)
                        sum += completeNumber.toInt()
                    }
                    if (!isSymbol(input[adjRow][adjCol])) {
                        queue.addLast(adjRow to adjCol)
                    }
                }
            }
        }
        return 0
    }

    fun part2(input: List<String>): Int {

        return 0
    }


    // test if implementation meets criteria from the description, like:
    var testInput = readInput("y2023", "Day03_test_part1")
    check(part1(testInput) == 4361)
    println(part1(testInput))
//    check(part2(testInput) == 2286)
//    println(part2(testInput))

//    val input = readInput("y2023", "Day03")
//    check(part1(input) == 2563)
//    println(part1(input))
//    check(part2(input) == 70768)
//    println(part2(input))
}
