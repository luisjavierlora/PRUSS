package com.example.pruss.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pruss.model.LocalTask.Task
import kotlinx.android.synthetic.main.layout_task.view.*

class Rv_AdapterTask(var context: Context, var items: ArrayList<Task>): RecyclerView.Adapter<Rv_AdapterTask.ViewHolder>()  {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Rv_AdapterTask.ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(com.example.pruss.R.layout.layout_task,parent,false)

        val viewHolder= Rv_AdapterTask.ViewHolder(itemView,context)
        return  viewHolder
    }

    override fun getItemCount(): Int {
        return this.items.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=items.get(position)



        holder.bindDeudor(item)
    }


    class ViewHolder(itemView: View,context: Context):RecyclerView.ViewHolder(itemView) {


        fun bindDeudor(task: Task) {
            itemView.tv_title.text = task.titulo
            itemView.tv_horalist.text=task.hora_en

        }
    }


    fun update( listI: ArrayList<Task>){
        items.clear()
        items.addAll(listI)
        this.notifyDataSetChanged()

    }
}