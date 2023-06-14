fun main() {
    runSimulation(playerName = "Guyal", costPrinter = ::printConstructionCost) { playerName, numBuildings ->
        val currentYear = 2018
        println("Adding $numBuildings houses")
        "Welcome to SimVillage, $playerName! (copyright $currentYear)"
    }
}

private inline fun runSimulation(
    playerName: String,
    costPrinter: (Int) -> Unit,
    greetingFunction: (String, Int) -> String
) {
    val numBuildings = (1..3).shuffled().last()
    costPrinter(numBuildings)
    println(greetingFunction(playerName, numBuildings))
}

private fun printConstructionCost(numBuildings: Int) {
    val cost = 500
    println("construction cost: ${cost * numBuildings}")
}