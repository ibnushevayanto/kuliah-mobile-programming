package com.example.pertemuan5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.R.layout.simple_list_item_1

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val languages = listOf<String>("Java", "Kotlin", "Javascript", "PHP", "Phython", "R", "Ruby", "C++", "C#", "C", "Cobol", "Pascal")
        val lv_language: ListView = findViewById(R.id.lv_language)
        lv_language.adapter = ArrayAdapter(this, simple_list_item_1, languages)
    }
}