package com.example.sharedcode

import android.os.Bundle
import android.widget.GridLayout
import android.widget.GridLayout.spec
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.chunk_cell.*

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

class MainActivity : AppCompatActivity() {
    val board = Board()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val board = Board()
        board.GenerateBoard(Location(0, 0))


        val gridView = this.findViewById(R.id.gridview) as GridView

        val adapter = CellAdapter(this, R.layout.chunk_cell, data)

        gridView.adapter = adapter

    }

    val data: ArrayList<CellLayout>
    get() {
        val item_list: ArrayList<CellLayout> = ArrayList()

        for (row in 0 until board.height) {
            for (col in 0 until board.width) {
                item_list.add(CellLayout(board.cells[row][col].imageFile))
            }
        }

        return item_list
    }


}
