import java.lang.Exception

class LRUCache<T>(val capacity: Int) {
    init {
        if (capacity < 1) {
            throw Exception("positive cache capacity required")
        }
    }
    private inner class Node(val key: Int, val value: T) {
        var next: Node? = null
        var prev: Node? = null
    }

    private var head: Node? = null
    private var tail: Node? = null

    private val hashMap: HashMap<Int, Node> = HashMap()

    private fun insertNode(node: Node) {
        node.prev = head
        node.next = null
        head?.next = node

        head = node
        if (tail == null) {
            tail = node
        }
    }

    private fun deleteNode(node: Node) {
        node.prev?.next = node.next
        node.next?.prev = node.prev

        if (node == head) {
            head = node.prev
        }
        if (node == tail) {
            tail = node.next
        }
    }

    fun put(key: Int, value: T) {
        assert(hashMap.size <= capacity)

        val initialSize = hashMap.size

        val newNode = Node(key, value)

        if (!hashMap.containsKey(key)) {
            insertNode(newNode)
            if (hashMap.size == capacity) {
                hashMap.remove(tail!!.key)
                deleteNode(tail!!)
            }
        } else {
            deleteNode(hashMap[key]!!)
            insertNode(newNode)
        }
        hashMap[key] = newNode

        assert(hashMap.size <= capacity)
        assert(head != null)
        assert(tail != null)
        assert(hashMap.size >= initialSize)
        assert(head!!.key == key)
    }

    fun get(key: Int) : T? {
        assert(hashMap.size <= capacity)

        val node = hashMap[key] ?: return null

        deleteNode(node)
        insertNode(node)

        assert(hashMap.size <= capacity)
        assert(head != null)
        assert(tail != null)
        assert(head!!.key == key)

        return node.value
    }
}