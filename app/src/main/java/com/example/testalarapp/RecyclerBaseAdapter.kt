package com.example.testalarapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.testalarapp.database.DataOfDataBase
import com.squareup.picasso.Picasso

class RecyclerBaseAdapter(val data: ArrayList<DataOfDataBase>, val activity: ActivityBase): RecyclerView.Adapter<ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_of_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            val intent = Intent(activity, DetailInfoActivity::class.java)
            intent.putExtra("info", data[position])
            activity.startActivity(intent)
        }
        holder.textView.text = data[position].name
        Picasso.get()
            .load("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg")
            .into(holder.imageView)

        if (position == data.size-1){
            activity.numberPage++
            activity.getDataByPage(activity.numberPage)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

}