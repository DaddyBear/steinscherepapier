package huma.rockpaperscissors.asciiart

import org.assertj.core.api.Assertions
import kotlin.test.Test

class AsciiArtTest {
    private val s2 = """
            | B1 
            | B2 
            | B3 
        """.trimMargin()
    private val s1 = """
            | A1 
            | A2 
            | A3 
        """.trimMargin()

    @Test
    fun `check simple combination of multiline strings`() {
        val expected = """
            | A1  B1 
            | A2  B2 
            | A3  B3 
        """.trimMargin()

        // When
        val result = combineMultilineStrings(s1, s2)

        // Then
        Assertions.assertThat(result).isEqualTo(expected)
    }
}
