fun String.addEnthusiasm(amount: Int = 1) = this + "!".repeat(amount)

fun <T> T.easyPrint(): T {
    println(this)
    return this
}

fun main() {
    val foo = "Hello"
    println(foo.addEnthusiasm(5))
    foo.easyPrint()
    42.easyPrint()

    foo.easyPrint().addEnthusiasm(5).easyPrint()
}
