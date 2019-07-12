package huma.rockpaperscissors.utils

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class CommonsTest {

    @Test
    fun `check range returns expected`() {
        // Given
        val range = IntRange(0, 2)

        // When Then
        assertThat((-1).getValueWhenInRangeElseNull(range)).isNull()
        assertThat(0.getValueWhenInRangeElseNull(range)).isEqualTo(0)
        assertThat(1.getValueWhenInRangeElseNull(range)).isEqualTo(1)
        assertThat(2.getValueWhenInRangeElseNull(range)).isEqualTo(2)
        assertThat(3.getValueWhenInRangeElseNull(range)).isNull()
    }
}
