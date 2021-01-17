package twentytwenty.day2.secondPart

import java.io.File
import java.io.InputStream

data class Password(val min: Int, val max: Int, val letter: Char, val password: String)

fun main() {

    val inputStream: InputStream =
        File("/Users/mykolamiroshnychenko/coding/studying/adventOfCode/src/main/kotlin/twentytwenty/day2/firstPart/passwordInput.txt").inputStream()

    var amountOfValidOnes = 0
    inputStream.bufferedReader().forEachLine {

        val list = it.split(" ")
        val charRangeList = list.first().split("-")

        val charToFind = list[1].removeSuffix(":")
        val password = list[2]


        val isFirstMatch = password[charRangeList.first().toInt().dec()] == charToFind.first()
        val isSecondMatch = password[charRangeList[1].toInt().dec()] == charToFind.first()

        if (listOf(isFirstMatch, isSecondMatch).count {it} == 1) amountOfValidOnes++

    }
    println("amountOfValidOnes = $amountOfValidOnes")
}

