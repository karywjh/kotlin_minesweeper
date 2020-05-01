package com.example.sharedcode

data class Location(val row: Int, val col: Int) {

    fun getNeighbors(height: Int, width: Int): MutableList<Location> {
        val up: Location = this + Location(-1, 0)
        val down: Location = this + Location(1, 0)
        val right: Location = this + Location(0, 1)
        val left: Location = this + Location(0, -1)
        val upRight: Location = this + Location(-1, 1)
        val upLeft: Location = this + Location(-1, -1)
        val downRight: Location = this + Location(1, 1)
        val downLeft: Location = this + Location(1, -1)

        val locList: MutableList<Location> = mutableListOf<Location>()

        if (up.isValid(height, width)) {
            locList.add(up)
        }
        if (down.isValid(height, width)) {
            locList.add(down)
        }
        if (right.isValid(height, width)) {
            locList.add(right)
        }
        if (left.isValid(height, width)) {
            locList.add(left)
        }
        if (upRight.isValid(height, width)) {
            locList.add(upRight)
        }
        if (upLeft.isValid(height, width)) {
            locList.add(upLeft)
        }
        if (downRight.isValid(height, width)) {
            locList.add(downRight)
        }
        if (downLeft.isValid(height, width)) {
            locList.add(downLeft)
        }

        return locList
    }

    operator fun plus(incr: Location): Location {
        return Location(row + incr.row, col + incr.col)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Location)
            return false

        else if ((other.row == this.row) && (other.col == this.col))
                return true

        return false
    }

    fun isValid(height: Int, width: Int): Boolean {
        return (row < height) && (row >= 0) && (col >= 0) && (col < width)
    }
}