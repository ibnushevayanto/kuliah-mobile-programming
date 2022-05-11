package com.example.pertemuan1_4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity: AppCompatActivity() {

    private lateinit var btnLogout: Button

    private fun firstInit(){
        btnLogout = findViewById(R.id.logoutButton)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        firstInit()

        val actionBar = supportActionBar
        if(actionBar != null){
            actionBar!!.title = "Home"
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        btnLogout.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}