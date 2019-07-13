package huma.rockpaperscissors.utils

import huma.rockpaperscissors.gameflow.output

/**
 * Check if the int value is included to the range. If *true* return *this* else give a message and return *null*.
 *
 * @param range Range the value must fit into
 * @return the current value if it's inside the range, else null
 */
fun Int.getValueWhenInRangeElseNull(range: IntRange) =
    getValueWhenInRangeElseNull("number", this, this in range)

/**
 * Check if the string size is in given range. If *true* return *this* else give a message and return *null*.
 *
 * @param range Range the length must fit into
 * @return the current value if string length is in range, else null
 */
fun String.getValueWhenInRangeElseNull(range: IntRange) =
    getValueWhenInRangeElseNull("text", this, this.length in range)

/**
 * Check if [isInRange] is *true* then return [value], else give a message and return *null*.
 *
 * @param type Description of the type [T] for the message
 * @param value Value to return if [isInRange] is *true*
 * @param isInRange Result of the check if [value] is in range
 * @return [value] if [isInRange] is *true*, else *null*
 */
private fun <T> getValueWhenInRangeElseNull(type: String, value: T, isInRange: Boolean): T? {
    return if (isInRange) {
        value
    } else {
        output("The given $type [$value] is not in range, please try again.")
        null
    }
}

fun readUserInputInt(range: IntRange, question: String): Int {
    val rangeString = "Insert a number between ${range.first} and ${range.last}"

    var input: Int? = null
    while (input == null) {
        output("$question $rangeString")
        try {
            input = readLine()!!.toInt().getValueWhenInRangeElseNull(range)
        } catch (e: NumberFormatException) {
            output("Sorry that was not a correct number, please try again.")
        }
    }
    return input
}

fun readUserInputString(range: IntRange, question: String): String {
    val rangeString = "Insert a text between ${range.first} and ${range.last} characters."

    var input: String? = null
    while (input == null) {
        output("$question $rangeString")
        input = readLine()!!.getValueWhenInRangeElseNull(range)
    }

    return input
}
