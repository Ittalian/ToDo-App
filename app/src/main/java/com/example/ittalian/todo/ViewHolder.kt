package com.example.ittalian.todo

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//class ViewHolder(itemView: View){
    var dateText: TextView? = null
    var todoText: TextView? = null
    var deadLineText: TextView? = null

    init {
        dateText = itemView.findViewById(R.id.dateText)
        todoText  = itemView.findViewById(R.id.todoText)
        deadLineText = itemView.findViewById(R.id.deadLineText)
    }
}