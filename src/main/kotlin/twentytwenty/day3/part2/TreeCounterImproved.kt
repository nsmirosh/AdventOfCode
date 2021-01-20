package twentytwenty.day3.part2

import java.io.File
import java.io.InputStream

fun main() {


  /*  val startTime = System.currentTimeMillis()
    println(" result = ${
        setOf(Pair(1, 1), Pair(3, 1), Pair(5, 1), Pair(7, 1), Pair(1, 2))
            .map { traverseInPattern(it.first, it.second).toLong() }
            .reduce { accumulatedBefore, currentNumber -> accumulatedBefore * currentNumber }
    }"
    )

    println("totalTimeV1 = ${System.currentTimeMillis() - startTime}")*/


    val startTime1 = System.currentTimeMillis()

    println(" result = ${
        setOf(Pair(1, 1), Pair(3, 1), Pair(5, 1), Pair(7, 1), Pair(1, 2))
            .map { traverseInPatternV2(it.first, it.second).toLong() }
            .reduce { accumulatedBefore, currentNumber -> accumulatedBefore * currentNumber }
    }"
    )

    println("totalTimeV2 = ${System.currentTimeMillis() - startTime1}")

}

fun traverseInPattern(horizontalStep: Int, verticalStep: Int): Int {

    val startTime = System.currentTimeMillis()
    val inputStream: InputStream =
        File("/Users/mykolamiroshnychenko/coding/studying/adventOfCode/src/main/kotlin/twentytwenty/day3/input.txt").inputStream()

    //build a complete "picture" first
    //figure out the "width" of the part by finding the next "\n" char
    //figure out the "height" of the part by counting the amount of \n chars
    //get the amount of iterations we can go based on the width of the part of map we have
    // divide the height by the amount of iterations we can go - and get the number of "parts" of map we have to add to get the "complete" picture
    // for each line - iterate till "\n" char and add the needed amount of lines based on the number above - don't forget to put the "\n char" at the end


    val inputString = inputStream.bufferedReader().use { it.readText() }

    val height = inputString.count {
        it == '\n'
    }

    val width = inputString.indexOf("\n")
    val amountOfWidthIterationsTillNextNchar = width / horizontalStep

    val amountOfPartsWeHaveToGlueTogether = height / amountOfWidthIterationsTillNextNchar + 1

    var completePicture = ""

    val inputStream2: InputStream =
        File("/Users/mykolamiroshnychenko/coding/studying/adventOfCode/src/main/kotlin/twentytwenty/day3/input.txt").inputStream()

    inputStream2.bufferedReader().forEachLine { line ->
        repeat(amountOfPartsWeHaveToGlueTogether) {
            completePicture += line.removeSuffix("\n")
        }
        completePicture += "\n"
    }

    var amountOfTrees = 0
    var horizontalPos = 0

    val pictureSplitByLines = completePicture
        .removeSuffix("\n")
        .split("\n")

    for (verticalPos in 0..height step verticalStep) {
        val horizontalLine = pictureSplitByLines[verticalPos]
        if (horizontalLine[horizontalPos] == '#') amountOfTrees++
        horizontalPos += horizontalStep
    }


    val endTime = System.currentTimeMillis()
    println("totalTime = ${endTime - startTime}")

    return amountOfTrees

}


fun traverseInPatternV2(horizontalStep: Int, verticalStep: Int): Int {
    val startTime = System.currentTimeMillis()
    val inputStream: InputStream =
        File("/Users/mykolamiroshnychenko/coding/studying/adventOfCode/src/main/kotlin/twentytwenty/day3/input.txt").inputStream()

    val inputString = inputStream.bufferedReader().use { it.readText() }

    val height = inputString.count {
        it == '\n'
    }

    val width = inputString.indexOf("\n")
    val amountOfWidthIterationsTillNextNchar = width / horizontalStep
    val amountOfPartsWeHaveToGlueTogether = height / amountOfWidthIterationsTillNextNchar + 1

    val completePicture = mutableListOf<String>()
    val inputStringSplitByLines = inputString.split("\n")


    //TODO write an article about this optimization - the time optimized by adding to a list instead of string concatenation is HUGE
    inputStringSplitByLines.forEachIndexed { index, line ->
        var lineToAdd = ""
        repeat(amountOfPartsWeHaveToGlueTogether) {
            lineToAdd += line.removeSuffix("\n")
        }
        if (index != inputStringSplitByLines.size) {
            lineToAdd += "\n"
        }
        completePicture.add(lineToAdd)
    }

    var amountOfTrees = 0
    var horizontalPos = 0

    for (verticalPos in 0..height step verticalStep) {
        val horizontalLine = completePicture[verticalPos]
        if (horizontalLine[horizontalPos] == '#') amountOfTrees++
        horizontalPos += horizontalStep
    }

    val endTime = System.currentTimeMillis()
    println("totalTime = ${endTime - startTime}")

    return amountOfTrees

}





