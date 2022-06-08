package com.example.pertemuan5

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.models.Transaksi
import com.example.myapplication.R
import com.squareup.picasso.Picasso

class ListJenisTransaksiAdapter (private val items: List<Transaksi>)
    : RecyclerView.Adapter<ListJenisTransaksiAdapter.ListViewHolder>() {
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var namaTransaksi: TextView = itemView.findViewById(R.id.namaTransaksi)
        var jenisTransaksi: TextView = itemView.findViewById(R.id.jenisTransaksi)
        var gambarJenisTransasksi: ImageView = itemView.findViewById(R.id.gambarJenisTransaksi)
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListJenisTransaksiAdapter.ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_transaksi, parent, false)
        return ListViewHolder(view)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Transaksi, index: Int)
    }

    override fun onBindViewHolder(holder: ListJenisTransaksiAdapter.ListViewHolder, position: Int) {
        val transaksi = items[position]

        Picasso.get().load(transaksi.image).into(holder.gambarJenisTransasksi)
        holder.namaTransaksi.text = transaksi.namaTransaksi + " (RP." + transaksi.harga + ")"
        holder.jenisTransaksi.text = transaksi.jenisTransaksi.toString()


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