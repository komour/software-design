import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlin.test.Test
import kotlin.test.BeforeTest

import java.time.Instant
import java.time.temporal.ChronoUnit

internal class TagStatsTest {

    @MockK
    private lateinit var api: VkApi

    private lateinit var stats: TagStats

    @BeforeTest
    @Throws(Exception::class)
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        stats = TagStats(api)
    }

    @Test
    fun tagHistory() {
        val hours = 4

        val time1 = Instant.now().minus(1, ChronoUnit.HOURS).epochSecond
        val time2 = Instant.now().minus(2, ChronoUnit.HOURS).epochSecond
        val time3 = Instant.now().minus(4, ChronoUnit.HOURS).epochSecond

        val time = Instant.now().minus(hours.toLong(), ChronoUnit.HOURS).epochSecond

        every {
            api.loadPosts("#философия", time)
        } returns listOf(
            Post(26163, time1, "sample_text1 #философия"),
            Post(26164, time1, "sample_text2 #философия"),
            Post(3246, time2, "sample_text2 #философия"),
            Post(138334, time3, "sample_text3 #философия")
        )

        assert(stats.tagHistory("философия", hours).contentEquals(intArrayOf(0, 2, 1, 0, 1)))
    }
}