package tictactoe

class TicTacToe(gridSize: Int) {
    private val grid: MutableList<MutableList<Char>> = ArrayList()
    private var spaces = ""
    private var symbol = 'X'
    private var isGameOver = false

    init {
        repeat(gridSize) { spaces += " " }
        for (i in 0 until gridSize) grid.add(spaces.toMutableList())
        gridPrint(gridSize)

        while (!isGameOver) {
            while (!isInputValid(readln(), gridSize)) continue
            gridPrint(gridSize)

            if (isGameWon(symbol)) {
                isGameOver = true
                println("$symbol wins")
            } else if (isGameDrawn()) {
                isGameOver = true
                println("Draw")
            } else {
                if (symbol == 'X') symbol = 'O' else if (symbol == 'O') symbol = 'X'
                continue
            }
        }
    }

    private fun isGameWon(symbol: Char) : Boolean {
        for (i in 0..grid.lastIndex) {
            for (ch in 0..grid.lastIndex - 2) {
                if (symbol == grid[i][ch] && symbol == grid[i][ch + 1] && symbol == grid[i][ch + 2]) return true
            }
        }

        for (ch in 0..grid.lastIndex) {
            for (i in 0..grid.lastIndex - 2) {
                if (symbol == grid[i][ch] && symbol == grid[i + 1][ch] && symbol == grid[i + 2][ch]) return true
            }
        }

        for (i in 0..grid.lastIndex - 2) {
            for (ch in 0..grid.lastIndex - 2) {
                if (symbol == grid[i][ch] && symbol == grid[i + 1][ch + 1] && symbol == grid[i + 2][ch + 2]) return true
            }
        }
        for (i in 0..grid.lastIndex - 2) {
            for (ch in grid.lastIndex downTo 0 + 2) {
                if (symbol == grid[i][ch] && symbol == grid[i + 1][ch - 1] && symbol == grid[i + 2][ch - 2]) return true
            }
        }
        return false
    }

    private fun isGameDrawn() : Boolean {
        for (i in 0..grid.lastIndex) {
            for (ch in 0..grid.lastIndex) {
                if (grid[i][ch] != 'X' && grid[i][ch] != 'O') return false
            }
        }
        return true
    }

    private fun isInputValid(str: String, grizSize: Int) : Boolean {
        val regex = Regex("^\\d+ \\d+\$")
        if (!str.matches(regex)) {
            println("You should enter numbers!")
            return false
        }

        val strFirstInt = str.substring(0, str.indexOfFirst { !it.isDigit() }).toInt()
        val strLastInt = str.last().digitToInt()

        if (strFirstInt !in 1..grizSize ||
            strLastInt !in 1..grizSize) {
            println("Coordinates should be from 1 to 3!")
            return false
        } else if ('X' == grid[strFirstInt - 1][strLastInt - 1] ||
            'O' == grid[strFirstInt - 1][strLastInt - 1]) {
            println("This cell is occupied! Choose another one!")
            return false
        }

        grid[strFirstInt - 1][strLastInt - 1] = symbol
        return true
    }

    private fun gridPrint(grizSize: Int) {
        println("---------")
        for (i in 0 until grizSize) println('|' + " " + grid[i].joinToString(" ") + " " + '|')
        println("---------")
    }
}

fun main() {
    TicTacToe(3)
}