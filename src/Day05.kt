
fun main() {


    val columnsTest = listOf("ZN", "MCD", "P")
    val columnsTask = listOf("FDBZTJRN","RSNJH","CRNJGZFQ","FVNGRTQ","LTQF","QCWZBRGN","FCLSNHM","DNQMTJ","PGS")


    fun foldIt(columns: List<String>): String {
        return columns.fold("") { acc: String, string: String -> acc + string.last() }
    }

    fun part1(input: List<String>, columns: MutableList<String>): String {
        val reg = Regex("""\d+""")
        val operations = input.map { reg.findAll(it).map { it.groupValues[0] }.joinToString(separator = ",") }
        operations.forEachIndexed() { index, it ->
            val task = it.split(",")
            val qty = task[0].toInt()
            val from = task[1].toInt() - 1
            val to = task[2].toInt() - 1
            val source = columns.get(from)
            val a = source.length - qty
            val reversed = source.substring(a, source.length).reversed()
            val target = columns.get(to)
            val updatedSource = source.removeRange(a, source.length)
            columns.set(from, updatedSource)
            var new = target + reversed
            columns.set(to, new)
        }
        return foldIt(columns)
    }

    fun part2(input: List<String>, columns: MutableList<String>): String {
        val reg = Regex("""\d+""")
        val operations = input.map { reg.findAll(it).map { it.groupValues[0] }.joinToString(separator = ",") }
        operations.forEachIndexed() { index, it ->
            val task = it.split(",")
            val qty = task[0].toInt()
            val from = task[1].toInt() - 1
            val to = task[2].toInt() - 1
            val source = columns.get(from)
            val a = source.length - qty
            val reversed = source.substring(a, source.length)
            val target = columns.get(to)
            val updatedSource = source.removeRange(a, source.length)
            columns.set(from, updatedSource)
            var new = target + reversed
            columns.set(to, new)
        }
        return foldIt(columns)
    }




    // test if implementation meets criteria from the description, like:
    var input = readInput("Day05_test")
    var res = part1(input, columnsTest.toMutableList())
    println(res)
    check(res == "CMZ")
    res = part2(input, columnsTest.toMutableList())
    println(res)
    check(res == "MCD")

     input = readInput("Day05")
    res = part1(input, columnsTask.toMutableList())
    println(res)
    check(res == "QNNTGTPFN")
    res = part2(input, columnsTask.toMutableList())
    println(res)
    check(res == "GGNPJBTTR")
}
