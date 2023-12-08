package y2023

import utils.readInput

fun main() {

    /*
    Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
     */
    fun part1(input: List<String>): Int {
        var sum = 0
        input.forEach { card ->
            val cardData = card.split(":")
            cardData.filter { it.isNotBlank() && !it.startsWith("Card") }.forEach { data ->
                val winners = data.substringBefore("|").trim()
                    .split(" ").filter { it.isNotBlank() }.toSet()
                val playerHand = data.substringAfter("|").trim()
                    .split(" ").filter { it.isNotBlank() }.toSet()
                val count = winners.intersect(playerHand).count()
                println("Winners $winners --> player $playerHand = ${winners.intersect(playerHand)}")
                sum += 1 shl (count - 1)
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {

        return 0
    }


    // test if implementation meets criteria from the description, like:
    var testInput = readInput("y2023", "Day04_test_part1")
    println(part1(testInput))
    check(part1(testInput) == 13)
//    check(part2(testInput) == 2286)
//    println(part2(testInput))

    val input = readInput("y2023", "Day04")
//    check(part1(input) == 2563)
    println(part1(input))
//    check(part2(input) == 70768)
//    println(part2(input))
}
