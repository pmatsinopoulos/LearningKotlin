package com.bignerdranch.nyethack

fun main(args: Array<String>) {
    val player = Player(
        _name = "madrigal",
        healthPoints = 89,
        isBlessed = true,
        isImmortal = false
    )
    player.castFireball()

    printPlayerStatus(player)
}

private fun printPlayerStatus(
    player: Player
) {
    println(player.auraColor())
    println("(Aura: ${player.auraColor()}) (Blessed: ${if (player.isBlessed) "YES" else "NO"}) ${player.name}${player.formatHealthStatus()}")
}
