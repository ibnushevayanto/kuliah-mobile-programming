package com.example.pertemuan5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvBuah: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemsBuah: ArrayList<Buah> = arrayListOf<Buah>(
            Buah(urlImage = R.drawable.cherry, namaBuah = "Cherry", hargaBuah =1000),
        )

        rvBuah = findViewById(R.id.listview_buah)
        rvBuah.setHasFixedSize(true)
        rvBuah.layoutManager = LinearLayoutManager(this)
        val listBuahAdapter = MyAdapter(itemsBuah)
        rvBuah.adapter = listBuahAdapter
    }
}