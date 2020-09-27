import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class LRUCacheTest {

    @Test
    fun putGetSimpleTest() {
        val cache = LRUCache<Int>(3)
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
        val cache = LRUCache<Int>(3)
        cache.put(4, 7)
        assertEquals(7, cache.get(4))
        cache.put(4, 2)
        assertEquals(2, cache.get(4))
    }

    @Test
    fun changePriorityTest() {
        val cache = LRUCache<Int>(3)
        assertEquals(null, cache.get(1))
        cache.put(1, 1)
        cache.put(2, 2)
        cache.put(3, 3)
        cache.put(4, 4)
        assertEquals(null, cache.get(1))
        assertEquals(2, cache.get(2))
        assertEquals(4, cache.get(4))
        cache.put(42, 239)
        assertEquals(null, cache.get(3))
        assertEquals(239, cache.get(42))
    }

    @Test
    fun negateSizeTest() {
        assertThrows(Exception::class.java) {
            LRUCache<Int>(-12)
        }
    }
}