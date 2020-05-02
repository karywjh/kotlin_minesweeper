package com.example.sharedcode

class Cell (row: Int, col: Int){
    enum class CellState {
        COVERED,
        FLAGGED,
        OPENED,
    }

    var value: Int = 0
    var imageFile = R.drawable.facing_down
    var state: CellState = CellState.COVERED
    var location: Location = Location(row, col)

    fun InitCell(value: Int, loc: Location) {
        this.value = value
        this.location = loc
    }

    fun changeState(new_state: CellState) {
        this.state = new_state

        // change image corresponding to new state
        if (new_state == CellState.COVERED) {
            this.imageFile = R.drawable.facing_down
        } else if (new_state == CellState.FLAGGED) {
            this.imageFile = R.drawable.flagged
        } else if (new_state == CellState.OPENED) {
            // Display bomb image if value is -1 (cell is bomb)
            this.imageFile = GetImage(this.value)
        }
    }

    fun GetImage(value: Int): Int = when (value) {
        -1 -> R.drawable.bomb
        0 -> R.drawable.cell0
        1 -> R.drawable.cell1
        2 -> R.drawable.cell2
        3 -> R.drawable.cell3
        4 -> R.drawable.cell4
        5 -> R.drawable.cell5
        6 -> R.drawable.cell6
        7 -> R.drawable.cell7
        8 -> R.drawable.cell8
        else -> {
            R.mipmap.ic_launcher
        }
    }
}