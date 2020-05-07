package com.tejendramachinetest.navigationdrawermodule

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.tejendramachinetest.R

class DrawerItemCustomAdapter internal constructor(private val mContext: Context, private val layoutResourceId: Int, var data: Array<DataModel?>) : ArrayAdapter<DataModel?>(mContext, layoutResourceId, data!!) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItem = convertView
        val inflater = (mContext as Activity).layoutInflater
        listItem = inflater.inflate(layoutResourceId, parent, false)
        val imageViewIcon = listItem.findViewById<ImageView>(R.id.imageViewIcon)
        val textViewName = listItem.findViewById<TextView>(R.id.textViewName)
        val folder = data[position]
        imageViewIcon.setImageResource(folder!!.icon)
        textViewName.text = folder.name
        return listItem
    }


}