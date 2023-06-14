fun main() {
    val greetingFunction: (String, Int) -> String = { playerName, numBuildings ->
        val currentYear = 2018
        println("Adding $numBuildings houses")
        "Welcome to SimVillage, $playerName! (copyright $currentYear)"
    }
    println(greetingFunction("Guyal", 2))
}