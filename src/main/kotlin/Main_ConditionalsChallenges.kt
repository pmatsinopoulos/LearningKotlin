import java.util.*
import kotlin.math.pow

fun main(args: Array<String>) {
    val name = "Madrigal"
    var healthPoints = 89
    val isBlessed = true
    val isImmortal = false

    val karma = (Math.random().pow((110 - healthPoints) / 100.0) * 20).toInt()
    println("Karma: $karma")

    val auraColor = when (karma) {
        in 0..5 -> "RED"
        in 6..10 -> "ORANGE"
        in 11..15 -> "PURPLE"
        in 16..20 -> "GREEN"
        else -> "NONE"
    }
    println(auraColor)

    val healthStatus = when (healthPoints) {
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

    println("(Aura: $auraColor) (Blessed: ${if (isBlessed) "YES" else "NO"}) $name$healthStatus")
    val statusFormatString = "H(HP) (A) -> H"
    var status = statusFormatString.replace("HP", "HP: $healthPoints")
    status = status.replace("A", "Aura: $auraColor")
    status = status.replace("(H[^P]|H\$)".toRegex()) {
        "$healthStatus${if (it.value.length >= 2) it.value[it.value.length - 1] else ""}"
    }
    println(status)
}