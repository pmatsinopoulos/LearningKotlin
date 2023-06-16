import java.io.File
import java.util.*
import kotlin.math.roundToInt

const val TAVERN_NAME = "Taernyl's Folly"
const val MENU_TITLE = "*** Welcome to $TAVERN_NAME ***"

var playerGold = 10
var playerSilver = 10
val patronList = mutableListOf("Eli", "Mordoc", "Sophie")
val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")
val uniquePatrons = mutableSetOf<String>()
val menuList = File("data/tavern-menu-data.txt")
    .readText()
    .split("\n")
    .filterNot { it == "" }
val patronGold: MutableMap<String, Double> = mutableMapOf()

fun main() {
    if (patronList.contains("Eli")) {
        println("The tavern master says: Eli's in the back playing cards.")
    } else {
        println("The tavern master says: Eli isn't here.")
    }
    if (patronList.containsAll(listOf("Sophie", "Mordoc"))) {
        println("The tavern master says: Yea, they're seated by the stew kettle.")
    } else {
        println("The tavern master says: Nay, they departed hours ago.")
    }

    printMenu()

    (0..9).forEach { _ ->
        val first = patronList.shuffled().first()
        val last = lastName.shuffled().first()
        val name = "$first $last"
        uniquePatrons.add(name)
    }
    println(uniquePatrons)

    var orderCount = 0
    while (orderCount < 10) {
        placeOrder(patron = uniquePatrons.shuffled().first(), menuData = menuList.shuffled().first())
        orderCount++
    }

    uniquePatrons.forEach { patron ->
        patronGold[patron] = 6.0
    }

    println(patronGold)
}

private fun printMenu() {
    println()
    println(MENU_TITLE)
    println()
    val menuItemsSplit = menuList.map { menuEntry ->
        menuEntry.split(',')
    }
    val menuItemSplitGrouped = menuItemsSplit.groupBy { mi -> mi[0] }
    menuItemSplitGrouped.forEach { menuItemGroup ->
        printMenuType(menuType = menuItemGroup.key)
        menuItemGroup.value.forEach { menuEntry ->
            printMenuEntry(menuEntry = menuEntry)
        }
    }
    println()
}

private fun printMenuType(menuType: String): Unit {
    val padding = (MENU_TITLE.length - menuType.length) / 2 + menuType.length
    println("%${padding}s".format("~[$menuType]~"))
}

private fun printMenuEntry(menuEntry: List<String>) {
    val (_, menuItem, price) = menuEntry
    val numberOfDotsInBetween = calculateNumberOfDotsInBetween(menuItem = menuItem, price = price.toDouble())
    val dotsString = ".".repeat(numberOfDotsInBetween)
    println("$menuItem$dotsString$price")
}

private fun calculateNumberOfDotsInBetween(menuItem: String, price: Double): Int {
    return MENU_TITLE.length - menuItem.length - "%.2f".format(price).length
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

private fun placeOrder(patron: String, menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("$patron speaks with $tavernMaster about their order.")

    val (type, name, price) = menuData.split(',')
    val message = "$patron buys a $name ($type) for $price GOLD."
    println(message)

//    performPurchase(price.toDoubleOrNull() ?: 0.0)

    val phrase = if (name == "Dragon's Breath") {
        "$patron exclaims ${toDragonSpeak("Ah, Delicious $name!")}"
    } else {
        "$patron says: Thanks for the $name."
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