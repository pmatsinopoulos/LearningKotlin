const val MIN_NUM_FIREBALLS = 1
const val MAX_NUM_FIREBALLS = 2_000
const val MIN_INEBRIATION_LEVEL = 1
const val MAX_INEBRIATION_LEVEL = 50

fun main(args: Array<String>) {
    val name = "Madrigal"
    var healthPoints = 89
    val isBlessed = true
    val isImmortal = false

    // Aura
    val auraColor = auraColor(isBlessed, healthPoints, isImmortal)

    val healthStatus = formatHealthStatus(healthPoints, isBlessed)

    printPlayerStatus(auraColor, isBlessed, name, healthStatus)

    var inebriationLevel = castFireball(numFireballs = 5)
    println("inebriationLevel: $inebriationLevel")
    printInebriationStatus(inebriationLevel = inebriationLevel)

    inebriationLevel = castFireball(numFireballs = 2_000)
    println("inebriationLevel: $inebriationLevel")

    inebriationLevel = castFireball(numFireballs = 1)
    println("inebriationLevel: $inebriationLevel")
}

private fun printInebriationStatus(inebriationLevel: Double) {
    val result = when (inebriationLevel) {
        in 1.0..9.99 -> "tipsy"
        in 10.0..19.99 -> "sloshed"
        in 20.0..29.99 -> "soused"
        in 30.0..39.99 -> "stewed"
        in 40.0..49.99 -> "..t0aSt3d"
        else -> "very drunk"
    }
    println("Inebriation Status: $result")
}

private fun printPlayerStatus(
    auraColor: String,
    isBlessed: Boolean,
    name: String,
    healthStatus: String
) {
    println(auraColor)
    println("(Aura: $auraColor) (Blessed: ${if (isBlessed) "YES" else "NO"}) $name$healthStatus")
}

private fun auraColor(isBlessed: Boolean, healthPoints: Int, isImmortal: Boolean) =
    if (isBlessed && healthPoints > 50 || isImmortal) {
        "GREEN"
    } else {
        "NONE"
    }


private fun formatHealthStatus(healthPoints: Int, isBlessed: Boolean) =
    when (healthPoints) {
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

private fun castFireball(numFireballs: Int = 2): Double {
    if (numFireballs !in MIN_NUM_FIREBALLS..MAX_NUM_FIREBALLS) throw IllegalArgumentException("numFireballs should be between 1 and 2000")

    println("A glass of Fireball springs into existence. (x$numFireballs)")
    var inebriationLevel = 0.0

    inebriationLevel =
        (numFireballs - MIN_NUM_FIREBALLS) * MAX_INEBRIATION_LEVEL / MAX_NUM_FIREBALLS.toDouble() + MIN_INEBRIATION_LEVEL

    return inebriationLevel
}
