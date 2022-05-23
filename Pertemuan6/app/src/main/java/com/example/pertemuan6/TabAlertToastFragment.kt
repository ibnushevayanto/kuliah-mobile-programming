package com.example.pertemuan6

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast

class TabAlertToastFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_tab_alert_toast, container, false)

        val btnShowAlert = view.findViewById<Button>(R.id.btnShowAlert)
        btnShowAlert.setOnClickListener {
            val alertBuilder = AlertDialog.Builder(view.context)
            alertBuilder.setTitle("Android Alert")
            alertBuilder.setPositiveButton("OKE") {
                    _, _ -> Toast.makeText(view.context, "Show Toast", Toast.LENGTH_SHORT).show()
            }
            alertBuilder.show()
        }
        return view
    }
}