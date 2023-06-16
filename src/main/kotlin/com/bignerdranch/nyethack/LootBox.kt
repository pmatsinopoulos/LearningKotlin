package com.bignerdranch.nyethack

class LootBox<T : Loot>(item: T) {
    var open = false
    private var loot: T = item

    fun fetch(): T? {
        return loot.takeIf { open }
    }

    fun <R> fetch(lootModFunction: (T) -> R): R? {
        return lootModFunction(loot).takeIf { open }
    }
}

open class Loot(val value: Int)

class Fedora(val name: String, value: Int) : Loot(value)

class Coin(value: Int) : Loot(value) {
    override fun toString(): String {
        return value.toString()
    }
}

fun main() {
    val lootBoxOne = LootBox(item = Fedora(name = "a generic-looking Fedora", value = 15))
    val lootBoxTwo = LootBox(item = Coin(value = 15))
    lootBoxOne.open = true
    lootBoxOne.fetch()?.run {
        println("name: $name, value: $value")
    }
    val coin = lootBoxOne.fetch() {
        Coin(value = it.value * 3)
    }
    println("Coin: $coin")
}
