import java.io.File
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

private const val ACCESS_KEY = "2ba909f72ba909f72ba909f7a72bdd3cf722ba92ba909f774259b56ea457c2692f55a00"
private const val API_VERSION = "5.124"
private const val BASE_URL = "https://api.vk.com/method/newsfeed.search"

fun load(url: String): String {
    val connection = URL(url).openConnection() as HttpURLConnection

    connection.inputStream.bufferedReader().use {
        return it.readText()
    }
}

class VkApi {
    fun loadPosts(query: String, startTime: Long): List<Post> {
        val responseFile = File("sampleResponse.json")
        responseFile.writeText(load(buildUrl(query, startTime)))
        return parse(load(buildUrl(query, startTime)))
    }

    private fun buildUrl(query: String, startTime: Long): String {
        val q = URLEncoder.encode(query, "utf8")
        return "$BASE_URL?q=$q&start_time=$startTime&count=200&access_token=$ACCESS_KEY&v=$API_VERSION"
    }
}