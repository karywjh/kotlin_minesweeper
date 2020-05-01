package com.example.sharedcode

import java.sql.Time
import kotlin.random.Random

class Board {
    var width: Int = 9
    var height: Int = 9
    var mineCount: Int = 40
    var id: Int = Random.nextInt()
    var cells: List<List<Cell>> = List(height) { i -> List(width) { j -> Cell(i, j) } }


    fun InitBoard(width: Int, height: Int, mines: Int) {
        this.width = width;
        this.height = height;
        this.mineCount = mines;

        this.cells = List(this.height) { i -> List(this.width) { j -> Cell(i, j) } }
    }


//    fun GenerateMines(start: Location) : MutableList<Location> {
//        mineLocs: MutableList<Location> =
//    }
//    fun InitProp(int )
}