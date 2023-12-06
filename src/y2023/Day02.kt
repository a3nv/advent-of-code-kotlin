package y2023

import utils.readInput

fun main() {

    fun part1(input: List<String>): Int {
        val limits = mapOf(
            "red" to 12,
            "green" to 13,
            "blue" to 14
        )
        var sum = 0
        input.forEach { gameString ->
            val gameData = gameString.split("Game")
            gameData.filter { it.isNotBlank() }.forEach { game ->
                val gameNumber = game.substringBefore(":").trim().toInt()
                val gameSets = game.substringAfter(":").split(";")
                var valid = true
                gameSets.forEach { set ->
                    set.split(",").forEach { cubes: String ->
                        val cube: List<String> = cubes.split("\\s+".toRegex()).filter { it.isNotBlank() }
                        val color: String = cube.last()
                        val count: Int = cube.first().toInt()
                        if (count > limits[color]!!) {
                            valid = false
                        }
                    }
                }
                if (valid) {
                    sum += gameNumber
                }
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sumOfPowers = 0
        input.forEach { gameString ->
            val gameData = gameString.split("Game")
            gameData.filter { it.isNotBlank() }.forEach { game ->
                val gameSets = game.substringAfter(":").split(";")
                val max = mutableMapOf<String, Int>()
                gameSets.forEach { set ->
                    set.split(",").forEach { cubes: String ->
                        val cube: List<String> = cubes.split("\\s+".toRegex()).filter { it.isNotBlank() }
                        val color: String = cube.last()
                        val count: Int = cube.first().toInt()
                        if (!max.containsKey(color) || max[color]!! < count) {
                            max[color] = count
                        }
                    }
                }
                val reduce = max.values.reduce { acc, i -> acc * i }
                println("Reduced: $reduce")
                sumOfPowers += reduce
            }
        }
        println("Sum of powers: $sumOfPowers")
        return sumOfPowers
    }


    // test if implementation meets criteria from the description, like:
    var testInput = readInput("y2023", "Day02_test_part1")
    check(part1(testInput) == 8)
    println(part1(testInput))
    check(part2(testInput) == 2286)
    println(part2(testInput))

    val input = readInput("y2023", "Day02")
    check(part1(input) == 2563)
    println(part1(input))
    check(part2(input) == 70768)
    println(part2(input))
}
