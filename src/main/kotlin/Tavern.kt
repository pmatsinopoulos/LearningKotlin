import java.util.*
import kotlin.math.roundToInt

const val TAVERN_NAME = "Taernyl's Folly"

var playerGold = 10
var playerSilver = 10
val patronList: List<String> = listOf("Eli", "Mordoc", "Sophie")

fun main() {
    placeOrder("shandy,Dragon's Breath,5.91")
    println(patronList)
}

private fun performPurchase(price: Double) {
    displayBalance()
    println("Purchasing item for $price")
    val totalGoldInPurse = playerGold + playerSilver / 100.0
    println("Total purse: $totalGoldInPurse")
    val remainingBalance = totalGoldInPurse - price
    if (remainingBalance < 0) {
        println("Not enough money")
    } else {
        println("Remaining balance: ${"%.2f".format(remainingBalance)}")
        val remainingGold = remainingBalance.toInt()
        val remainingSilver = (remainingBalance % 1 * 100).roundToInt()
        playerGold = remainingGold
        playerSilver = remainingSilver
        println("New wallet, Gold: $playerGold, Silver: $playerSilver")
    }
}

private fun displayBalance() {
    println("Player's purse balance: Gold: $playerGold , Silver: $playerSilver")
}

private fun placeOrder(menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("Madrigal speaks with $tavernMaster about their order.")

    val (type, name, price) = menuData.split(',')
    val message = "Madrigal buys a $name ($type) for $price GOLD."
    println(message)

    performPurchase(price.toDoubleOrNull() ?: 0.0)

    val phrase = if (name == "Dragon's Breath") {
        "Madrigal exclaims ${toDragonSpeak("Ah, Delicious $name!")}"
    } else {
        "Madrigal says: Thanks for the $name."
    }
    println(phrase)
}

private fun toDragonSpeak(phrase: String) =
    phrase.replace(Regex("[aeiouAEIOU]")) {
        when (it.value.lowercase(Locale.getDefault())) {
            "a" -> "4"
            "e" -> "3"
            "i" -> "1"
            "o" -> "0"
            "u" -> "|_|"
            else -> it.value
        }
    }