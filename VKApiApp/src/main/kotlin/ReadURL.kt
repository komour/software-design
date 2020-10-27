import java.net.HttpURLConnection
import java.net.URL

fun readUrl(url: String): String {
    val connection = URL(url).openConnection() as HttpURLConnection

    connection.inputStream.bufferedReader().use {
        return it.readText()
    }
}