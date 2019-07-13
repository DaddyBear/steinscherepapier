package huma.rockpaperscissors.gameflow

import huma.rockpaperscissors.GameParameter
import huma.rockpaperscissors.Match
import huma.rockpaperscissors.Player
import huma.rockpaperscissors.Shape
import huma.rockpaperscissors.Shape.PAPER
import huma.rockpaperscissors.Shape.ROCK
import huma.rockpaperscissors.Shape.SCISSORS
import huma.rockpaperscissors.Strategy
import huma.rockpaperscissors.asciiart.GREATER
import huma.rockpaperscissors.asciiart.LOWER
import huma.rockpaperscissors.asciiart.NULL
import huma.rockpaperscissors.asciiart.ROCK_PAPER_SCISSOR
import huma.rockpaperscissors.asciiart.combineMultilineStrings
import huma.rockpaperscissors.utils.readUserInputInt
import huma.rockpaperscissors.utils.readUserInputString
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

const val DRAW = "-"
const val ONE = "one"
const val TWO = "two"

fun introduction() {
    output("Hello to rock paper scissors on console")
    output(ROCK_PAPER_SCISSOR)
}

fun readGameParameters(): GameParameter {
    val rounds: Int = readUserInputInt(1..100_000, "Please tell me how many rounds do you wanna let play?")
    output("Okay so we play $rounds rounds\n")

    val player1 = readPlayerParameters(ONE)
    val player2 = readPlayerParameters(TWO)

    return GameParameter(rounds = rounds, player1 = player1, player2 = player2)
}

fun readPlayerParameters(player: String): Player {
    val rangePlayerName = 2..25
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

fun runMatches(params: GameParameter): MutableList<Match> {
    val matches = mutableListOf<Match>()
    val rounds = params.rounds
    // As more matches to play as less time to wait between
    val delayTime: Long = when {
        rounds > 1_000 -> 2
        rounds > 100 -> 44
        rounds > 20 -> 666
        else -> 5_000
    }
    runBlocking {
        for (round in 1..rounds) {
            delay(delayTime)
            matches.add(playMatch(round, params))
            output("\n") // Separator
        }
    }
    return matches
}

fun playMatch(round: Int, params: GameParameter): Match {
    output("Play round $round")
    val shapeP1 = params.player1.roShamBo()
    val shapeP2 = params.player2.roShamBo()
    output("${params.player1.name} choose $shapeP1 and ${params.player2.name} choose $shapeP2.")
    val matchWinner = checkMatchResult(shapeP1, params.player1.name, shapeP2, params.player2.name)
    output(combineMultilineStrings(shapeP1.symbol(), matchWinner.second, shapeP2.symbol()))
    return Match(round = round, player1Shape = shapeP1, player2Shape = shapeP2, winner = matchWinner.first)
}

fun checkMatchResult(p1Shape: Shape, p1Name: String, p2Shape: Shape, p2Name: String): Pair<String, String> {
    return when {
        p1Shape == p2Shape -> {
            output("It's a draw")
            Pair(DRAW, NULL)
        }
        p1Shape == ROCK && p2Shape == SCISSORS ||
                p1Shape == PAPER && p2Shape == ROCK ||
                p1Shape == SCISSORS && p2Shape == PAPER -> {

            output("$p1Name won")
            Pair(p1Name, GREATER)
        }
        else -> {
            output("$p2Name won")
            Pair(p2Name, LOWER)
        }
    }
}

fun showResults(params: GameParameter, matchResults: MutableList<Match>) {
    val p1Win = matchResults.count { it.winner == params.player1.name }
    val p2Win = matchResults.count { it.winner == params.player2.name }
    val result = when {
        p1Win == p2Win -> "Both player won same amount of games. It's a draw."
        p1Win > p2Win -> "${params.player1.name} won more matches. Congratulation"
        else -> "${params.player2.name} won more matches. Congratulation"
    }
    output(
        """Statistics:
        |${params.rounds} rounds were played
        |Player one '${params.player1.name}' won $p1Win times
        |Player two '${params.player2.name}' won $p2Win times
        |$result
    """.trimMargin()
    )
}

fun stopGame() {
    output("Good buy")
    output(ROCK_PAPER_SCISSOR)
}

fun output(text: String) = println(text)
