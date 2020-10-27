import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser

class PostParser {
    private val gson = Gson()

    fun parse(response: String): List<Post> {
        val json: JsonObject? = JsonParser.parseString(response)?.asJsonObject
        val items: JsonArray? = json?.getAsJsonObject("response")?.getAsJsonArray("items")

        return items?.mapNotNull { gson.fromJson(it, Post::class.java) } ?: run { return emptyList() }
    }
}