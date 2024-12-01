package y2023

import readInput

fun main() {

    fun isSymbol(c: Char): Boolean {
        return !c.isLetterOrDigit() && '.' != c
    }

    fun getNeighbours(row: Int, col: Int, size: Int, length: Int): List<Pair<Int, Int>> {
        val neighbours = mutableListOf<Pair<Int, Int>>();
        val directions = listOf(
            -1 to -1, -1 to 0, -1 to 1,
             0 to -1,           0 to 1,
             1 to -1,  1 to 0,  1 to 1
        )
        for (dir in directions) {
            val newRow = row + dir.first
            val newCol = col + dir.second
            if (newRow in 0 until size && newCol >= 0 && newCol < length) {
                neighbours.add(newRow to newCol)
            }
        }
        return neighbours
    }

    fun isNumber(c: Char): Boolean {
        return c.isDigit()
    }

    fun collectCompleteNumber(
        grid: List<String>,
        startRow: Int,
        startCol: Int,
        visited: HashSet<Pair<Int, Int>>
    ): String {
        val numberQueue = ArrayDeque<Pair<Int, Int>>()
        numberQueue.addLast(startRow to startCol)
        val sb = StringBuilder()
        visited.add(startRow to startCol)
        val currentLine = grid[startRow]
        val (row, col) = numberQueue.removeFirst()
        sb.append(grid[row][col])
        var left = startCol
        var right = startCol
        while (left > 0 && currentLine[left - 1].isDigit()) {
            left--
            visited.add(startRow to left)
        }
        while (right < currentLine.length - 1 && currentLine[right + 1].isDigit()) {
            right++
            visited.add(startRow to right)
        }
        return currentLine.substring(left..right)
    }

    fun part1(input: List<String>): Int {
        val visited = HashSet<Pair<Int, Int>>()
        val queue = ArrayDeque<Pair<Int, Int>>()
        var sum = 0

        for (i in input.indices) {
            for (j in input[0].indices) {
                if (isSymbol(input[i][j])) {
                    queue.addLast(i to j)
//                    println("Found: ${input[i][j]}")
                }
            }
        }

        while (queue.isNotEmpty()) {
            val (row, col) = queue.removeFirst()
//            println("Searching around: ${input[row][col]}")
            for ((adjRow, adjCol) in getNeighbours(row, col, input.size, input[0].length)) {
//                println("Checking $adjRow to $adjCol found ${input[adjRow][adjCol]}")
                if (isNumber(input[adjRow][adjCol])) {
                    if (!visited.contains(adjRow to adjCol)) {
                        val completeNumber = collectCompleteNumber(input, adjRow, adjCol, visited)
                        sum += completeNumber.toInt()
                    }
                }
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        val visited = HashSet<Pair<Int, Int>>()
        val queue = ArrayDeque<Pair<Int, Int>>()
        var sum = 0

        for (i in input.indices) {
            for (j in input[0].indices) {
                if (input[i][j] == '*') {
                    queue.addLast(i to j)
//                    println("Found: ${input[i][j]}")
                }
            }
        }
        while (queue.isNotEmpty()) {
            val (row, col) = queue.removeFirst()
//            println("Searching around: ${input[row][col]}")
            var list = mutableListOf<Int>();
            for ((adjRow, adjCol) in getNeighbours(row, col, input.size, input[0].length)) {
//                println("Checking $adjRow to $adjCol found ${input[adjRow][adjCol]}")
                if (isNumber(input[adjRow][adjCol])) {
                    if (!visited.contains(adjRow to adjCol)) {
                        val completeNumber = collectCompleteNumber(input, adjRow, adjCol, visited)
                        list.add(completeNumber.toInt())
                    }
                }
            }
            if (list.size == 2) {
                sum += list.reduce { acc, i -> acc * i }
            }
        }
        return sum
    }


    // test if implementation meets criteria from the description, like:
    var testInput = readInput("y2023", "Day03_test_part1")
    println("Test input: ${part1(testInput)}")
    check(part1(testInput) == 4361)
    println("Test input, part 2: ${part2(testInput)}")
//    check(part2(testInput) == 2286)

    val input = readInput("y2023", "Day03")
    println("User input: ${part1(input)}")
    check(part1(input) == 532331)
    println("User input, part 2: ${part2(input)}")
    check(part2(input) == 82301120)
}
