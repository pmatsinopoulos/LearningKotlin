package com.bignerdranch.nyethack

fun main(args: Array<String>) {
    val player = Player(
        _name = "Kard"
    )
    player.castFireball()

    var currentRoom = Room(name = "Foyer")
    println(currentRoom.description())
    println(currentRoom.load())

    var townSquare: Room = TownSquare()
    println(townSquare.description())
    println(townSquare.load())

    printPlayerStatus(player)
}

private fun printPlayerStatus(
    player: Player
) {
    println(player.auraColor())
    println(player.healthPoints)
    println("(Aura: ${player.auraColor()}) (Blessed: ${if (player.isBlessed) "YES" else "NO"}) ${player.name}${player.formatHealthStatus()}")
}
