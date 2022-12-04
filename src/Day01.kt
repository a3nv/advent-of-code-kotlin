fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        var max = 0
        input.forEach {
            if (it.isBlank()) {
                max = Math.max(max, sum)
                sum = 0
            } else {
                sum += it.toInt()
            }
        }
        return max
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        var a = 0; var b = 0; var c = 0
        input.forEach {
            if (it.isBlank()) {
                when {
                    sum >= a -> {
                        c = b; b = a; a = sum
                    }
                    sum >= b -> {
                        c = b; b = sum
                    }
                    sum >= c -> c = sum
                }
                sum = 0
            } else {
                sum += it.toInt()
            }
        }
        if (sum != 0) {
            when {
                sum >= a -> {
                    c = b; b = a; a = sum
                }
                sum >= b -> {
                    c = b; b = sum
                }
                sum >= c -> c = sum
            }
        }
        return a + b + c
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    check(part1(input) == 68787)
    check(part2(input) == 198041)
    println(part1(input))
    println(part2(input))
}
