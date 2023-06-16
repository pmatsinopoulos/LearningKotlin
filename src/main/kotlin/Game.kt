fun main(args: Array<String>) {
    val player = Player()
    player.name = "    madrigal    "
    player.castFireball()

    printPlayerStatus(player)
}

private fun printPlayerStatus(
    player: Player
) {
    println(player.auraColor())
    println("(Aura: ${player.auraColor()}) (Blessed: ${if (player.isBlessed) "YES" else "NO"}) ${player.name}${player.formatHealthStatus()}")
}
