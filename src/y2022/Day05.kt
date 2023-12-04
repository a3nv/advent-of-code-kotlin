package y2022

import utils.readInput

fun main() {

    fun part1(columns: MutableList<MutableList<String>>, operations: List<String>): String {
        operations.forEachIndexed() { index, it ->
            val task = it.split(",")
            val qty = task[0].toInt()
            val from = task[1].toInt() - 1
            val to = task[2].toInt() - 1
            val source = columns[from]
            val tail = source.takeLast(qty).reversed()
            source.subList(source.size - qty, source.size).clear()
            val target = columns[to]
            target.addAll(tail)
        }
        return foldIt(columns)
    }

    fun part2(columns: MutableList<MutableList<String>>, operations: List<String>): String {
        operations.forEachIndexed() { index, it ->
            val task = it.split(",")
            val qty = task[0].toInt()
            val from = task[1].toInt() - 1
            val to = task[2].toInt() - 1
            val source = columns[from]
            val tail = source.takeLast(qty)
            source.subList(source.size - qty, source.size).clear()
            val target = columns[to]
            target.addAll(tail)
        }
        return foldIt(columns)
    }




    // test if implementation meets criteria from the description, like:
    var input = readInput("y2022", "Day05_test_full")
    var pairs = parseInput(input)
    var res = part1(pairs.first, pairs.second)
    println(res)
    check(res == "CMZ")
    pairs = parseInput(input)
    res = part2(pairs.first, pairs.second)
    println(res)
    check(res == "MCD")

    input = readInput("y2022", "Day05_full")
    pairs = parseInput(input)
    res = part1(pairs.first, pairs.second)
    println(res)
    check(res == "QNNTGTPFN")
    pairs = parseInput(input)
    res = part2(pairs.first, pairs.second)
    println(res)
    check(res == "GGNPJBTTR")
}

fun foldIt(columns: MutableList<MutableList<String>>): String {
    return columns.filter{ it.isNotEmpty() }.fold("") { acc: String, list: List<String> -> acc + list.last() }
}

fun parseInput(input: List<String>): Pair<MutableList<MutableList<String>>, List<String>> {
    val header: List<String> = input.takeWhile { it.isNotBlank() }.reversed()
    val last = Regex("""\d+""").findAll(header.get(0)).map { it.groupValues[0] }.joinToString().last()
    val columns: MutableList<MutableList<String>> = MutableList(last.toInt() - 1) { mutableListOf() }
    header.drop(1).forEach { line ->
        println(line.replace(" [", ",[").replace("] ", "],").replace(",    ", ",   ,"))
        line.replace(" [", ",[").replace("] ", "],").replace(",    ", ",   ,") // that will help us to distinguish spaces which separate columns and placeholders for empty places in columns
            .split(",")
            .forEachIndexed {index, item ->
                val column: MutableList<String> = columns.get(index)
                val str = item.get(1).toString() // it's either an empty line (3 spaces) "   " or "[A]", in second case get(1) will return actual value
                if (str.isNotBlank())  {
                     column.add(str)
                }
            }
    }
    val reg = Regex("""\d+""")
    val operations = input.drop(header.size + 1).map { reg.findAll(it).map { it.groupValues[0] }.joinToString(separator = ",") }
    return Pair(columns, operations)
}
