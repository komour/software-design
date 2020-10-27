import java.io.File
import java.lang.IllegalStateException
import kotlin.test.assertEquals
import kotlin.test.Test
import kotlin.test.assertFailsWith

private const val sample = "src/test/resources/sampleResponse.json"

class PostParserTest {
    private val parser = PostParser()

    private val response: String = File(sample).inputStream().bufferedReader().use {
        it.readText()
    }

    @Test
    fun parseSample() {
        val posts = parser.parse(response)
        assertEquals(posts, listOf(
            Post(97213, 1603774017, "Я ничуть не жалею, что Мой Характер сложный. Он обеспечивает мне сильных людей рядом.\n#soul#philosophy#философия#души#просто"),
            Post(97211, 1603772217, "Если твоя девочка ревнует, плачет, психует значит любит. Береги ее, свою единственную.\n" +
                    "#soul#philosophy#философия#души#просто"),
            Post(1109, 1603772018, "ॐ ॐ ॐ ॐ ॐ ॐ ❤ ॐ ॐ ॐ ॐ\uFEFF ॐ\uFEFF ॐ\n" +
                    "СЛУШАЙТЕ СВОЁ ТЕЛО, ДА УСЛЫШИТЕ!\n" +
                    "\n" +
                    "Идите внутрь и слушайте свое тело, потому что ваше тело никогда не будет лгать вам. Ваш ум будет играть шутки, но то, что вы чувствуете в своем сердце, в своих кишках, - это правда. \n" +
                    "\uD83D\uDE4F\uD83C\uDFFB \n" +
                    "\n" +
                    "LAMA THANKA PAINTING SCHOOL.\n" +
                    "#буддизм #духовноеразвитие #духовность #философия #мудрость #благость #добро #карма #buddhism #spirituality"),
            Post(8217, 1603771980, "\uD83D\uDE0A\uD83D\uDE18\uD83D\uDC96 \n" +
                    "Доброе утро\n" +
                    "\uD83D\uDE0A\uD83D\uDE18\uD83D\uDC96 \n" +
                    " \n" +
                    "Доброго и красивого утра!)\n" +
                    " \n" +
                    "\uD83E\uDD17\uD83E\uDD17\uD83E\uDD17\n" +
                    "\n" +
                    "#добрый_мир#философия#мудрые_мысли#Россия#лучше_дома#октябрь#доброта#красота#вторник")
        ))
    }

    @Test
    fun parseError() {
        val posts = parser.parse("{error: \"JHy UHji\"}")
        assert(posts.isEmpty())

        assertFailsWith(IllegalStateException::class) { parser.parse("TRASH") }
        assertFailsWith(IllegalStateException::class) { parser.parse("") }
    }
}