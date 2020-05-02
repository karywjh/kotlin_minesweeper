package com.example.sharedcode

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView

class CellAdapter(
    private val getContext: Context,
    private val CellLayoutId: Int,
    private val cell_item: ArrayList<CellLayout>
) : ArrayAdapter<CellLayout>(getContext, CellLayoutId, cell_item) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var row = convertView
        val Holder : ViewHolder

        if (row == null) {

            val inflater = (getContext as Activity).layoutInflater
            row = inflater!!.inflate(CellLayoutId, parent, false)

            Holder = ViewHolder()
            Holder.img = row!!.findViewById(R.id.img) as ImageView

            row.setTag(Holder)
        } else {

            Holder = row.getTag() as ViewHolder
        }

        val item = cell_item[position]

        Holder.img!!.setImageResource(item.image)

        return row

    }

    class ViewHolder {
        internal var img: ImageView? = null
    }
}