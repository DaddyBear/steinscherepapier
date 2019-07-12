package huma.rockpaperscissors

import huma.rockpaperscissors.gameflow.introduction
import huma.rockpaperscissors.gameflow.output
import huma.rockpaperscissors.gameflow.playMatch
import huma.rockpaperscissors.gameflow.stopGame
import huma.rockpaperscissors.utils.getValueWhenInRangeElseNull

fun main(args: Array<String>) {
    introduction()

    val rounds: Int = readUserInputInt(1..100_000, "Please tell me how many rounds do you wanna let play?")
    output("Okay so we play $rounds rounds")

    // Let's play
    playMatch()

    // Print result


    // This is the end
    stopGame()
}

fun readUserInputInt(range: IntRange, question: String): Int {
    val rangeString = "Insert a number between ${range.first} and ${range.last}"

    var input: Int? = null
    while (input == null) {
        output("$question $rangeString")
        try {
            input = readLine()!!.toInt().getValueWhenInRangeElseNull(range)
        } catch (e: NumberFormatException) {
            output("Sorry that was not a correct number, please try again.")
        }
    }
    return input
}

