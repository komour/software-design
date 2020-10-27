import com.xebialabs.restito.server.StubServer
import com.xebialabs.restito.builder.stub.StubHttp.whenHttp
import com.xebialabs.restito.semantics.Action.status
import com.xebialabs.restito.semantics.Action.stringContent
import com.xebialabs.restito.semantics.Condition.*
import org.glassfish.grizzly.http.util.HttpStatus
import java.io.FileNotFoundException
import kotlin.test.assertEquals
import kotlin.test.Test
import kotlin.test.assertFailsWith


private const val PORT = 8081

internal class UrlUtilsTest {

    @Test
    fun load() {
        withStubServer { server ->
            whenHttp(server)
                .match(startsWithUri("/ping"))
                .then(stringContent("pong"))

            val response = load("http://localhost:$PORT/ping")

            assertEquals("pong", response)
        }
    }

    @Test
    fun loadError() {
        assertFailsWith(FileNotFoundException::class) {
            withStubServer { server ->
                whenHttp(server)
                    .match(startsWithUri("/ping"))
                    .then(status(HttpStatus.NOT_FOUND_404))

                load("http://localhost:$PORT/ping")
            }
        }
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