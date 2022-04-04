package com.example.testalarapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    val textView = itemView.findViewById<TextView>(R.id.textView2)
    val imageView = itemView.findViewById<ImageView>(R.id.imageView)

}