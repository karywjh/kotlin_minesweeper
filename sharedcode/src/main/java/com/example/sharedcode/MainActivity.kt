package com.example.sharedcode

import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    val engine = Engine()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gridView = this.findViewById(R.id.gridview) as GridView
        var adapter = CellAdapter(this, R.layout.chunk_cell, data)
        gridView.adapter = adapter

        gridView.onItemClickListener =
            OnItemClickListener { parent, v, position, id ->
                // DO something
                val loc = engine.board.getLocation(position)
                if (engine.state == Engine.GameState.kNotStarted && engine.startGame(loc.row, loc.col)) {
                    engine.openCell(loc.row, loc.col)
                    data[position] = CellLayout(engine.board.cells[loc.row][loc.col].imageFile)
                    println("start game, open cell: ${loc.row}, ${loc.col}")
                } else {
                    engine.openCell(loc.row, loc.col)
                    data[position] = CellLayout(engine.board.cells[loc.row][loc.col].imageFile)
                    println("middle game, open cell: ${loc.row}, ${loc.col}")
                }

                adapter = CellAdapter(this, R.layout.chunk_cell, data)
                gridView.adapter = adapter
            }
    }

    val data: ArrayList<CellLayout>
    get() {
        val item_list: ArrayList<CellLayout> = ArrayList()

        for (row in 0 until engine.board.height) {
            for (col in 0 until engine.board.width) {
                item_list.add(CellLayout(engine.board.cells[row][col].imageFile))
            }
        }

        return item_list
    }

}
