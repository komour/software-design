fun main() {
    print("Enter cache capacity: ")
    val capacity = readLine()?.toInt()!!
    if (capacity < 1) {
        println("Positive cache capacity required.")
        return
    }

    val cache = LRUCache<Int>(capacity)
    while (true) {
        val line = readLine()!!
        val command = line.split(" ")
        when (command.first()) {
            "put" -> cache.put(command[1].toInt(), command[2].toInt())
            "get" -> println(cache.get(command[1].toInt()))
            else -> println("wrong command")
        }
    }
}