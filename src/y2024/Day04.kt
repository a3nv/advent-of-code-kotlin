package y2024

import println
import readInput

fun main() {

    val directions = listOf(
        Pair(1, 0), // horizontal right
        Pair(-1, 0), // horizontal left
        Pair(0, 1), // vertical down
        Pair(0, -1), // vertical up
        Pair(1, 1), // Diagonal down-right
        Pair(-1, -1), // Diagonal up-left
        Pair(-1, 1), // Diagonal down-left
        Pair(1, -1) // Diagonal up-right
    )

    fun iterateGrid(grid: List<List<Char>>, operation: (x: Int, y: Int) -> Unit) {
        for (y in grid.indices) {
            for (x in grid[y].indices) {
                operation(x, y)
            }
        }
    }

    fun checkWord(grid: List<List<Char>>, x: Int, y: Int, word: String, dx: Int, dy: Int): Boolean {
        for (i in word.indices) {
            val newX = x + i * dx
            val newY = y + i * dy
            if (newX !in grid[0].indices || newY !in grid.indices || grid[newY][newX] != word[i]) {
                return false
            }
        }
        return true
    }

    fun countWord(grid: List<List<Char>>, word: String): Int {
        var count = 0
        fun checkDirections(x: Int, y: Int) {
            for ((dx, dy) in directions) {
                if (checkWord(grid, x, y, word, dx, dy)) {
                    count++
                }
            }
        }
        iterateGrid(grid, ::checkDirections)
        return count
    }

    fun isXMas(grid: List<List<Char>>, x: Int, y: Int): Boolean {
        if (y - 1 < 0 || y + 1 >= grid.size || x - 1 < 0 || x + 1 >= grid[0].size) {
            return false
        }
        val middle = grid[y][x] == 'A'
        val topLeft = (grid[y - 1][x - 1] == 'M' && grid[y + 1][x + 1] == 'S') || (grid[y - 1][x - 1] == 'S' && grid[y + 1][x + 1] == 'M')
        val topRight = (grid[y - 1][x + 1] == 'S' && grid[y + 1][x - 1] == 'M') || (grid[y - 1][x + 1] == 'M' && grid[y + 1][x - 1] == 'S')

        return middle && topLeft && topRight
    }

    fun countXMas(grid: List<List<Char>>): Int {
        var count = 0
        fun checkXmas(x: Int, y: Int) {
            if (isXMas(grid, x, y)) {
                count++
            }
        }
        iterateGrid(grid, ::checkXmas)
        return count
    }

    fun part1(input: List<String>): Int {
        return countWord(input.map { it.toList() }, "XMAS")
    }

    fun part2(input: List<String>): Int {
        return countXMas(input.map { it.toList() })
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    var testInput = readInput("y2024", "Day04_test_part1")
    println(part1(testInput))
    check(part1(testInput) == 18)
    testInput = readInput("y2024", "Day04_part1")
    part1(testInput).println()

//    // Read the input from the `src/Day01.txt` file.
    var input = readInput("y2024", "Day04_test_part1")
    println(part2(input))
    check(part2(input) == 9)

    input = readInput("y2024", "Day04_part1")
    part2(input).println()
}

