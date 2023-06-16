package com.bignerdranch.nyethack

abstract class Monster(
    val name: String,
    val description: String,
    override var healthPoints: Int
) : Fightable {
    override fun attack(opponent: Fightable): Int {
        val damageDealt = damageRoll
        opponent.healthPoints -= damageDealt
        return damageDealt
    }
}
