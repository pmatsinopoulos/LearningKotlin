package com.bignerdranch.nyethack

class LootBox<T : Loot>(vararg item: T) {
    var open = false
    private var loot: Array<out T> = item

    fun fetch(item: Int): T? {
        return loot[item].takeIf { open }
    }

    fun <R> fetch(item: Int, lootModFunction: (T) -> R): R? {
        return lootModFunction(loot[item]).takeIf { open }
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
    val lootBoxOne = LootBox(
        Fedora(name = "a generic-looking Fedora", value = 15),
        Fedora(name = "a dazzling magenta fedora", value = 35)
    )
    val lootBoxTwo = LootBox(Coin(value = 15))
    lootBoxOne.open = true
    lootBoxOne.fetch(1)?.run {
        println("name: $name, value: $value")
    }
    val coin = lootBoxOne.fetch(1) {
        Coin(value = it.value * 3)
    }
    println("Coin: $coin")
}
