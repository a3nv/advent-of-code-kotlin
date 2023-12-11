package y2023

import utils.readInput

fun main() {

    fun List<Long>.toPairs(): List<Pair<Long, Long>> {
        return this.chunked(size = 2) { it[0] to it[1] }
    }

    fun part1(input: List<String>): Long? {
        val seeds = input.first().split("seeds: ")[1].split(" ").map { it.toLong() }
        val mappings = mutableListOf<MutableList<Pair<LongRange, Long>>>()
        input.drop(1).forEach { line ->
            when {
                line.endsWith("map:") -> {
                    mappings.add(mutableListOf())
                }

                line.isNotBlank() -> {
                    val (dest, src, size) = line.split(" ").map { it.toLong() }
                    mappings.last().add(src..src + size to dest - src)
                }
            }
        }
        val minOf = seeds.minOf { seed ->
            mappings.fold(seed) { accumulator, mappings ->
                val match = mappings.firstOrNull { it.first.contains(accumulator) }
                if (match == null) accumulator else accumulator + match.second
            }
        }
        return minOf
    }


    fun part2(input: List<String>): Long {
        val seeds = input.first().split("seeds: ")[1].split(" ")
            .map { it.toLong() }
            .toPairs()
        val mappings = mutableListOf<MutableMap<LongRange, (Long) -> Long>>()
        input.drop(1).forEach { line ->
            when {
                line.endsWith("map:") -> mappings.add(mutableMapOf())
                line.isNotBlank() -> {
                    val (dest, src, size) = line.split(" ").map { it.toLong() }
                    val range = src until src + size
                    val f = { x: Long -> dest + (x - src) }
                    mappings.last()[range] = f
                }
            }
        }

        fun mapValue(value: Long, mappings: List<Map<LongRange, (Long) -> Long>>): Long {
            var result = value
            for (mapping in mappings) {
                val f = mapping.keys.firstOrNull { it.contains(result) }?.let { longRange -> mapping[longRange] }
                result = f?.invoke(result) ?: result
            }
            return result
        }

        val minOf = seeds.minOfOrNull { seed ->
            var minValue = Long.MAX_VALUE
            for (value in seed.first..seed.first + seed.second) {
                val mapped = mapValue(value, mappings)
                if (mapped < minValue) {
                    minValue = mapped
                }
            }
            minValue
        } ?: Long.MAX_VALUE
        return minOf
    }


    // test if implementation meets criteria from the description, like:
    var testInput = readInput("y2023", "Day05_test_part1")
//        println(part1(testInput))
//    check(part1(testInput) == 35)
    println(part2(testInput))
//    check(part2(testInput) == 2286)

    val input = readInput("y2023", "Day05")
//    check(part1(input) == 2563)
//        println(part1(input))
//    check(part2(input) == 70768)
    println(part2(input))
}
