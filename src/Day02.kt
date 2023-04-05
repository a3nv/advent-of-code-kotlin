fun main() {

    fun getGame(choice: String): Choice {
        return when (choice) {
            "A", "X" -> Rock(choice)
            "B", "Y" -> Paper(choice)
            "C", "Z" -> Scissors(choice)
            else -> throw IllegalArgumentException()
        }
    }

    fun game(round: String): Int {
        val split = round.split(" ", limit = 2)
        val game = getGame(split[1])
        return game.play(getGame(split[0])) + game.points
    }

    fun part1(rounds: List<String>): Int {
        var sum = 0
        rounds.forEach {
            sum += game(it)
        }
        return sum
    }

    fun yield(round: String): Int {
        val split = round.split(" ", limit = 2)
        val game = getGame(split[0])
        val choose = game.choose(split[1])
        return choose.play(game) + choose.points
    }

    fun part2(rounds: List<String>): Int {
        var sum = 0
        rounds.forEach {
            sum += yield(it)
        }
        return sum
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    println(part1(testInput))
    check(part1(testInput) == 15)
    println(part2(testInput))
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    check(part1(input) == 15337)
    println(part2(input))
    check(part2(input) == 11696)
}

abstract class Choice(val choice: String, var label: String, var points: Int) {
    abstract fun play(opp: Choice): Int
    abstract fun choose(outcome: String): Choice

}

class Rock(choice: String, label: String = "A", points: Int = 1) : Choice(choice, label, points) {
    override fun play(opp: Choice): Int {
        return when (opp) {
            is Rock -> 3
            is Paper -> 0
            is Scissors -> 6
            else -> 0
        }
    }

    override fun choose(outcome: String): Choice {
        return when (outcome) {
            "X" -> Scissors(outcome) // lose to Rock
            "Y" -> Rock(outcome) // draw with Rock
            "Z" -> Paper(outcome) // win Rock
            else -> throw IllegalArgumentException()
        }
    }

    override fun toString(): String {
        return choice + label
    }
}

class Paper(choice: String, label: String = "B", points: Int = 2) : Choice(choice, label, points) {
    override fun play(opp: Choice): Int {
        return when (opp) {
            is Rock -> 6
            is Paper -> 3
            is Scissors -> 0
            else -> 0
        }
    }

    override fun choose(outcome: String): Choice {
        return when (outcome) {
            "X" -> Rock(outcome) // lose to Paper
            "Y" -> Paper(outcome) // draw with Paper
            "Z" -> Scissors(outcome) // win Paper
            else -> throw IllegalArgumentException()
        }
    }

    override fun toString(): String {
        return choice + label
    }
}

class Scissors(choice: String, label: String = "C", points: Int = 3) : Choice(choice, label, points) {
    override fun play(opp: Choice): Int {
        return when (opp) {
            is Rock -> 0
            is Paper -> 6
            is Scissors -> 3
            else -> 0
        }
    }

    override fun choose(outcome: String): Choice {
        return when (outcome) {
            "X" -> Paper(outcome) // lose to Scissors
            "Y" -> Scissors(outcome) // draw with Scissors
            "Z" -> Rock(outcome) // win Scissors
            else -> throw IllegalArgumentException()
        }
    }

    override fun toString(): String {
        return choice + label
    }
}