package com.bignerdranch.nyethack

fun main() {
    val valuesToAdd = listOf(1, 18, 73, 3, 44, 6, 1, 33, 2, 22, 5, 7)
    val result = valuesToAdd.filter { it -> it >= 5 }
        .chunked(2)
        .map { pair -> pair[0] * pair[1] }
        .reduce { accumulator, item ->
            accumulator + item
        }
    println(result) // expected result is 2339
}
