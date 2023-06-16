package com.bignerdranch.nyethack

class LootBox<T>(item: T) {
    var open = false
    private var loot: T = item

    fun fetch(): T? {
        return loot.takeIf { open }
    }
}

class Fedora(val name: String, val value: Int)

class Coin(val value: Int)

fun main() {
    val lootBoxOne = LootBox(item = Fedora(name = "a generic-looking Fedora", value = 15))
    val lootBoxTwo = LootBox(item = Coin(value = 15))
    lootBoxOne.open = true
    lootBoxOne.fetch()?.run {
        println("name: $name, value: $value")
    }
}
