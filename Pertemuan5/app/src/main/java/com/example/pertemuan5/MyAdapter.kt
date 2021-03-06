package com.example.pertemuan5

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter (private val items: List<Buah>)
    : RecyclerView.Adapter<MyAdapter.ListViewHolder>() {
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var namaBuah: TextView = itemView.findViewById(R.id.namaBuah)
        var hargaBuah: TextView = itemView.findViewById(R.id.hargaBuah)
        var imgPhoto: ImageView = itemView.findViewById(R.id.gambarBuah)
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyAdapter.ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_row_buah, parent, false)
        return ListViewHolder(view)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Buah, index: Int)
    }

    override fun onBindViewHolder(holder: MyAdapter.ListViewHolder, position: Int) {
        val buah = items[position]

        holder.imgPhoto.setImageResource(buah.urlImage)
        holder.namaBuah.text = buah.namaBuah
        holder.hargaBuah.text = buah.hargaBuah.toString()

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(items[holder.adapterPosition], position)
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun getItemCount(): Int {
        return items.size
    }
}