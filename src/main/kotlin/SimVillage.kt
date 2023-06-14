fun main() {
    val greetingFunction = {
        val currentYear = 2018
        "Welcome to SimVillage, Mayor! (copyright $currentYear)"
    }
    println(greetingFunction())
}