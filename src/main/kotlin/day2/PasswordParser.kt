package day2

import java.io.File
import java.io.InputStream

data class Password(val min: Int, val max: Int, val letter: Char, val password: String)

fun main() {

    val inputStream: InputStream =
        File("/Users/mykolamiroshnychenko/coding/studying/adventOfCode/src/main/kotlin/day2/passwordInput.txt").inputStream()

    var rangeOfChars = 0..1
    inputStream.bufferedReader().forEachLine {

        val list = it.split(" ")
        val totalChars = list[0].split("-")

        rangeOfChars = totalChars[0].toInt()..totalChars[1].toInt()

        val charToFind = list[1].removeSuffix(":")
        val password = list[2]


        println(it)

        val isValid = password.count {
            it.equals(charToFind.toCharArray())
        } in rangeOfChars

        println(" rangeOfChars = $rangeOfChars, charToFind = $charToFind, password = $password, isValid = $isValid")
    }
}

