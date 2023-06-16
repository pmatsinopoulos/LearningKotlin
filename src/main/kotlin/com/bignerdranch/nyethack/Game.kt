package com.bignerdranch.nyethack

import kotlin.system.exitProcess

object Game {
    private val player = Player(_name = "Madrigal")
    private var currentRoom: Room = TownSquare(name = "Town Square")
    private var endGame = false

    init {
        println("Welcome, adventurer")
        player.castFireball()
    }

    fun play() {
        while (!endGame) {
            println(currentRoom.description())
            println(currentRoom.load())
            printPlayerStatus()

            print("> Enter your command: ")
            println(GameInput(readLine()).processCommand())
        }
    }

    private var worldMap = listOf(
        listOf(currentRoom, Room("Tavern"), Room("Back Room")),
        listOf(Room("Long Corridor"), Room("Generic Room"))
    )

    private fun move(directionInput: String) =
        try {
            val direction = Direction.valueOf(directionInput.uppercase())
            val newPosition = direction.updateCoordinate(player.currentPosition)
            if (!newPosition.isInBounds) {
                throw IllegalStateException("$direction is out of bounds")
            }

            val newRoom = worldMap[newPosition.y][newPosition.x]
            player.currentPosition = newPosition
            currentRoom = newRoom
            "OK, you move $direction to the ${newRoom.name}.\n${newRoom.load()}"
        } catch (e: Exception) {
            "Invalid direction: $directionInput"
        }

    private fun printPlayerStatus() {
        println(player.auraColor())
        println(player.healthPoints)
        println("(Aura: ${player.auraColor()}) (Blessed: ${if (player.isBlessed) "YES" else "NO"}) ${player.name}${player.formatHealthStatus()}")
    }

    private fun printMap() {
        worldMap.forEachIndexed { y, yRooms ->
            yRooms.forEachIndexed { x, xRoom ->
                val indicator = if (player.currentPosition == Coordinate(x, y)) {
                    "X"
                } else {
                    "O"
                }
                print("$indicator ")
            }
            println()
        }
    }

    private fun fight() = currentRoom.monster?.let {
        while (player.healthPoints > 0 && it.healthPoints > 0) {
            slay(it)
            Thread.sleep(1_000)
        }

        "Combat complete."
    } ?: "There is nothing here to fight"

    private fun slay(monster: Monster) {
        println("${monster.name} did ${monster.attack(player)} damage!")
        println("${player.name} did ${player.attack(monster)} damage!")

        if (player.healthPoints <= 0) {
            println(">>>> You have been defeated! Thanks for playing. <<<<")
            exitProcess(0)
        }

        if (monster.healthPoints <= 0) {
            println(">>>> ${monster.name} has been defeated! <<<<<")
            currentRoom.monster = null
        }
    }

    private class GameInput(arg: String?) {
        private val input = arg ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1) { "" }

        fun processCommand() = when (command.lowercase()) {
            "fight" -> fight()
            "move" -> move(argument)
            "map" -> printMap()
            in listOf("exit", "quit") -> endGame = true
            else -> commandNotFound()
        }

        private fun commandNotFound() = "I'm not quite sure what you're trying to do!"
    }
}

fun main(args: Array<String>) {
    Game.play()
}
