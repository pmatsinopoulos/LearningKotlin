package com.bignerdranch.nyethack

class Player(
    _name: String,
    var healthPoints: Int = 100,
    var isBlessed: Boolean,
    private var isImmortal: Boolean
) {
    var name: String = _name
        get() = field.capitalize()
        set(value) {
            field = value.trim()
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
}
