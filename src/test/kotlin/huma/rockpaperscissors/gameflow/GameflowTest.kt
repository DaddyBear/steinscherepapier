package huma.rockpaperscissors.gameflow

import huma.rockpaperscissors.GameParameter
import huma.rockpaperscissors.Player
import huma.rockpaperscissors.Shape
import huma.rockpaperscissors.Strategy
import huma.rockpaperscissors.asciiart.GREATER
import huma.rockpaperscissors.asciiart.LOWER
import huma.rockpaperscissors.asciiart.NULL
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.assertj.core.api.Assertions.assertThat
import org.junit.runner.RunWith
import java.io.ByteArrayInputStream
import kotlin.test.Test

private const val ROCK: String = "ROCK"
private const val PAPER: String = "PAPER"
private const val SCISSORS: String = "SCISSORS"

@RunWith(JUnitParamsRunner::class)
class GameflowTest {

    @Test
    @Parameters(ROCK, SCISSORS, PAPER)
    fun `check match result draw`(shapeString: String) {
        // Given
        val shape = Shape.valueOf(shapeString)

        // When
        val result = checkMatchResult(shape, ONE, shape, TWO)

        // Then
        assertThat(result.first).isEqualTo(DRAW)
        assertThat(result.second).isEqualTo(NULL)
    }

    @Test
    @Parameters(
        "$ROCK, $PAPER, $TWO",
        "$ROCK, $SCISSORS, $ONE",
        "$PAPER, $ROCK, $ONE",
        "$PAPER, $SCISSORS, $TWO",
        "$SCISSORS, $ROCK, $TWO",
        "$SCISSORS, $PAPER, $ONE"
    )
    fun `check match results win`(p1ShapeString: String, p2ShapeString: String, expectedName: String) {
        // Given
        val p1Shape = Shape.valueOf(p1ShapeString)
        val p2Shape = Shape.valueOf(p2ShapeString)
        val expectedSymbol = if (expectedName == ONE) {
            GREATER
        } else {
            LOWER
        }

        // When
        val result = checkMatchResult(p1Shape, ONE, p2Shape, TWO)

        // Then
        assertThat(result.first).isEqualTo(expectedName)
        assertThat(result.second).isEqualTo(expectedSymbol)
    }

    @Test
    @Parameters(
        "5, One, r, RANDOM, Two, o, ROCK_ONLY",
        "111, Sepp, X, SCISSORS_ONLY, Hans, P, PAPER_ONLY"
    )
    fun `check readGameParameters`(
        rounds: Int,
        p1Name: String,
        inP1StrategyString: String,
        p1StrategyString: String,
        p2Name: String,
        inP2StrategyString: String,
        p2StrategyString: String
    ) {
        // Given
        val p1Strategy = Strategy.valueOf(p1StrategyString)
        val p2Strategy = Strategy.valueOf(p2StrategyString)
        val input = "$rounds\n$p1Name\n$inP1StrategyString\n$p2Name\n$inP2StrategyString"
        val testSysIn = ByteArrayInputStream(input.toByteArray())
        System.setIn(testSysIn)

        // When
        val parameter = readGameParameters()

        // Then
        assertThat(parameter.rounds).isEqualTo(rounds)
        assertThat(parameter.player1.name).isEqualTo(p1Name)
        assertThat(parameter.player1.strategy).isEqualTo(p1Strategy)
        assertThat(parameter.player2.name).isEqualTo(p2Name)
        assertThat(parameter.player2.strategy).isEqualTo(p2Strategy)
    }

    @Test
    @Parameters(
        "ROCK_ONLY, PAPER_ONLY, $TWO",
        "SCISSORS_ONLY, PAPER_ONLY, $ONE",
        "SCISSORS_ONLY, SCISSORS_ONLY, $DRAW"
    )
    fun `check match result is correct`(p1StrategyString: String, p2StrategyString: String, winner: String) {
        // Given
        val round = 66
        val p1Strategy = Strategy.valueOf(p1StrategyString)
        val p2Strategy = Strategy.valueOf(p2StrategyString)

        val params = GameParameter(100_000, Player(ONE, p1Strategy), Player(TWO, p2Strategy))

        // When
        val match = playMatch(round, params)

        // Then
        assertThat(match.round).isEqualTo(round)
        assertThat(match.player1Shape).isEqualTo(params.player1.strategy.select())
        assertThat(match.player2Shape).isEqualTo(params.player2.strategy.select())
        assertThat(match.winner).isEqualTo(winner)
    }

    @Test
    fun `run matches and check result`() {
        // Given
        val rounds = 2
        val gameParameter = GameParameter(rounds, Player(ONE, Strategy.ROCK_ONLY), Player(TWO, Strategy.PAPER_ONLY))

        // When
        val matchResults = runMatches(gameParameter)

        // Then
        assertThat(matchResults).hasSize(rounds)
        for (i in 0 until rounds) {
            assertThat(matchResults[i].round).isEqualTo(i + 1)
            assertThat(matchResults[i].winner).isEqualTo(TWO)
            assertThat(matchResults[i].player1Shape).isEqualTo(Shape.ROCK)
            assertThat(matchResults[i].player2Shape).isEqualTo(Shape.PAPER)
        }
    }
}
