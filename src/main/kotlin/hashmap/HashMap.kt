package hashmap

import java.lang.RuntimeException

class HashMap<K,V> {
    private var tableSize = 1000
    private var hashTable: Array<Pair<K, V?>?>

    constructor(size: Int) : this() {
        this.tableSize = size
        this.hashTable = arrayOfNulls(tableSize)
    }

    constructor() {
        this.hashTable = arrayOfNulls(tableSize)
    }

    fun get(key: K): V? {
        return getEntryForKey(key)?.let { getValue(it) }
    }

    fun put(key: K, value: V) {
        var indexToInsert = hashFunction(key)
        val initialIndex = indexToInsert
        while (true) {
            val entryAtIndex = getEntryAtIndex(indexToInsert)

            if (entryAtIndex == null || getKey(entryAtIndex) == key) {
                setKeyAndValue(Pair(key, value), indexToInsert)
                return
            }

            indexToInsert = nextIndex(indexToInsert)
            if (indexToInsert == initialIndex) {
                // TODO :: Handle collision more gracefully, e.g., resize the table
                throw RuntimeException("no space in hashMap")
            }
        }
    }

    fun containsKey(key: K): Boolean {
        getEntryForKey(key)?.let {
            return true
        }
        return false
    }

    private fun hashFunction(key: K): Int {
        return key.hashCode() % tableSize
    }

    private fun nextIndex(index: Int): Int {
        return (index + 1) % tableSize
    }

    private fun getEntryAtIndex(index: Int): Pair<K, V?>? {
        return hashTable[index]
    }

    private fun getKey(entry: Pair<K, V?>): K {
        return entry.first
    }

    private fun getValue(entry: Pair<K, V?>): V? {
        return entry.second
    }

    private fun setKeyAndValue(entry: Pair<K, V?>, index: Int) {
        hashTable[index] = entry
    }

    private fun getEntryForKey(key: K): Pair<K, V?>? {
        var indexToSeek = hashFunction(key)
        val initialIndex = indexToSeek
        do {
            val keyAndValue = getEntryAtIndex(indexToSeek) ?: return null
            if (getKey(keyAndValue) == key) {
                return keyAndValue
            } else {
                indexToSeek = nextIndex(indexToSeek)
            }
        } while (initialIndex != indexToSeek)

        return null
    }
}
