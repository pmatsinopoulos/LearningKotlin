fun String.addEnthusiasm(amount: Int = 1) = this + "!".repeat(amount)
val String.numOfVowels
    get() = count { "aeiouy".contains(it) }

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
    println("Number of vowels: ${"foo".numOfVowels}")
}
