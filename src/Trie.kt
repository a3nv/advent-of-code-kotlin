class TrieNode {
    val children = mutableMapOf<Char, TrieNode>()
    var isEnd: Boolean = false
}

class Trie {
    private val root = TrieNode()

    fun insert(word: String) {
        var current = root
        for (char in word) {
            current = current.children.computeIfAbsent(char) { TrieNode() }
        }
        current.isEnd = true
    }

    fun search(word: String): Boolean {
        var current = root
        for (char in word) {
            current = current.children[char] ?: return false
        }
        return current.isEnd
    }

    fun getRoot(): TrieNode {
        return root
    }
}