package twentytwenty.day2.firstPart

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

        val rangeOfChars = charRangeList.first().toInt()..charRangeList[1].toInt()

        val charToFind = list[1].removeSuffix(":")
        val password = list[2]

        if (password.count {
                it.equals(charToFind.first())
            } in rangeOfChars) amountOfValidOnes++

    }
    println("amountOfValidOnes = $amountOfValidOnes")
}

