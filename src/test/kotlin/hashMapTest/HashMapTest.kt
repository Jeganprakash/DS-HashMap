import hashmap.HashMap
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class HashMapTest {

    @Test
    fun `should put and get values`() {
        val hashMapInt = HashMap<Int,Int>()
        hashMapInt.put(1, 42)
        hashMapInt.put(2, 84)

        assertEquals(42, hashMapInt.get(1))
        assertEquals(84, hashMapInt.get(2))

        val hashMapString = HashMap<Int, String>()
        hashMapString.put(1, "Batman")
        hashMapString.put(2, "Ironman")

        assertEquals("Batman", hashMapString.get(1))
        assertEquals("Ironman", hashMapString.get(2))

        data class MyKey(val key: String)
        data class MyClass(val value: String)

        val hashMapCustom = HashMap<MyKey, MyClass>()
        val key1 = MyKey("key1")
        val value1 = MyClass("value1")
        val key2 = MyKey("key2")
        val value2 = MyClass("value2")

        hashMapCustom.put(key1, value1)
        hashMapCustom.put(key2, value2)

        assertEquals(value1, hashMapCustom.get(key1))
        assertEquals(value2, hashMapCustom.get(key2))

        // Adding test for inequality with different string case keys
        val hashMapStringCase = HashMap<String, String>()
        hashMapStringCase.put("Test", "value1")
        hashMapStringCase.put("test", "value2")
        assertNotEquals(hashMapStringCase.get("Test"), hashMapStringCase.get("test"))

        val j = HashMap<List<String>,Int>()
        val listKey = listOf("1","2","3")
        j.put(listKey,9)
        j.put(listOf("1","2","3"),4)
        println(j.get(listKey))

        val a = HashMap<Set<String>,Int>()
        val setKey = setOf("1","2","3")
        a.put(setKey,9)
        a.put(setOf("1","2","3"),4)
        println(a.get(setKey))
        println(setKey == setOf("3","2","1"))

        val b = kotlin.collections.HashMap<Set<String>,Int>()
        val setKey2 = setOf("1","2","3")
        b.put(setKey2,9)
        b.put(setOf("1","2","3"),4)
        println(b.get(setKey))
        println(setKey2 == setOf("3","2","1"))

    }

    @Test
    fun `should return null for non-existent key`() {
        val hashMap = HashMap<Int,Int>()
        assertNull(hashMap.get(3))
    }

    @Test
    fun `should contain key after putting`() {
        val hashMap = HashMap<Int,Int>()
        hashMap.put(4, 168)

        assertTrue(hashMap.containsKey(4))
    }

    @Test
    fun `should handle collisions`() {
        // Add test cases for handling collisions
        // You may need to modify the HashMap size or other parameters for testing
        // ...

        // Example:
        val hashMapWithCollisions = HashMap<Int,Int>()
        hashMapWithCollisions.put(1, 42)
        hashMapWithCollisions.put(1001, 84) // Collision with index 1

        assertEquals(42, hashMapWithCollisions.get(1))
        assertEquals(84, hashMapWithCollisions.get(1001))
    }

    @Test
    fun `should replace key with another value`() {
        val hashMap = HashMap<Int,Int>()
        hashMap.put(1, 42)

        // Replace the value associated with key 1
        hashMap.put(1, 84)
        // Verify that the value for key 1 is updated
        assertEquals(84, hashMap.get(1))
    }

    @Test
    fun `should throw No space exception when table is full`() {
        val hashMap = HashMap<Int,Int>(3)
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
