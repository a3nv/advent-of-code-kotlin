package y2023

import utils.readInput
import java.io.DataInput

fun main() {

    val s = mapOf(
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9",
    )

    fun getD(line: String): Int {
//        println(line)
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

    fun digitize(line: String): String {
        var chunk = ""
        var r = ""
        for (i in line.indices) {
            if (line[i].isDigit()) {
                r += line[i]
                chunk = ""
            } else {
                chunk += line[i]
                s.forEach {
                    if (chunk.contains(it.key)) {
                        r += it.value
                        chunk = ""
                    }
                }
            }
        }
        print(r)
        return r
    }

    fun calc(int: String): Int {
        println(" => " + Integer.valueOf("" + int[0] + int[int.length - 1]))
        return Integer.valueOf("" + int[0] + int[int.length - 1]);
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { getD(digitize(it)) }
    }

    fun part2(input: List<String>): Int {
        var i = 0
        return input.sumOf {
            print("" + i++ + " : ")
            calc(digitize(it))
        }
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("y2023", "Day01_test")
//    check(part1(testInput) == 142)
    check(part2(testInput) == 281)
//    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("y2023", "Day01")
//    check(part1(input) == 68787)
//    check(part2(input) == 198041)
//    println(part2(input))
    println(part2(input))
}
