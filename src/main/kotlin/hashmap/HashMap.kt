package hashmap

import java.lang.RuntimeException
import java.security.Key

class HashMap {
    private var tableSize = 1000
    private lateinit var hashTable: Array<Pair<Int, Int?>?>

    constructor(size: Int) : this() {
        this.tableSize = size
        this.hashTable = arrayOfNulls(tableSize)
    }

    constructor() {
        this.hashTable = arrayOfNulls(tableSize)
    }


    fun get(key: Int):Int?{
        return getEntryForKey(key)?.let { getValue(it) }
    }

    fun put(key: Int,value: Int){
        var indexToInsert = hashFunction(key)
        val initialIndex = indexToInsert
        while(true){
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

    fun containsKey(key: Int):Boolean{
        getEntryForKey(key)?.let {
            return true
        }
        return false
    }

    private fun hashFunction(key:Int):Int {
        return key % tableSize
    }

    private fun nextIndex(index: Int):Int{
        return (index + 1) % tableSize
    }

    private fun getEntryAtIndex(index: Int) : Pair<Int,Int?>?{
        return hashTable[index]
    }

    private fun getKey(entry: Pair<Int,Int?>) : Int{
        return entry.first
    }

    private fun getValue(entry: Pair<Int,Int?>) : Int?{
        return entry.second
    }

    private fun setKeyAndValue(entry: Pair<Int,Int?>,index: Int){
        hashTable[index] = entry
    }


    private fun getEntryForKey(key: Int):Pair<Int,Int?>?{
        var indexToSeek = hashFunction(key)
        val initialIndex = indexToSeek
        do {
            val keyAndValue = getEntryAtIndex(indexToSeek) ?: return null
            if(getKey(keyAndValue) == key){
                return keyAndValue
            } else {
                indexToSeek = nextIndex(indexToSeek)
            }
        } while (initialIndex != indexToSeek)

        return null
    }
}