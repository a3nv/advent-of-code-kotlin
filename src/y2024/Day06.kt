package y2024

import println
import readInput
import kotlin.math.abs

enum class Direction(val dy: Int, val dx: Int) {
    UP(-1, 0), RIGHT(0, 1), DOWN(1, 0), LEFT(0, -1);

    fun turnRight(): Direction = when (this) {
        UP -> RIGHT
        RIGHT -> DOWN
        DOWN -> LEFT
        LEFT -> UP
    }
}

fun main() {
    val DIRECTIONS = setOf('^', 'v', '<', '>')
    fun findGuard(grid: List<MutableList<Char>>): Triple<Int, Int, Direction> {
        val directions = mapOf('^' to Direction.UP, 'v' to Direction.DOWN, '<' to Direction.LEFT, '>' to Direction.RIGHT)
        grid.forEachIndexed { y, row ->
            row.forEachIndexed { x, cell ->
                directions[cell]?.let { return Triple(y, x, it) }
            }
        }
        error("Guard not found!")
    }

    fun solve(grid: List<MutableList<Char>>): Int {
        val (startY, startX, startDirection) = findGuard(grid)
        var y = startY
        var x = startX
        var direction = startDirection
        grid[y][x] = '.';
        val visited = mutableSetOf<Pair<Int, Int>>()
        while (true) {
            visited.add(y to x)
            val nextY = y + direction.dy
            val nextX = x + direction.dx

            if (nextY !in grid.indices || nextX !in grid[nextY].indices) break

            if (grid[nextY][nextX] == '.') {
                y = nextY
                x = nextX
            } else {
                direction = direction.turnRight()
            }
        }
        return visited.size
    }

    fun solveCycle(grid: List<MutableList<Char>>, triple: Triple<Int, Int, Direction>): Boolean {
        val (startY, startX, startDirection) = triple
        var y = startY
        var x = startX
        var direction = startDirection
        grid[y][x] = '.'
        val visited = mutableSetOf<Triple<Int, Int, Direction>>()
        var steps = 0;
        while (true) {
            steps++
            if (visited.contains(Triple(y, x, direction))) {
                return true
            }
            visited.add(Triple(y, x, direction))
            val nextY = y + direction.dy
            val nextX = x + direction.dx

            if (nextY !in grid.indices || nextX !in grid[nextY].indices) return false

            if (grid[nextY][nextX] == '.') {
                y = nextY
                x = nextX
            } else {
                direction = direction.turnRight()
            }
        }
//        return false
    }

    fun part1(input: List<String>): Int {
        return solve(input.map { it.toMutableList() });
    }


    fun part2(input: List<String>): Int {
        val grid = input.map { it.toMutableList() }
        val triple = findGuard(grid)
        var res = 0;
        grid.forEachIndexed { y, row ->
            row.forEachIndexed { x, cell ->
                if (cell == '.' &&  y to x != triple.first to triple.second) {
                    grid[y][x] = '#'
                    if (solveCycle(grid, triple)) {
                        println("Found cycle at $y, $x")
                        res++;
                    }
                    grid[y][x] = '.'
                }
            }
        }

        return res
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    var testInput = readInput("y2024", "Day06_test_part1")
    var res = part1(testInput)
    println(res)
    check(res == 41)
    testInput = readInput("y2024", "Day06_part1")
    part1(testInput).println()

//    // Read the input from the `src/Day01.txt` file.
    var input = readInput("y2024", "Day06_test_part1")
    res = part2(input)
    println(res)
    check(res == 6)
//
    input = readInput("y2024", "Day06_part1")
    part2(input).println()
}