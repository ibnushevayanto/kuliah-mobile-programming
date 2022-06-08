package com.example.myapplication

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso

class DetailTransaksiActivity : AppCompatActivity() {
    private lateinit var detailJenisTransaksi: TextView
    private lateinit var detailNamaBarang: TextView
    private lateinit var detailHargaBarang: TextView
    private lateinit var detailImageBarang: ImageView
    private lateinit var btnHapus: Button
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_transaksi)
        title = "Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        detailJenisTransaksi = findViewById(R.id.detailJenisTransaksi)
        detailNamaBarang = findViewById(R.id.detailNamaBarang)
        detailHargaBarang = findViewById(R.id.detailHarga)
        detailImageBarang = findViewById(R.id.detailImageTransaksi)

        detailJenisTransaksi.text = intent.getStringExtra("jenis_transaksi")
        detailNamaBarang.text = intent.getStringExtra("nama_transaksi")
        detailHargaBarang.text = "Rp. " + intent.getStringExtra("harga")
        Picasso.get().load(intent.getStringExtra("image")).into(detailImageBarang)
        id = intent.getStringExtra("id").toString()

        btnHapus = findViewById(R.id.btnHapus)
        btnHapus.setOnClickListener {

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Perhatian")
            builder.setMessage("Apakah anda yakin ingin menghapus data ini ?")

            builder.setPositiveButton("Ya") { dialog, which ->

                val mDialog = ProgressDialog(this)
                mDialog.setMessage("Sedang Menyimpan Data")
                mDialog.setCancelable(false)
                mDialog.show()

                val dbTransasksi = FirebaseDatabase.getInstance().getReference("transaksi").child(id)
                dbTransasksi.removeValue().addOnSuccessListener {
                    mDialog.hide()
                    val mainIntent = Intent(this, MainActivity::class.java)
                    startActivity(mainIntent)
                }.addOnFailureListener {
                    mDialog.hide()
                    Toast.makeText(this, "Gagal menghapus data", Toast.LENGTH_SHORT)
                }
            }

            builder.setNegativeButton("Tidak") { dialog, which ->
                Toast.makeText(applicationContext,
                    "Batal menghapus data", Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }
    }
}