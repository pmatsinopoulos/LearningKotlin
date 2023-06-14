fun main() {
    val greetingFunction: (String) -> String = { playerName ->
        val currentYear = 2018
        "Welcome to SimVillage, $playerName! (copyright $currentYear)"
    }
    println(greetingFunction("Guyal"))
}