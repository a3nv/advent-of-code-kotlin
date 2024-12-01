package y2022

import readInput
import java.util.function.Predicate

fun main() {

    fun parse(input: List<String>): Folder {
        val rootFolderName = input.first().split(" ")[2]
        var currentFolder = Folder(rootFolderName)
        val rootFolder = currentFolder
        input.drop(1).forEach {
            when {
                isBack(it) -> {
                    currentFolder = currentFolder.parent!!
                }

                isCd(it) -> {
                    val currentFolderName = it.split(" ")[2]
                    val folder = currentFolder.subFolders.first { sub -> sub.name == currentFolderName }
                    currentFolder = folder
                }

                isDir(it) -> {
                    val folderName = it.split(" ")[1]
                    val f = Folder(folderName)
                    f.parent = currentFolder
                    currentFolder.subFolders.add(f)
                }

                isFile(it) -> {
                    val size = it.split(" ")[0].toInt()
                    fun add(current: Folder?, size: Int) {
                        if (current != null) {
                            current.size += size
                            add(current.parent, size)
                        }
                    }
                    add(currentFolder, size)
                }
            }
        }
        return rootFolder
    }

    fun count(root: Folder, predicate: Predicate<Folder>): List<Int> {
        val q = mutableListOf<Folder>()
        val result = mutableListOf<Int>()
        q.add(root)
        while (q.isNotEmpty()) {
            val candidate = q.removeFirst()
            if (predicate.test(candidate)) {
                result.add(candidate.size)
            }
            q.addAll(candidate.subFolders)
        }
        return result
    }

    fun part1(input: List<String>): Int {
        val rootFolder = parse(input)
        return count(rootFolder) { it: Folder -> it.size <= 100000 }.sum()
    }

    fun part2(input: List<String>): Int {
        val rootFolder = parse(input)
        val required = 30000000 - (70000000 - rootFolder.size)
        return count(rootFolder) { it: Folder -> it.size >= required }.min()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("y2022", "Day07_test")
    println(part1(testInput))
    check(part1(testInput) == 95437)
    println(part2(testInput))
    check(part2(testInput) == 24933642)

    val input = readInput("y2022", "Day07")
    println(part1(input))
    check(part1(input) == 2031851)
    println(part2(input))
    check(part2(input) == 2568781)
}

fun isFile(line: String): Boolean {
    return line[0].isDigit()
}

fun isCd(line: String): Boolean {
    return line.startsWith("\$ cd")
}

fun isBack(line: String): Boolean {
    return line == "\$ cd .."
}

fun isDir(line: String): Boolean {
    return line.startsWith("dir")
}

class Folder(
    var name: String,
    var parent: Folder? = null,
    var subFolders: MutableList<Folder> = mutableListOf(),
    var size: Int = 0
)