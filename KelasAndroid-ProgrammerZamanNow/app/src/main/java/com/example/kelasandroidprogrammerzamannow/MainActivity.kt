package com.example.kelasandroidprogrammerzamannow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var sayHelloButton: Button
    private lateinit var sayHelloTextView: TextView

    private fun initComponents() {
        nameEditText = findViewById(R.id.nameEditText)
        sayHelloButton = findViewById(R.id.sayHelloButton)
        sayHelloTextView = findViewById(R.id.resultTextView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hello_world)
        initComponents()

        sayHelloTextView.text = resources.getString(R.string.app_name)
        sayHelloButton.setOnClickListener {
            Log.i("PZN", "Hello World")

            for (name in resources.getStringArray(R.array.family)){
                Log.i("String Array", name)
            }

            val name = nameEditText.text.toString()
            sayHelloTextView.text = resources.getString(R.string.sayHelloTextView, name)
        }
    }
}