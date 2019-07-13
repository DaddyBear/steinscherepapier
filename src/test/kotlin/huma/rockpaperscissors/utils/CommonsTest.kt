package huma.rockpaperscissors.utils

import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.assertj.core.api.Assertions.assertThat
import org.junit.runner.RunWith
import java.io.ByteArrayInputStream
import kotlin.test.Test
import kotlin.test.fail

@RunWith(JUnitParamsRunner::class)
class CommonsTest {

    @Test
    fun `check range returns only expected int values`() {
        // Given
        val range = IntRange(0, 2)

        // When Then
        assertThat((-1).getValueWhenInRangeElseNull(range)).isNull()
        assertThat(0.getValueWhenInRangeElseNull(range)).isEqualTo(0)
        assertThat(1.getValueWhenInRangeElseNull(range)).isEqualTo(1)
        assertThat(2.getValueWhenInRangeElseNull(range)).isEqualTo(2)
        assertThat(3.getValueWhenInRangeElseNull(range)).isNull()
    }

    @Test
    fun `check range returns string with correct length`() {
        // Given
        val stringLengthRange = IntRange(2, 4)

        // When Then
        assertThat("".getValueWhenInRangeElseNull(stringLengthRange)).isNull()
        assertThat("a".getValueWhenInRangeElseNull(stringLengthRange)).isNull()
        assertThat("ab".getValueWhenInRangeElseNull(stringLengthRange)).isEqualTo("ab")
        assertThat("abc".getValueWhenInRangeElseNull(stringLengthRange)).isEqualTo("abc")
        assertThat("abcd".getValueWhenInRangeElseNull(stringLengthRange)).isEqualTo("abcd")
        assertThat("abcde".getValueWhenInRangeElseNull(stringLengthRange)).isNull()
    }

    @Test
    @Parameters("1, 1", "2, 2")
    fun `check read user input int works with correct input`(input: String, expected: Int) {
        // Given
        val testSysIn = ByteArrayInputStream(input.toByteArray())
        System.setIn(testSysIn)

        // When
        val result = readUserInputInt(1..2, "Test user input.")

        // Then
        assertThat(result).isEqualTo(expected)
    }

    @Test(expected = NullPointerException::class) // Cause first input was not correct second input is empty by default
    @Parameters("False", "-1")
    fun `check read user input int fails with wrong input`(input: String) {
        // Given
        val textSysIn = ByteArrayInputStream(input.toByteArray())
        System.setIn(textSysIn)

        // When
        readUserInputInt(1..2, "Test user input.")

        // Then
        fail("Exception is expected when reading null as second input")
    }

    @Test
    @Parameters("HAL", "Hello", "Four", "123", "()+")
    fun `check read user input text works with correct input`(input: String) {
        // Given
        val testSysIn = ByteArrayInputStream(input.toByteArray())
        System.setIn(testSysIn)

        // When
        val result = readUserInputString(3..5, "Test user input text.")

        // Then
        assertThat(result).isEqualTo(input)
    }

    @Test(expected = NullPointerException::class) // Cause first input was not correct second input is empty by default
    @Parameters("", "Oh", "TooLong")
    fun `check read user input text fails with wrong input`(input: String) {
        // Given
        val testSysIn = ByteArrayInputStream(input.toByteArray())
        System.setIn(testSysIn)

        // When
        readUserInputString(3..5, "Test user input text.")

        // Then
        fail("Exception is expected when reading null as second input")
    }
}
