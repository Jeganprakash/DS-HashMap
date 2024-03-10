import hashmap.HashMap
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class HashMapTest {

    @Test
    fun `should put and get values`() {
        val hashMap = HashMap()
        hashMap.put(1, 42)
        hashMap.put(2, 84)

        assertEquals(42, hashMap.get(1))
        assertEquals(84, hashMap.get(2))
    }

    @Test
    fun `should return null for non-existent key`() {
        val hashMap = HashMap()
        assertNull(hashMap.get(3))
    }

    @Test
    fun `should contain key after putting`() {
        val hashMap = HashMap()
        hashMap.put(4, 168)

        assertTrue(hashMap.containsKey(4))
    }

    @Test
    fun `should handle collisions`() {
        // Add test cases for handling collisions
        // You may need to modify the HashMap size or other parameters for testing
        // ...

        // Example:
        val hashMapWithCollisions = HashMap()
        hashMapWithCollisions.put(1, 42)
        hashMapWithCollisions.put(1001, 84) // Collision with index 1

        assertEquals(42, hashMapWithCollisions.get(1))
        assertEquals(84, hashMapWithCollisions.get(1001))
    }

    @Test
    fun `should replace key with another value`() {
        val hashMap = HashMap()
        hashMap.put(1, 42)

        // Replace the value associated with key 1
        hashMap.put(1, 84)
        // Verify that the value for key 1 is updated
        assertEquals(84, hashMap.get(1))
    }

    @Test
    fun `should throw No space exception when table is full`() {
        val hashMap = HashMap(3)
        val tableSize = 3

        // Insert elements until the table is full
        for (i in 1..tableSize) {
            hashMap.put(i, i * 10)
        }

        // Attempt to insert another element should throw an exception
        assertThrows(RuntimeException::class.java) {
            hashMap.put(tableSize + 1, (tableSize + 1) * 10)
        }
    }
}
