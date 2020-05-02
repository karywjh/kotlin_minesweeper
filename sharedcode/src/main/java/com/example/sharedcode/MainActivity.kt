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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val board = Board()
        board.GenerateBoard(Location(0, 0))


        val gridView = this.findViewById(R.id.gridview) as GridView

        val adapter = CellAdapter(this, R.layout.chunk_cell, data)

        gridView.adapter = adapter

//        boardGrid.get = chunk_cell
//        val adapter: ArrayAdapter<*> =
//            ArrayAdapter<String>(this, R.layout.ListView, R.id.textView, StringArray)
//        val customAdapter = CustomAdapter(applicationContext, logos)
//
//        for (i in 0..10) {
//            boardGrid.addView(chunk_cell)
//        }
    }

    val data: ArrayList<CellLayout>
    get() {
        val item_list: ArrayList<CellLayout> = ArrayList<CellLayout>()

        item_list.add(CellLayout(R.drawable.facing_down))
        item_list.add(CellLayout(R.drawable.cell0))
        item_list.add(CellLayout(R.drawable.cell1))
        item_list.add(CellLayout(R.drawable.cell2))
        item_list.add(CellLayout(R.drawable.facing_down))
        item_list.add(CellLayout(R.drawable.cell0))
        item_list.add(CellLayout(R.drawable.cell1))
        item_list.add(CellLayout(R.drawable.cell2))
        item_list.add(CellLayout(R.drawable.facing_down))
        item_list.add(CellLayout(R.drawable.cell0))
        item_list.add(CellLayout(R.drawable.cell1))
        item_list.add(CellLayout(R.drawable.cell2))
        item_list.add(CellLayout(R.drawable.facing_down))
        item_list.add(CellLayout(R.drawable.cell0))
        item_list.add(CellLayout(R.drawable.cell1))
        item_list.add(CellLayout(R.drawable.cell2))


        return item_list
    }

}
