import java.util.*

fun main() {
    var beverage = readLine()
    beverage = null
    beverage?.let {
        it.capitalize()
    } ?: println("I can't do that without crashing - beverage was null")
    var beverageServed: String = beverage ?: "Buttered Ale"
    println(beverageServed)
}