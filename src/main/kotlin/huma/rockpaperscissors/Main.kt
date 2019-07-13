package huma.rockpaperscissors

import huma.rockpaperscissors.gameflow.introduction
import huma.rockpaperscissors.gameflow.readGameParameters
import huma.rockpaperscissors.gameflow.runMatches
import huma.rockpaperscissors.gameflow.showResults
import huma.rockpaperscissors.gameflow.stopGame

fun main() {
    introduction()
    val params = readGameParameters()

    // Let's play
    val matchResults = runMatches(params)

    // Print result
    showResults(params, matchResults)

    // This is the end
    stopGame()
}
