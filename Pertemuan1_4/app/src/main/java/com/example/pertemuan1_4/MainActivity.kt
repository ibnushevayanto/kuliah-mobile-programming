package com.example.pertemuan1_4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity: AppCompatActivity() {
    private lateinit var btnSimpan: Button
    private lateinit var fieldNIM: EditText
    private lateinit var fieldNama: EditText

    private fun firstInit(){
        btnSimpan = findViewById(R.id.simpanButton)
        fieldNIM = findViewById(R.id.nimEditText)
        fieldNama = findViewById(R.id.namaEditText)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firstInit()

        btnSimpan.setOnClickListener() {
            if(fieldNIM.text.toString() == "admin" && fieldNama.text.toString() == "admin"){
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }else{
                val alertDialog = AlertDialog.Builder(this)

                alertDialog.setTitle("Informasi")
                alertDialog.setMessage("NIM / Nama Salah")
                alertDialog.setPositiveButton(android.R.string.yes) {
                    dialog,
                    which -> Log.i("Hello", "Hello World")
                }
                alertDialog.setNegativeButton(android.R.string.no) { dialog, which ->
                    Toast.makeText(applicationContext,
                        android.R.string.no, Toast.LENGTH_SHORT).show()
                }
                alertDialog.setNeutralButton("Maybe") { dialog, which ->
                    Toast.makeText(applicationContext,
                        "Maybe", Toast.LENGTH_SHORT).show()
                }
                alertDialog.show()
            }
        }
    }
}