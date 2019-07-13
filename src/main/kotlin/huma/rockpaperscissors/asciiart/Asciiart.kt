package huma.rockpaperscissors.asciiart

val ROCK: String = """
    |                  
    |     _______      
    | ---'   ____)     
    |       (_____)    
    |       (_____)    
    |       (____)     
    | ---.__(___)      
    |                  
""".trimMargin()

val PAPER: String = """
    |                    
    |     _______        
    | ---'   ____)____   
    |           ______)  
    |           _______) 
    |          _______)  
    | ---.__________)    
    |                    
""".trimMargin()

val SCISSORS: String = """
    |                    
    |     _______        
    | ---'   ____)____   
    |           ______)  
    |        __________) 
    |       (____)       
    | ---.__(___)        
    |                    
""".trimMargin()

val ROCK_PAPER_SCISSOR = combineMultilineStrings(ROCK, PAPER, SCISSORS)

val SEPARATOR = """
    |>< >< >< >< >< >< >< >< >< >< >< >< >< >< >< >< >< >< >< ><
""".trimMargin()

val SHIT = """
    |      _       _   
    |     | |   (_) |  
    |  ___| |__  _| |_ 
    | / __| '_ \| | __|
    | \__ \ | | | | |_ 
    | |___/_| |_|_|\__|
    |                  
""".trimMargin()

/**
 * Combines various multiline strings in that way that all lines are concatenated one by one with the others.
 * So ascii arts can be displayed side by side.
 *
 * Attention: Please take care that all given ascii arts have same number of lines else you wouldn't be happy with
 * the result.
 *
 * Example:
 * -----      -----       --------
 * | A1 |     | B1 |     | A1  B1 |
 * | A2 |  +  | B2 |  =  | A2  B2 |
 * | A3 |     | B3 |     | A3  B3 |
 * -----       -----     ---------
 *
 * @param asciiArtStrings Variable amount of multiline ascii art strings
 * @return Combined ascii art string with the given ones side by side
 */
fun combineMultilineStrings(vararg asciiArtStrings: String): String {
    val resultStringList = mutableListOf<String>()
    asciiArtStrings.forEach { asciiArt ->
        var i = 0
        asciiArt.split("\n").forEach {
            if (resultStringList.size > i) {
                resultStringList[i] += it
            } else {
                resultStringList.add(it)
            }
            i++
        }
    }

    return resultStringList.joinToString(separator = "\n")
}
