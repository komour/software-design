import actors.MasterActor
import actors.SearchActor
import akka.actor.ActorSystem
import akka.actor.Props
import com.xebialabs.restito.builder.stub.StubHttp.whenHttp
import com.xebialabs.restito.semantics.Action.*
import com.xebialabs.restito.semantics.Condition.alwaysTrue
import com.xebialabs.restito.server.StubServer
import org.junit.Assert.*
import org.junit.Test
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import java.io.File

private const val PORT = 322

class SearchActorTest {

    private val response = File("test_YaHoo.html").bufferedReader().readText()

    @Test
    fun okTest() {
        var answerHashMap: HashMap<String, List<String>>? = null
        withStubServer { server ->
            whenHttp(server)
                .match(alwaysTrue())
                .then(stringContent(response))

            val system = ActorSystem.create("ActorSystem")
            val actors = listOf(
                system.actorOf(
                    Props.create(SearchActor::class.java, "http://localhost:$PORT/", "ol li h3 a"),
                    "test"
                )
            )
            val master =
                system.actorOf(
                    Props.create(
                        MasterActor::class.java,
                        actors,
                        { answerMap: HashMap<String, List<String>> ->
                            answerHashMap = answerMap
                        }), "master"
                )
            master.tell("test", null)
            Await.result(system.whenTerminated(), Duration.Inf())
        }
        assertNotNull(answerHashMap)
        assertEquals(1, answerHashMap!!.size)
        assertEquals(
            "How Old Am I? Exact Age Calculator https://www.calculators.org/health/age.php",
            answerHashMap!!["test"]!![1]
        )
    }

    @Test
    fun timeoutTest() {
        var answerHashMap: HashMap<String, List<String>>? = null
        withStubServer { server ->
            whenHttp(server)
                .match(alwaysTrue())
                .then(delay(5000), stringContent(response))

            val system = ActorSystem.create("ActorSystem")
            val actors = listOf(
                system.actorOf(
                    Props.create(SearchActor::class.java, "http://localhost:$PORT/", "ol li h3 a"),
                    "test"
                )
            )
            val master = system.actorOf(Props.create(
                MasterActor::class.java,
                actors,
                { answerMap: HashMap<String, List<String>> ->
                    answerHashMap = answerMap
                }
            ), "master")
            master.tell("test", null)
            Await.result(system.whenTerminated(), Duration.Inf())
        }
        assertNotNull(answerHashMap)
        assertEquals(1, answerHashMap!!.size)
        assertEquals(
            "TIMEOUT!\n",
            answerHashMap!!["deadLetters"]!![0]
        )
    }

    private fun withStubServer(callback: (StubServer) -> Unit) {
        var stubServer: StubServer? = null
        try {
            stubServer = StubServer(PORT).run()
            callback(stubServer)
        } finally {
            stubServer?.stop()
        }
    }
}