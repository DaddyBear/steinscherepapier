package huma.rockpaperscissors

import kotlin.random.Random

// Data classes

data class GameParameter(val rounds: Int, val player1: Player, val player2: Player)

data class Player(val name: String, val strategy: Strategy = Strategy.RANDOM)

// Enums

enum class Shape {
    ROCK,
    PAPER,
    SCISSORS
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
