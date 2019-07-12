package huma.rockpaperscissors.utils

import huma.rockpaperscissors.gameflow.output

// Extension function
/**
 * Check if the int value is included to the range, if it is return this else give a message and return null.
 *
 * @return the current value if it's inside the range, else null
 */
fun Int.getValueWhenInRangeElseNull(range: IntRange) =
    if (this in range) {
        this
    } else {
        output("The given number [$this] is not in range, please try again.")
        null
    }
