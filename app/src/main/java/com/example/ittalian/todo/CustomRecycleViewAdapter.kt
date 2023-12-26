package com.example.ittalian.todo

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.realm.RealmResults
import android.text.format.DateFormat
import android.util.Log

class CustomRecycleViewAdapter(realmResults: RealmResults<ToDo>) : RecyclerView.Adapter<ViewHolder>() {
    private  val rResults: RealmResults<ToDo> = realmResults
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.one_result, parent, false)
        val viewHolder = ViewHolder(view)

        return viewHolder
    }

    override fun getItemCount(): Int {
        return rResults.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = rResults[position]
        Log.v("position", "$position")
        holder.dateText?.text = DateFormat.format("yyyy/MM/dd kk:mm", todo?.dateTime)
        holder.todoText?.text = "${todo?.toDo.toString()}"
        holder.deadLineText?.text = "${todo?.deadLine.toString()}\nまで"
        holder.itemView.setBackgroundColor(if (position % 2 == 0) Color.LTGRAY else Color.WHITE)
        holder.itemView.setOnClickListener{
            val intent = Intent(it.context, EditActivity::class.java)
            intent.putExtra("id", todo?.id)
            it.context.startActivity(intent)
        }
    }
}