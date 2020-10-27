import java.time.Duration
import java.time.Instant
import java.time.temporal.ChronoUnit

class TagStatistic(private val api: VKClient) {

    fun tagHistory(tag: String, hours: Int): IntArray {
        val time = Instant.now().minus(hours.toLong(), ChronoUnit.HOURS).epochSecond
        val posts = api.loadPosts("#$tag", time)
//        println("post count : " + posts.size)

        val history = (0 until hours).associateWith { 0 }.toMutableMap()
        return posts.groupingBy {
            Duration.between(Instant.ofEpochSecond(it.date), Instant.now()).toHours().toInt()
        }.eachCountTo(history).values.toIntArray()
    }
}