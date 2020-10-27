import java.io.File
import java.net.URLEncoder

private var ACCESS_KEY = File("ACCESS_KEY").inputStream().bufferedReader().use {
    it.readText()
}

private const val API_VERSION = "5.124"
private const val BASE_URL = "https://api.vk.com/method/newsfeed.search"

class VKClient {
    private val parser = PostParser()
    private val urlReader = URLReader()

    fun loadPosts(query: String, startTime: Long): List<Post> {
//        val responseFile = File("sampleResponse.json")
//        responseFile.writeText(readUrl(buildUrl(query, startTime)))
        return parser.parse(urlReader.readUrl(buildUrl(query, startTime)))
    }

    private fun buildUrl(query: String, startTime: Long): String {
        val q = URLEncoder.encode(query, "utf8")
        return "$BASE_URL?q=$q&start_time=$startTime&count=200&access_token=$ACCESS_KEY&v=$API_VERSION"
    }
}