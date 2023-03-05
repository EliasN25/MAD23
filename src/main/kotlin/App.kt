import kotlin.random.Random

fun main() {
    val number = generateRandomNumber()
    var guess: Int
    do {
        println("Enter your guess:")
        guess = readLine()?.toIntOrNull() ?: 0
        if (guess != 0 && isValidGuess(guess)) {
            val result = checkGuess(number, guess)
            println("${result.first}:${result.second}")
        } else {
            println("Invalid guess, try again.")
        }
    } while (guess != number)
    println("Congratulations you guessed the number!")
}

// Generate Random Number that does not contain repeating digits
fun generateRandomNumber(): Int {
    var number = Random.nextInt(1000, 10000)
    while (!isValidNumber(number)) {
        number = Random.nextInt(1000, 10000)
    }
    return number
}

// checks if a number is valid (does not have repeating digits)
fun isValidNumber(number: Int): Boolean {
    val digits = number.toString().toCharArray()
    return digits.distinct().size == digits.size
}

// checks if the number you put in is valid
fun isValidGuess(guess: Int): Boolean {
    val digits = guess.toString().toCharArray()
    return digits.distinct().size == digits.size && digits.size == 4
}

// checks if the input matches the random number
fun checkGuess(number: Int, guess: Int): Pair<Int, Int> {
    var n = 0
    var m = 0
    val digits = guess.toString().toCharArray()
    for (i in digits.indices) {
        if (digits[i] == number.toString()[i]) {
            m++
        }
        if (number.toString().contains(digits[i])) {
            n++
        }
    }
    n -= m
    return Pair(n, m)
}
