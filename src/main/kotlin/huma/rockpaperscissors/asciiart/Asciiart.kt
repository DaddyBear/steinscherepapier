package huma.rockpaperscissors.asciiart

val ROCK: String = """
    |     _______      
    | ---'   ____)     
    |       (_____)    
    |       (_____)    
    |       (____)     
    | ---.__(___)      
    |                  
""".trimMargin()

val PAPER: String = """
    |     _______        
    | ---'   ____)____   
    |           ______)  
    |           _______) 
    |          _______)  
    | ---.__________)    
    |                    
""".trimMargin()

val SCISSORS: String = """
    |     _______        
    | ---'   ____)____   
    |           ______)  
    |        __________) 
    |       (____)       
    | ---.__(___)        
    |                    
""".trimMargin()

val ROCK_PAPER_SCISSOR = combineMultilineStrings(ROCK, PAPER, SCISSORS)

fun combineMultilineStrings(rock: String, paper: String, scissors: String): String {
    return """
    |      _       _   
    |     | |   (_) |  
    |  ___| |__  _| |_ 
    | / __| '_ \| | __|
    | \__ \ | | | | |_ 
    | |___/_| |_|_|\__|
    |                  
""".trimMargin()
}
