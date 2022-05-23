package com.example.pertemuan5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvBuah: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvBuah = findViewById(R.id.listview_buah)

        var itemsBuah: ArrayList<Buah> = arrayListOf<Buah>(
            Buah(urlImage = R.drawable.cherry, namaBuah = "Cherry", hargaBuah = 1000),
            Buah(urlImage = R.drawable.jeruk, namaBuah = "Jeruk", hargaBuah = 2000),
            Buah(urlImage = R.drawable.nanas, namaBuah = "Nanas", hargaBuah = 4000),
            Buah(urlImage = R.drawable.pisang, namaBuah = "Pisang", hargaBuah = 6000 ),
        )

        rvBuah.setHasFixedSize(true)
        rvBuah.layoutManager = LinearLayoutManager(this)
        val listBuahAdapter = MyAdapter(itemsBuah)
        rvBuah.adapter = listBuahAdapter

        listBuahAdapter.setOnItemClickCallback(object: MyAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Buah, index: Int) {
                val detailIntent = Intent(this@MainActivity, DetailBuah::class.java)
                detailIntent.putExtra("nama_buah", itemsBuah[index].namaBuah)
                detailIntent.putExtra("harga_buah", itemsBuah[index].hargaBuah)
                detailIntent.putExtra("image", itemsBuah[index].urlImage)
                startActivity(detailIntent)
            }
        } )
    }
}