package y2022

import readInput

fun main() {

    check(findMarker("bvwbjplbgvbhsrlpgdmjqwftvncz", 4) == 5)
    check(findMarker("nppdvjthqldpwncqszvftbrmjlhg", 4) == 6)
    check(findMarker("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 4) == 10)
    check(findMarker("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 4) == 11)

    check(findMarker("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 14) == 19)
    check(findMarker("bvwbjplbgvbhsrlpgdmjqwftvncz", 14) == 23)
    check(findMarker("nppdvjthqldpwncqszvftbrmjlhg", 14) == 23)
    check(findMarker("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 14) == 29)
    check(findMarker("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 14) == 26)


    val input = readInput("y2022", "Day06")
    val findMarker = findMarker(input[0], 4)
    val message = findMarker(input[0], 14)
    println(findMarker)
    println(message)
    check(findMarker == 1760)
    check(message == 2974)
}

fun findMarker(string: String, windowSize: Int): Int {
    return string.windowed(windowSize, 1, false)
        .mapIndexed { index, window -> windowSize + index to (window.toSet().size == windowSize)}
        .toMap()
        .filter { it.value }
        .iterator()
        .next()
        .key
}