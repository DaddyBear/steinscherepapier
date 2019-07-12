package huma.rockpaperscissors.gameflow

import huma.rockpaperscissors.asciiart.ROCK_PAPER_SCISSOR
import huma.rockpaperscissors.asciiart.SEPARATOR

fun introduction() {
    output("Hello to rock paper scissors on console")
    output(ROCK_PAPER_SCISSOR)
    output(SEPARATOR)
}

fun playMatch() {

}

fun stopGame() {
    output("Good buy")
}

fun output(text: String) {
    println(text)
}
