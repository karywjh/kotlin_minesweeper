package com.example.sharedcode

class Cell (val row: Int, val col: Int){
    enum class CellState {
        COVERED,
        FLAGGED,
        OPENED,
    }

    var value: Int = 0
    var imageFile: String = "@drawable/facing_down"
    var state: CellState = CellState.COVERED
    var location: Location = Location(row, col)

    fun InitCell(value: Int, loc: Location) {
        this.value = value;
        this.location = loc;
    }

    fun changeState(new_state: CellState) {
        this.state = new_state
        // change image corresponding to new state
        if (new_state == CellState.COVERED) {
            this.imageFile = "@drawable/facing_down"
        } else if (new_state == CellState.FLAGGED) {
            this.imageFile = "@drawable/flagged"
        } else if (new_state == CellState.OPENED) {
            // Display bomb image if value is -1 (cell is bomb)
            if (this.value < 0) {
                this.imageFile = "@drawable/bomb"
            } else {
                this.imageFile = "@drawable/" + value.toString()
            }
        }
    }
}