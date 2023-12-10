package y2023

import utils.readInput
import java.math.BigInteger

data class Mapping(val source: BigInteger, val step: BigInteger, val destination: BigInteger)

val order = listOf(
    "seed-to-soil",
    "soil-to-fertilizer",
    "fertilizer-to-water",
    "water-to-light",
    "light-to-temperature",
    "temperature-to-humidity",
    "humidity-to-location"
)

fun main() {

    fun part1(input: List<String>): Int {
        val maps = mutableMapOf<String, MutableList<Mapping>>()
        val seeds = input.first().split("seeds: ")[1].split(" ").map { it.toBigInteger() }
        var currentCategory = ""
        input.drop(1).forEach {line ->
            when {
                line.endsWith("map:") -> currentCategory = line.split(" map:")[0]
                line.isNotBlank() -> {
                    val parts = line.split(" ").map { it.toBigInteger() }
                    maps.getOrPut(currentCategory) { mutableListOf() }.add(Mapping(parts[1], parts[2], parts[0]))
                }
            }
        }

        val res = mutableListOf<BigInteger>()
        seeds.forEach { seed ->
            var source = seed
            order.forEach { mappingName ->
                val mapping: List<Mapping> = maps[mappingName]!!
                val matchedMapping = mapping.firstOrNull {
                    currentMapping -> source >= currentMapping.source && source <= currentMapping.source + currentMapping.step
                }
                source = matchedMapping?.destination?.plus((source - matchedMapping.source)) ?: source
            }
            res.add(source)
        }
        return res.minOrNull()!!.toInt()
    }

    fun part2(input: List<String>): Int {

        return 0
    }


    // test if implementation meets criteria from the description, like:
    var testInput = readInput("y2023", "Day05_test_part1")
    println(part1(testInput))
    check(part1(testInput) == 35)
//    check(part2(testInput) == 2286)
//    println(part2(testInput))

    val input = readInput("y2023", "Day05")
//    check(part1(input) == 2563)
    println(part1(input))
//    check(part2(input) == 70768)
//    println(part2(input))
}
