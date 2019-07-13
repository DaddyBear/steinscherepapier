package huma.rockpaperscissors.gameflow

import huma.rockpaperscissors.GameParameter
import huma.rockpaperscissors.Player
import huma.rockpaperscissors.Strategy
import huma.rockpaperscissors.asciiart.ROCK_PAPER_SCISSOR
import huma.rockpaperscissors.utils.readUserInputInt
import huma.rockpaperscissors.utils.readUserInputString

fun introduction() {
    output("Hello to rock paper scissors on console")
    output(ROCK_PAPER_SCISSOR)
}

fun readGameParameters(): GameParameter {
    val rounds: Int = readUserInputInt(1..100_000, "Please tell me how many rounds do you wanna let play?")
    output("Okay so we play $rounds rounds\n")

    val player1 = readPlayerParameters("one")
    val player2 = readPlayerParameters("two")

    return GameParameter(rounds = rounds, player1 = player1, player2 = player2)
}

fun readPlayerParameters(player: String): Player {
    val rangePlayerName = 2..15
    val questionPlayerName = "Please choose a name for player"
    val name = readUserInputString(rangePlayerName, "$questionPlayerName $player.")

    val questionPlayerStrategy = """Please select a strategy for player $name.
        | O = Always Stone
        | P = Always Paper
        | X = Always Scissors
        | Any other input selects a random choice.
    """.trimMargin()
    output(questionPlayerStrategy)

    val strategy = when (readLine()) {
        "o", "O" -> Strategy.ROCK_ONLY
        "p", "P" -> Strategy.PAPER_ONLY
        "x", "X" -> Strategy.SCISSORS_ONLY
        else -> Strategy.RANDOM
    }
    output("All right player $name plays with strategy $strategy.\n")

    return Player(name, strategy)
}

fun playMatch(params: GameParameter) {

}

fun stopGame() {
    output("Good buy")
}

fun output(text: String) {
    println(text)
}
