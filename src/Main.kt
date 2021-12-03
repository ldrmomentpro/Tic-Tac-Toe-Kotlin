import java.lang.IndexOutOfBoundsException

fun main() {
    val mutList = mutableListOf(
        mutableListOf("X ", "O ", "X"),
        mutableListOf("O ", "X ", "O"),
        mutableListOf("X ", "X ", "O")
    )

    val inputStr = "         ".toCharArray()
    fill(mutList, inputStr)
    printTic(mutList)

    for (i in 1..9) {
        firstMove(mutList)
        printTic(mutList)
        if (i == 9 && printResult(mutList) == 0) {
            println("Draw")
        } else if (printResult(mutList) == 1) {
            break
        }
        secondMove(mutList)
        printTic(mutList)
        if (i == 9 && printResult(mutList) == 0) {
            println("Draw")
        } else if (printResult(mutList) == 1) {
            break
        }
    }
}

fun firstMove(mutList: MutableList<MutableList<String>>) {
    var coordinates = ""
    loop@ while (true) {
        print("Enter the coordinates: ")
        val input = readLine()!!.split(" ")
        try {
            coordinates = mutList[input[0].toInt() - 1][input[1].toInt() - 1]
            if (coordinates == " " && coordinates != "X" && coordinates != "O") {
                mutList[input[0].toInt() - 1][input[1].toInt() - 1] = "X"
                break@loop
            } else {
                println("This cell is occupied! Choose another one!")
            }
        } catch (e: NumberFormatException) {
            println("You should enter numbers!")
        } catch (e: IndexOutOfBoundsException) {
            println("Coordinates should be from 1 to 3!")
        }
    }
}

fun secondMove(mutList: MutableList<MutableList<String>>) {
    var coordinates = ""
    loop@ while (true) {
        print("Enter the coordinates: ")
        val input = readLine()!!.split(" ")
        try {
            coordinates = mutList[input[0].toInt() - 1][input[1].toInt() - 1]
            if (coordinates == " " && coordinates != "X" && coordinates != "O") {
                mutList[input[0].toInt() - 1][input[1].toInt() - 1] = "O"
                break@loop
            } else {
                println("This cell is occupied! Choose another one!")
            }
        } catch (e: NumberFormatException) {
            println("You should enter numbers!")
        } catch (e: IndexOutOfBoundsException) {
            println("Coordinates should be from 1 to 3!")
        }
    }
}

fun printTic(mutList: MutableList<MutableList<String>>) {
    println()
    println("---------")
    for (i in mutList.indices) {
        print("| ")
        for (j in mutList.indices) {
            print("${mutList[i][j]} ")
        }
        print("|")
        println()
    }
    println("---------")
}

fun fill(mutList: MutableList<MutableList<String>>, str: CharArray) {
    var counter = 0
    for (i in mutList.indices) {
        for (j in mutList.indices) {
            mutList[i][j] = str[counter].toString()
            counter++
        }
    }
}

fun printResult(mutList: MutableList<MutableList<String>>): Int {
    var withWin = 0
    var row1X = 0
    var row2X = 0
    var row3X = 0
    var row1O = 0
    var row2O = 0
    var row3O = 0
    var col1X = 0
    var col2X = 0
    var col3X = 0
    var col1O = 0
    var col2O = 0
    var col3O = 0
    for (j in mutList.indices) {
        when (mutList[0][j]) {
            "X" -> row1X++
            "O" -> row1O++
        }
        when (mutList[1][j]) {
            "X" -> row2X++
            "O" -> row2O++
        }
        when (mutList[2][j]) {
            "X" -> row3X++
            "O" -> row3O++
        }

        when (mutList[j][0]) {
            "X" -> col1X++
            "O" -> col1O++
        }
        when (mutList[j][1]) {
            "X" -> col2X++
            "O" -> col2O++
        }
        when (mutList[j][2]) {
            "X" -> col3X++
            "O" -> col3O++
        }
    }

    when {
        row1X == 3 || row2X == 3 || row3X == 3 || col1X == 3 || col2X == 3 || col3X == 3 -> {
            withWin = 1
            println("X wins")
        }
        row1O == 3 || row2O == 3 || row3O == 3 || col1O == 3 || col2O == 3 || col3O == 3 -> {
            withWin = 1
            println("O wins")
        }

        (mutList[0][0] == "X" && mutList[1][1] == "X" && mutList[2][2] == "X") ||
                (mutList[0][2] == "X" && mutList[1][1] == "X" && mutList[2][0] == "X") -> {
            withWin = 1
            println("X wins")
        }
        (mutList[0][0] == "O" && mutList[1][1] == "O" && mutList[2][2] == "O") ||
                (mutList[0][2] == "O" && mutList[1][1] == "O" && mutList[2][0] == "O") -> {
            withWin = 1
            println("O wins")
        }
    }

    return withWin
}