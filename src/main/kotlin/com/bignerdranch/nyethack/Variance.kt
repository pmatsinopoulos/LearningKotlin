package com.bignerdranch.nyethack

class Barrel<out T>(val item: T)

fun main() {
    val fedoraBarrel = Barrel(Fedora("foo", 15))
    var lootBarrel: Barrel<Loot> = Barrel(Coin(30))

    lootBarrel = fedoraBarrel
    var myFedora: Fedora = lootBarrel.item
}
