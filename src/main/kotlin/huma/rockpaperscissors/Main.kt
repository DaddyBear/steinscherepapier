package huma.rockpaperscissors

import huma.rockpaperscissors.gameflow.introduction
import huma.rockpaperscissors.gameflow.playMatch
import huma.rockpaperscissors.gameflow.readGameParameters
import huma.rockpaperscissors.gameflow.stopGame

fun main() {
    introduction()
    val params = readGameParameters()

    // Let's play
    playMatch(params)

    // Print result

    // This is the end
    stopGame()
}
