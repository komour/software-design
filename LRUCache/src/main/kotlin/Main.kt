fun main() {
    print("Enter cache capacity: ")
    val capacity = readLine()?.toInt()!!
    if (capacity < 1) {
        println("Positive cache capacity required.")
        return
    }

    val cache = LRUCache<String, String>(capacity)
    val keySet = mutableSetOf<String>()
    while (true) {
        val line = readLine()!!
        val command = line.split(" ")
        when (command.first()) {
            "put" -> {
                cache.put(command[1], command[2])
                keySet.add(command[1])
            }
            "get" -> println(cache.get(command[1]))
            "stop" -> break
            "info" -> {
                keySet.forEach {
                    if (cache.get(it) != null) {
                        println("$it ${cache.get(it)}")
                    }
                }
            }
            else -> println("wrong command")
        }
    }
}