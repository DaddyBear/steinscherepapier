package huma.rockpaperscissors

import kotlin.random.Random
import huma.rockpaperscissors.asciiart.PAPER as PAPER_SYMBOL
import huma.rockpaperscissors.asciiart.ROCK as ROCK_SYMBOL
import huma.rockpaperscissors.asciiart.SCISSORS as SCISSORS_SYMBOL

// Data classes

data class GameParameter(val rounds: Int, val player1: Player, val player2: Player)

data class Player(val name: String, val strategy: Strategy = Strategy.RANDOM) {

    /**
     * Select a shape by the players strategy.
     */
    fun roShamBo() = strategy.select()
}

data class Match(val round: Int, val player1Shape: Shape, val player2Shape: Shape, val winner: String)

// Enums

enum class Shape {
    ROCK,
    PAPER,
    SCISSORS;

    fun symbol() = when (this) {
        ROCK -> ROCK_SYMBOL
        PAPER -> PAPER_SYMBOL
        SCISSORS -> SCISSORS_SYMBOL
    }
}

private val shapeSize = Shape.values().size

enum class Strategy {
    RANDOM,
    ROCK_ONLY,
    PAPER_ONLY,
    SCISSORS_ONLY;

    fun select(): Shape {
        return when (this) {
            ROCK_ONLY -> Shape.ROCK
            PAPER_ONLY -> Shape.PAPER
            SCISSORS_ONLY -> Shape.SCISSORS
            else -> {
                Shape.values()[Random.nextInt(0, shapeSize)]
            }
        }
    }
}
