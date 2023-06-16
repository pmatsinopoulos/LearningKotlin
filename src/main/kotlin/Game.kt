fun main(args: Array<String>) {
    var healthPoints = 89
    val isBlessed = true
    val isImmortal = false

    val player = Player()
    player.castFireball()

    // Aura
    val auraColor = auraColor(isBlessed, healthPoints, isImmortal)

    val healthStatus = formatHealthStatus(healthPoints, isBlessed)

    printPlayerStatus(auraColor, isBlessed, player.name, healthStatus)
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
