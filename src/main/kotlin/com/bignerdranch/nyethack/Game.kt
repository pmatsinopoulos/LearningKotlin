package com.bignerdranch.nyethack

object Game {
    private val player = Player(_name = "Madrigal")
    private var currentRoom: Room = TownSquare(name = "TS")

    init {
        println("Welcome, adventurer")
        player.castFireball()
    }

    fun play() {
        while (true) {
            println(currentRoom.description())
            println(currentRoom.load())
            printPlayerStatus()

            print("> Enter your command: ")
            println("Last command: ${readLine()}")
        }
    }

    private fun printPlayerStatus() {
        println(player.auraColor())
        println(player.healthPoints)
        println("(Aura: ${player.auraColor()}) (Blessed: ${if (player.isBlessed) "YES" else "NO"}) ${player.name}${player.formatHealthStatus()}")
    }

}

fun main(args: Array<String>) {
    Game.play()
}
