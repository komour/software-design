package actors

import akka.actor.UntypedAbstractActor
import org.jsoup.Jsoup
import java.net.URLEncoder


open class SearchActor(
    private val search: String,
    private val cssQuery: String
) : UntypedAbstractActor() {

    override fun onReceive(query: Any?) {
        if (query is String) {
            val response = Jsoup.connect(search + URLEncoder.encode(query, "UTF-8"))
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .get()
//            if (search == "https://yandex.ru/search/?text=") {
//                println(response)
//            }
            val links = response.select(cssQuery)
            sender().tell(links.map { it.text() + " " + it.absUrl("href") }.take(5), self())
        }
    }
}

class YandexActor : SearchActor("https://yandex.ru/search/?text=", "li h2 a")

class DuckDuckGoActor : SearchActor("https://lite.duckduckgo.com/lite/?q=", "tr td a")

class YaHooActor : SearchActor("https://search.yahoo.com/search?p=", "ol li h3 a")
