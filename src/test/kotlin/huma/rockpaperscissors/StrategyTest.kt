package huma.rockpaperscissors

import huma.rockpaperscissors.Strategy.PAPER_ONLY
import huma.rockpaperscissors.Strategy.ROCK_ONLY
import huma.rockpaperscissors.Strategy.SCISSORS_ONLY
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class StrategyTest {

    @Test
    fun `check random is random`() {
        // Given
        val shapeCounter = mutableMapOf<Shape, Int>()
        val strategy = Strategy.RANDOM
        val trials = 10_000

        // When
        for (i in 1..trials) {
            val shape = strategy.select()
            val nextCount = (shapeCounter[shape]) ?: 0
            shapeCounter[shape] = nextCount + 1
        }
        println(shapeCounter)

        // Then
        assertThat(trials).isEqualTo(shapeCounter.values.sum()) // Right amount of trials
        assertThat(shapeCounter[Shape.ROCK]).isGreaterThan((trials * 0.3).toInt())
        assertThat(shapeCounter[Shape.PAPER]).isGreaterThan((trials * 0.3).toInt())
        assertThat(shapeCounter[Shape.SCISSORS]).isGreaterThan((trials * 0.3).toInt())
    }

    @Test
    fun `test fix strategy only return this`() {
        // Given
        val trials = 1_000
        for (strategy in listOf(ROCK_ONLY, PAPER_ONLY, SCISSORS_ONLY)) {
            val shapeCounter = mutableMapOf<Shape, Int>()

            // When
            for (i in 1..trials) {
                val shape = strategy.select()
                val nextCount = (shapeCounter[shape]) ?: 0
                shapeCounter[shape] = nextCount + 1
            }
            println(shapeCounter)

            // Then
            assertThat(trials).isEqualTo(shapeCounter.values.sum()) // Right amount of trials
            assertThat(shapeCounter.size).isEqualTo(1)
            assertThat(shapeCounter.keys.first()).isEqualTo(
                when (strategy) {
                    ROCK_ONLY -> Shape.ROCK
                    PAPER_ONLY -> Shape.PAPER
                    SCISSORS_ONLY -> Shape.SCISSORS
                    else -> throw IllegalArgumentException("That's not part of the test")
                }
            )
        }
    }
}
