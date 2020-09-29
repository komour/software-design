import org.junit.jupiter.api.Assertions.*
import java.lang.IllegalArgumentException
import kotlin.test.Test
import kotlin.test.BeforeTest
import kotlin.test.assertFailsWith

class LRUCacheTest {

    private lateinit var cache: LRUCache<Int, Int>

    @BeforeTest
    fun setUpCache() {
        cache = LRUCache(3)
    }

    @Test
    fun putGetSimpleTest() {
        assertEquals(null, cache.get(1))
        cache.put(1, 12)
        cache.put(2, 23)
        cache.put(3, 34)
        assertEquals(12, cache.get(1))
        assertEquals(23, cache.get(2))
        assertEquals(34, cache.get(3))
        assertEquals(null, cache.get(4))
    }

    @Test
    fun updateValueTest() {
        cache.put(4, 7)
        assertEquals(7, cache.get(4))
        cache.put(4, 2)
        assertEquals(2, cache.get(4))
    }

    @Test
    fun changePriorityTest() {
        assertEquals(null, cache.get(1))
        cache.put(1, 1)
        cache.put(2, 2)
        cache.put(3, 3)
        cache.put(4, 4)
        assertEquals(null, cache.get(1)) // first element have to be already deleted from the cache
        assertEquals(2, cache.get(2)) // 2nd element goes to top
        assertEquals(4, cache.get(4)) // 4th element goes to top
        cache.put(42, 239) // element with key-42 goes to top
        assertEquals(null, cache.get(3)) // third element have to be already deleted here because of its lowest priority
        assertEquals(239, cache.get(42))
    }

    @Test
    fun negateSizeTest() {
        assertFailsWith(IllegalArgumentException::class) { LRUCache<Int, Int>(-12) }
    }
}