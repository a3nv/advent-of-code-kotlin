package y2023

import readInput


val WEIGHTS = "23456789TJQKA".mapIndexed { index, it -> it to index + 2 }.toMap()

class Hand(private val hand: String, val bid: Long) {
    fun handStrength(): Int {
        val cardWeights = hand.map { WEIGHTS[it] }
        val combos = cardWeights.groupingBy { it }.eachCount()
        val sortedCombos = combos.entries.sortedWith(
            compareByDescending<Map.Entry<Int?, Int>> { it.value }.thenByDescending { it.key }
        )
        val maxCombo = sortedCombos.maxByOrNull { it.value }!!
        val maxComboValue = maxCombo.key!!
        val secondComboValue = sortedCombos.drop(1).maxByOrNull { it.value }?.key ?: 0

        val baseScore = when (maxCombo.value) {
            4 -> 70000
            3 -> if (sortedCombos.size == 2) 60000 else 50000
            2 -> if (sortedCombos.size == 3) 40000 else 30000
            else -> 10000
        }

        return baseScore + maxComboValue * 100 + secondComboValue
    }
}
fun main() {

    val comparator: Comparator<Hand> = compareBy { it.handStrength() }

    fun part1(input: List<String>): Long {
        val hands = input.map {
            val split = it.split(" ")
            Hand(split[0], split[1].toLong())
        }
        val sortedWith = hands.sortedWith(comparator)
        return sortedWith.mapIndexed { index, hand ->
            (index + 1) * hand.bid
        }.sum()
    }


    fun part2(input: List<String>): Long {

        return 0
    }


    // test if implementation meets criteria from the description, like:
    var testInput = readInput("y2023", "Day07_test_part1")
    println(part1(testInput))
    check(part1(testInput) == 6440L)
//    println(part2(testInput))
//    check(part2(testInput) == 251927063)

    val input = readInput("y2023", "Day07")
    println(part1(input))
    check(part1(input) == 251927063L)
//    println(part2(input))
//    check(part2(input) == 255632664)
}