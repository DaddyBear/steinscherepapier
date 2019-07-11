package huma.rockpaperscissors

import huma.rockpaperscissors.asciiart.ROCK_PAPER_SCISSOR

fun main(args: Array<String>) {
    println("Hello to rock paper scissors on console")
    println(ROCK_PAPER_SCISSOR)

    val rounds: Int = readUserInputInt(1..100_000, "Please tell me how many rounds do you wanna let play?")
    println("Okay so we play $rounds rounds")

    // Print result


    // This is the end
    println("Good buy")
}

fun readUserInputInt(range: IntRange, question: String): Int {
    val rangeString = "Insert a number between ${range.first} and ${range.last}"

    var input: Int? = null
    while (input == null) {
        println("$question $rangeString")
        try {
            input = readLine()!!.toInt().checkRange(range)
        } catch (e: NumberFormatException) {
            println("Sorry that was not a correct number, please try again.")
        }
    }
    return input
}

// Extension function
fun Int.checkRange(range: IntRange) =
    if (this in range) {
        this
    } else {
        println("Sorry, your number is not inside the range please try again.")
        null
    }

