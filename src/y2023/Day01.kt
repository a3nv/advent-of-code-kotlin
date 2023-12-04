package y2023

import utils.readInput

fun main() {

    fun getD(line: String): Int {
        var f: String? = null
        var l: String? = null
        var left = 0;
        var right = line.length - 1
        for (i in 0 until line.length) {
            if (f != null && l != null) {
                break
            }
            if (f == null && line[left].isDigit()) {
                f = line[left].toString()
            } else {
                left++;
            }
            if (l == null && line[right].isDigit()) {
                l = line[right].toString()
            } else {
                right--;
            }
        }
        if (f != null && l != null) {
            return Integer.parseInt(f + l)
        }
        return 0
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { getD(it) }
    }



    // test if implementation meets criteria from the description, like:
    val testInput = readInput("y2023", "Day01_test")
    check(part1(testInput) == 142)
//    check(part2(testInput) == 281)
    println(part1(testInput))
//    println(part2(testInput))

    val input = readInput("y2023", "Day01")
//    check(part1(input) == 68787)
//    check(part2(input) == 198041)
    println(part1(input))
//    println(part2(input))
}
