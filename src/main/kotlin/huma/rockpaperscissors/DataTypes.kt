package huma.rockpaperscissors

import kotlin.random.Random

enum class Shape {
    ROCK,
    PAPER,
    SCISSORS
}

private val modulo = Shape.values().size

class Player(name: String, strategy: Strategy = Strategy.RANDOM) {

}

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
                Shape.values()[Random.nextInt(0, modulo)]
            }
        }
    }
}
