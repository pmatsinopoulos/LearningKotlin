class Player {
    var name: String = ""
        get() = field.capitalize()
        set(value) {
            field = value.trim()
        }
    var healthPoints = 89
    val isBlessed = true
    private val isImmortal = false

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
