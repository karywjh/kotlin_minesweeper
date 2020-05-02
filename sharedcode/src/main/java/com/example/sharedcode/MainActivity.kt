package com.example.sharedcode

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val board = Board()
        board.GenerateBoard(Location(0, 0))
//

//        val adapter: GridAdapter = gridv.getAdapter() as GridAdapter

//        boardGrid.get = chunk_cell
//        val adapter: ArrayAdapter<*> =
//            ArrayAdapter<String>(this, R.layout.ListView, R.id.textView, StringArray)
//        val customAdapter = CustomAdapter(applicationContext, logos)
//
//        for (i in 0..10) {
//            boardGrid.addView(chunk_cell)
//        }
    }
}
