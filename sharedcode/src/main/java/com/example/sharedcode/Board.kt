package com.example.sharedcode

import kotlin.random.Random

class Board {
    var width: Int = 13
    var height: Int = 23
    var mineCount: Int = 60
    var id: Int = Random.nextInt()
    var cells: List<List<Cell>> = List(height) { i -> List(width) { j -> Cell(i, j) } }
    var minesLoc: MutableSet<Location> = mutableSetOf()
    var nonMines: MutableSet<Location> = mutableSetOf()

    fun InitBoard(width: Int, height: Int, mines: Int) {
        this.width = width;
        this.height = height;
        this.mineCount = mines;

        this.cells = List(this.height) { i -> List(this.width) { j -> Cell(i, j) } }
    }

    fun IsMine(loc: Location): Boolean {
        return this.cells[loc.row][loc.col].value < 0
    }

    fun CountSurroundingMines(location: Location): Int {
        var mines = 0

        location.getNeighbors(this.height, this.width).iterator().forEach {
            if (IsMine(it)) {
                mines++
            }
        }

        return mines
    }

    fun GenerateMines(start: Location) {
        val random = Random(this.id)

        while (this.minesLoc.size < this.mineCount) {
            val loc = Location(random.nextInt(0, this.height), random.nextInt(0, this.width))

            if (!this.nonMines.contains(loc)) {
                this.minesLoc.add(loc)
            }
        }

        // Initiate mine-cells
        this.minesLoc.iterator().forEach {
            this.cells[it.row][it.col].InitCell(-1, it)
        }
    }

    fun FillInValues() {
        for (row in 0 until this.height) {
            for (col in 0 until this.width) {
                if (this.cells[row][col].value >= 0) {
                    val location = Location(row, col)
                    this.cells[row][col].InitCell(CountSurroundingMines(location), location)
                    this.nonMines.add(location)
                }
            }
        }
    }

    fun GenerateBoard(start: Location) {
        // Set start location to have value 0
        this.cells[start.row][start.col].InitCell(0, start)

        // All cells that are neighbor to starting location can't be mines
        this.nonMines = start.getNeighbors(this.height, this.width)
        this.nonMines.add(start)

        // Randomly Place Mines and fill in rest of the board
        GenerateMines(start)
        FillInValues()

    }

    fun getLocation(index: Int): Location {
        val row = index / this.width
        val col = index % this.width

        return Location(row, col)
    }

    fun openZeroNeighbors(location: Location) {
        if (this.cells[location.row][location.col].value == 0) {
            for (loc in location.getNeighbors(this.height, this.width)) {

                if (this.cells[loc.row][loc.col].state != Cell.CellState.OPENED) {
                    this.cells[loc.row][loc.col].changeState(Cell.CellState.OPENED)
                    this.nonMines.remove(loc)

                    // Recursion to open neighbors of 0 cells if they are being opened
                    openZeroNeighbors(loc)
                }
            }
        }
    }

    fun openNeighbors(location: Location) {
        if (this.cells[location.row][location.col].realValue == 0) {
            for (loc in location.getNeighbors(this.height, this.width)) {


                if (!IsMine(loc)) {
                    this.cells[loc.row][loc.col].changeState(Cell.CellState.OPENED)
                    this.nonMines.remove(loc)

                    // Recursion to open neighbors of 0 cells if they are being opened
                    openZeroNeighbors(loc)
                }
            }
        }
    }

    fun changeNeighborVals(location: Location, value: Int) {
        for (loc in location.getNeighbors(this.height, this.width)) {
            if (!IsMine(loc)) {
                this.cells[loc.row][loc.col].realValue += value
            }
        }
    }

    fun updateNeighbor(location: Location, to_flag: Boolean) {
        val row = location.row
        val col = location.col

        if (IsMine(location)) {
            // Flagged/Unflagged mine cell
            if (to_flag) {
                changeNeighborVals(location, -1)
            } else {
                changeNeighborVals(location, 1)
            }
        } else {
            // Flagged/Unflagged non-mine cell
            if (to_flag) {
                changeNeighborVals(location, -10)
            } else {
                changeNeighborVals(location, 10)
            }
        }
    }
}