package com.bignerdranch.nyethack

import java.io.File

class Player(
    _name: String,
    override var healthPoints: Int = 100,
    var isBlessed: Boolean,
    private var isImmortal: Boolean
) : Fightable {
    var name: String = _name
        get() = "${field.capitalize()} of $hometown"
        set(value) {
            field = value.trim()
        }
    val hometown by lazy { selectHometown() }
    var currentPosition = Coordinate(x = 0, y = 0)

    override val diceCount = 3
    override val diceSides = 6
    override fun attack(opponent: Fightable): Int {
        val damageDealt = if (isBlessed) {
            damageRoll * 2
        } else {
            damageRoll
        }
        opponent.healthPoints -= damageDealt
        return damageDealt
    }

    init {
        require(healthPoints > 0) { "healthPoints must be greater than zero." }
        require(name.isNotBlank()) { "name can't be blank" }
    }

    constructor(_name: String) : this(_name = _name, isBlessed = true, isImmortal = false) {
        if (name.lowercase() == "kar") {
            healthPoints = 40
        }
    }

    fun castFireball(numFireballs: Int = 2) = println("A glass of Fireball springs into existence. (x$numFireballs)")

    fun auraColor() =
        if (isBlessed && healthPoints > 50 || isImmortal) {
            "GREEN"
        } else {
            "NONE"
        }


    fun formatHealthStatus() = when (healthPoints) {
        in 15..74 -> " looks pretty hurt"
        in 75..89 -> if (isBlessed) {
            " has some minor wounds but is healing quite quickly!"
        } else {
            " has some minor wounds."
        }

        in 90..99 -> " has a few scratches."
        100 -> " is in excellent condition"
        else -> " is in awful condition!"
    }

    private fun selectHometown() = File("data/towns.txt")
        .readText()
        .split("\n")
        .filterNot { it == "" }
        .shuffled()
        .first()
}
