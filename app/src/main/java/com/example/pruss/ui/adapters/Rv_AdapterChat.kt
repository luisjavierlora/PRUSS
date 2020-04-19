package com.example.pruss.ui.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pruss.model.Chat
import kotlinx.android.synthetic.main.layout_inbox.view.*
import kotlinx.android.synthetic.main.layout_proyecto.view.*

class Rv_AdapterChat(var context: Context, var items: ArrayList<Chat>): RecyclerView.Adapter<Rv_AdapterChat.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Rv_AdapterChat.ViewHolder {
        val itemView = LayoutInflater.from(context)
            .inflate(com.example.pruss.R.layout.layout_inbox, parent, false)

        val viewHolder = Rv_AdapterChat.ViewHolder(itemView, context)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return this.items.count()
    }


    override fun onBindViewHolder(holder: Rv_AdapterChat.ViewHolder, position: Int) {
        val item = items.get(position)

        holder.bindDeudor(item)
    }

    class ViewHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView) {

        fun bindDeudor(chat: Chat) {
            itemView.tv_fotoemisor.setImageResource(chat.icono)
            itemView.tv_emisor.text = chat.emisor

        }

    }
}