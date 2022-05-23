package com.example.pertemuan5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailBuah : AppCompatActivity() {

    private lateinit var gambarImageView: ImageView
    private lateinit var namaBuahTextView: TextView
    private  lateinit var hargaBuahTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_buah)

        gambarImageView = findViewById(R.id.detailGambarBuah)
        namaBuahTextView = findViewById(R.id.detailNamaBuah)
        hargaBuahTextView = findViewById(R.id.detailHargaBuah)

        namaBuahTextView.text = intent.getStringExtra("nama_buah")
        hargaBuahTextView.text = intent.getStringExtra("nama_buah")
        gambarImageView.setImageResource(intent.getIntExtra("image", 0))

        val actionBar = supportActionBar
        if(actionBar != null){
            actionBar!!.title = "Detail"
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

    }
}