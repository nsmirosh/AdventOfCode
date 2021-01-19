package twentytwenty.otherExercise

import kotlin.random.Random


data class Word(val word: String, val isOperator: Boolean)

fun StringExpression(str: String): String {

    val stringToBeParsed = str

    val numbers = listOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    val operators = setOf("plus", "minus")

    val parsedWords = mutableListOf<Word>()

    var currentWordBeingProcessed = ""

    stringToBeParsed.forEach {
        currentWordBeingProcessed += it

        if (currentWordBeingProcessed in numbers || currentWordBeingProcessed in operators) {
            val isOperator = currentWordBeingProcessed in operators
            parsedWords += Word(currentWordBeingProcessed, isOperator)
            currentWordBeingProcessed = ""
        }
    }

    var currentString = ""
    val numbersAndOperatorsInterchanged = mutableListOf<String>()

    parsedWords.forEachIndexed { index, wordObject ->
        if (wordObject.word in numbers) {
            currentString += numbers.indexOf(wordObject.word)
            if (index == parsedWords.size.dec()) {
                numbersAndOperatorsInterchanged.add(currentString)
            }
        } else {
            numbersAndOperatorsInterchanged.add(currentString)
            numbersAndOperatorsInterchanged.add(wordObject.word)
            currentString = ""
        }
    }

    println("numbersAndOperatorsInterchanged = $numbersAndOperatorsInterchanged")

    var product = 0

    val (parsedOperators, parsedNumbers) = numbersAndOperatorsInterchanged.partition {
        it in operators
    }

    println("parsedNumbers = $parsedNumbers")
    println("parsedOperators = $parsedOperators")
    parsedNumbers.forEachIndexed { index, word ->
        val number = word.toInt()
        if (index == 0) {
            product += number
        } else {
            if (parsedOperators[index - 1] == operators.first()) {
                product += number
            }
            else {
                product -= number
            }
        }
    }

    println("product = $product")

    var output = ""

    if (product < 0) {
        output = "negative"
    }

    val listOfNums = mutableListOf<Int>()



    if (product > 0) {
        while (product > 0) {
            val moduloResult = product % 10
            println("moduloResult = $moduloResult")
            listOfNums.add(moduloResult)
            product /= 10
        }

    }

    else {
        do  {
            val moduloResult = product % 10
            println("moduloResult = $moduloResult")
            listOfNums.add(moduloResult)
            product /= 10

        } while(product.toString().length > 1)
    }
/*
    while (product > 0) {
        val moduloResult = product % 10
        println("moduloResult = $moduloResult")
        listOfNums.add(moduloResult)
        product /= 10
    }
*/

  /*  do  {
        val moduloResult = product % 10
        println("moduloResult = $moduloResult")
        listOfNums.add(moduloResult)
        product /= 10

    } while(product.toString().length > 1)*/
    listOfNums.reverse()

    println("listOfNums.reverse() = ${listOfNums}")


    Math.random().toInt()

    listOfNums.forEach {

        println("numbers[it] = ${numbers[Math.abs(it)]}")
        output += numbers[Math.abs(it)]
    }

    println("output = $output")


    //parse the parsed words
    //if the word "isOperator" cut off the previous word
    // add the operator as a separate String
    //we know for sure that the words and the operators interchange, so no need to validate that


    /*
    convert the numbers to a list of lists of numbers in the needed sequence
    convert the operators to a list of operators in the needed sequence
    interchange the numbers and the operators until we run out of numbers
     */
    return output;

}



val foo: Int
    get() = Random.nextInt()

fun main(args: Array<String>) {
    // The values should be different:
    println(foo)
    println(foo)
    println(foo)
}

/*
fun main() {
//    println(StringExpression("onetwominusthreefourpluseight"))
    println(StringExpression("onezeropluseight"))
    println(StringExpression("oneminusoneone"))
}*/
