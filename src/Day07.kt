fun main() {

    var currentFolder: Folder?
    var map: MutableMap<String, Folder?> = mutableMapOf()
    var subFolders: MutableList<String>

    fun parse(input: List<String>) {
        currentFolder = Folder(input.first().last().toString()) // when starts with cd replace folder with new folder
        map = mutableMapOf(input.first().last().toString() to currentFolder)
        subFolders = mutableListOf() // when cd into new folder replace with new list
        currentFolder?.subFolders = subFolders
        input.drop(1).forEach {
            when {
                isBack(it) -> {
                    currentFolder = currentFolder?.parent
                    currentFolder?.subFolders?.forEach {
                        val subFolder = map[it]
                        currentFolder?.size = subFolder?.size?.plus(currentFolder?.size!!)!!
                    }
                }
                isCd(it) -> {
                    val currentFolderName = it.last().toString()
                    subFolders = mutableListOf()
                    var folder = map[currentFolderName]
                    if (folder == null) {
                        folder = Folder(currentFolderName)
                        folder.parent = currentFolder
                        map[currentFolderName] = folder
                    }
                    currentFolder = folder
                    currentFolder?.subFolders = subFolders
                }
                isDir(it) -> {
                    val f = Folder(it.last().toString())
                    f.parent = currentFolder
                    map[it.last().toString()] = f
                    subFolders.add(f.name)
                }
                isFile(it) -> {
                    val size = it.split(" ")[0].toInt()
                    currentFolder?.size = size + currentFolder?.size!!
                }
                else -> {

                }
            }
        }
        val parent = map["/"]
        parent?.subFolders?.forEach {
            val subFolder = map[it]
            parent?.size = subFolder?.size?.plus(parent?.size!!)!!
        }
    }

    fun part1(input: List<String>): Int {
        parse(input)
        var sum = 0
        map.filter{it.key != "/" && it.value?.size!! <= 100000 }.forEach {
            sum += it.value?.size!!
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        parse(input)
        return map
            .filter { it.key != "/" }
            .map { it.value?.size!! }
            .sorted()
            .first { it > 70000000 - map["/"]?.size!! }
    }



    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    println(part1(testInput))
    check(part1(testInput) == 95437)
    println(part2(testInput))
    check(part2(testInput) == 24933642)

    val input = readInput("Day07")
    println(part1(input))
    check(part1(input) == 2031851)
    println(part2(input))
    check(part2(input) == 2568781)
}

fun isFile(line: String): Boolean {
    return line[0].isDigit()
}

fun isCd(line: String): Boolean {
    return line.contains("cd")
}

fun isBack(line: String): Boolean {
    return line.contains("cd ..")
}

fun isDir(line: String): Boolean {
    return line.startsWith("dir")
}

fun isLs(line: String): Boolean {
    return line.contains("ls")
}



class Folder(var name: String, var parent: Folder? = null, var subFolders: MutableList<String> = mutableListOf(), var size: Int = 0)