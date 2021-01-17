package twentytwenty.day3

import java.io.File
import java.io.InputStream

const val horizontalStep = 3

fun main() {
    val inputStream: InputStream =
        File("/Users/mykolamiroshnychenko/coding/studying/adventOfCode/src/main/kotlin/twentytwenty/day3/input.txt").inputStream()

    //build a complete "picture" first
    //figure out the "width" of the part by finding the next "\n" char
    //figure out the "height" of the part by counting the amount of \n chars

    val inputString = inputStream.bufferedReader().use { it.readText() }

    val height = inputString.count {
        it == '\n'
    }

    val width = inputString.indexOf("\n")
    val amountOfWidthIterationsTillNextNchar = width / horizontalStep
    val amountOfPartsWeHaveToGlueTogether = height / amountOfWidthIterationsTillNextNchar

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
    var horizontalPosition = 0

    completePicture
        .removeSuffix("\n")
        .split("\n")
        .forEach { line ->
            if (line[horizontalPosition] == '#') amountOfTrees++
            horizontalPosition += horizontalStep
        }

    //get the amount of iterations we can go based on the width of the part of map we have
    // divide the height by the amount of iterations we can go - and get the number of "parts" of map we have to add to get the "complete" picture
    // for each line - iterate till "\n" char and add the needed amount of lines based on the number above - don't forget to put the "\n char" at the end
}

