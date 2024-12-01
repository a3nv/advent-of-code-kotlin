package y2023

import readInput


fun main() {

    fun Pair<String, String>.pickDirection(dir: Char): String {
        return if ('L' == dir) this.first else this.second
    }

    fun part1(input: List<String>): Int {
        val pattern = input.first()
        val ruleSeq = generateSequence(0) { (it + 1) % pattern.length }.map { pattern[it] }
        val map = input.drop(2).filter { it.isNotBlank() }
            .associate {
                val key = it.substringBefore(" =")
                val (left, right) = it.substringAfter("= (").substringBefore(")").split(", ")
                key to Pair(left, right)
            }
        return ruleSeq.runningFold("AAA") { node, rule ->
            map[node]!!.pickDirection(rule)
        }.takeWhile { it != "ZZZ" }.count()
    }

    fun gcd(a: Long, b: Long): Long {
        if (b == 0L) return a
        return gcd(b, a % b)
    }

    fun part2(input: List<String>): Int {
        val pattern = input.first()
        val ruleSeq = generateSequence(0) { (it + 1) % pattern.length }.map { pattern[it] }
        val map = input.drop(2).filter { it.isNotBlank() }
            .associate {
                val key = it.substringBefore(" =")
                val (left, right) = it.substringAfter("= (").substringBefore(")").split(", ")
                key to Pair(left, right)
            }
        val startingNodes = map.filter { it.key.endsWith("A") }.keys.toSet()
        val map1 = startingNodes.map { startingNode ->
            ruleSeq.runningFold(startingNode) { currentNode, rule ->
                println("$startingNode -> $rule")
                map[currentNode]!!.pickDirection(rule)
            }.takeWhile { !it.endsWith("Z") }.count()
        }
        // this is the most important part
        val reduce = map1.map { it.toLong() }.reduce { a, b ->
            println("$a (a) * $b (b) / ${gcd(a,b)} (gcd)")
            a * b / gcd(a, b)
        }
        println(reduce)
        return 0
    }


    // test if implementation meets criteria from the description, like:
    var testInput = readInput("y2023", "Day08_test_part1")
//    println(part1(testInput))
//    check(part1(testInput) == 6440L)
    println(part2(testInput))
//    check(part2(testInput) == 251927063)

    val input = readInput("y2023", "Day08")
    println(part1(input))
//    check(part1(input) == 251927063L)
    println(part2(input))
//    check(part2(input) == 255632664)
}