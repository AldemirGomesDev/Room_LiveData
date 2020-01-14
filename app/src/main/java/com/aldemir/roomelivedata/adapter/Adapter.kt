package com.aldemir.roomelivedata.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aldemir.roomelivedata.R
import com.aldemir.roomelivedata.model.NotesEntity


class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var mData: List<NotesEntity>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.notes, parent, false))
    }

    override fun getItemCount(): Int {
        return mData?.size!!
    }

    fun updateList(data: List<NotesEntity>?) {
        mData = data
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = mData?.get(position)
        holder.note.text = note!!.texto
        holder.priority.text = note.prioridade
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val note = itemView.findViewById<TextView>(R.id.notes)
        val priority = itemView.findViewById<TextView>(R.id.priority)
    }
}