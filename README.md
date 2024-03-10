# HashMap Class

## Overview

This project implements a basic HashMap class in Kotlin. The HashMap supports key-value pairs, providing methods to put, get, and check for the existence of keys.

## Usage

### Initialization

You can create a HashMap instance by either using the default size (1000) or specifying a custom size.

```kotlin
// Create a HashMap with the default size (1000)
val hashMap = HashMap()

// Create a HashMap with a custom size (e.g., 500)
val customHashMap = HashMap(500)
```

### Putting and Getting Values
To add a key-value pair to the HashMap, use the put method. To retrieve the value associated with a key, use the get method.

```kotlin

// Adding key-value pairs
hashMap.put(1, 42)
hashMap.put(2, 84)

// Retrieving values
val value1 = hashMap.get(1) // Returns 42
val value2 = hashMap.get(2) // Returns 84
```

### Checking for Key Existence

 You can check if a key exists in the HashMap using the containsKey method.

```kotlin

val keyExists = hashMap.containsKey(1) // Returns true
val keyNotExists = hashMap.containsKey(3) // Returns false
```