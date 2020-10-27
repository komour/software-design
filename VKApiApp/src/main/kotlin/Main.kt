fun main() {
    println(TagStatistic(VKClient()).tagHistory("философия", 4).contentToString())
}