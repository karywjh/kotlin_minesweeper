package com.example.sharedcode

import kotlin.random.Random

class Board {
    var width: Int = 9
    var height: Int = 9
    var mineCount: Int = 40
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

        val startNeighbors: MutableList<Location> = start.getNeighbors(this.height, this.width)
        startNeighbors.add(start)

        while (this.minesLoc.size < this.mineCount) {
            val loc: Location = Location(random.nextInt(0, this.height), random.nextInt(0, this.width))
            if (!startNeighbors.contains(loc)) {
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
                    val loc = Location(row, col);
                    this.cells[row][col].InitCell(CountSurroundingMines(loc), loc)
                    this.nonMines.add(loc)
                }
            }
        }
    }

    fun GenerateBoard(start: Location) {
        // Set start location to have value 0
        this.cells[start.row][start.col].InitCell(-1, start)

        // All cells that are neighbor to starting location can't be mines

        // Randomly Place Mines and fill in rest of the board
        GenerateMines(start)
        FillInValues()

    }

}