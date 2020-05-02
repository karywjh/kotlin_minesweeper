package com.example.sharedcode

class Engine {

    enum class GameState {
        kNotStarted,
        kPlaying,
        kWin,
        kLose,
    }

    val board = Board()
    var state = GameState.kNotStarted

    fun startGame(row: Int, col: Int): Boolean {
        if (this.board.cells[row][col].state != Cell.CellState.FLAGGED) {
            // Start game when first cell was opened
            this.board.GenerateBoard(Location(row, col))
            this.state = GameState.kPlaying
            return true
        }

        return false
    }

    fun openCell(row: Int, col: Int) {
        if (this.board.cells[row][col].state != Cell.CellState.FLAGGED) {
            this.board.cells[row][col].changeState(Cell.CellState.OPENED)
            this.board.nonMines.remove(Location(row, col))

            if (this.board.IsMine(Location(row, col))) {
                this.state = GameState.kLose
                println("LOSES!!!!!")
            }
        }

        if (this.board.nonMines.isEmpty()) {
            this.state = GameState.kWin
            println("WINS!!!")
        }
    }

    fun flagCell(row: Int, col: Int) {
        if (this.board.cells[row][col].state == Cell.CellState.FLAGGED) {
            this.board.cells[row][col].changeState(Cell.CellState.COVERED)
            this.board.mineCount++ // Total mine count increased by one
            println("unflagged")

        } else if (this.board.cells[row][col].state == Cell.CellState.COVERED) {
            this.board.cells[row][col].changeState(Cell.CellState.FLAGGED)
            this.board.mineCount-- // Total mine count decreased by one
            println("flagged")
        }
    }

}