const val TAVERN_NAME = "Taernyl's Folly"

fun main() {
    placeOrder("shandy,Dragon's Breath,5.91")
}

private fun placeOrder(menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("Madrigal speaks with $tavernMaster about their order.")

    val (type, name, price) = menuData.split(',')
    val message = "Madrigal buys a $name ($type) for $price GOLD."
    println(message)

    val phrase = "Ah, delicious $name!"
    println(toDragonSpeak(phrase))
}

private fun toDragonSpeak(phrase: String) =
    phrase.replace(Regex("[aeiou]")) {
        when (it.value) {
            "a" -> "4"
            "3" -> "3"
            "i" -> "1"
            "o" -> "0"
            "u" -> "|_|"
            else -> it.value
        }
    }