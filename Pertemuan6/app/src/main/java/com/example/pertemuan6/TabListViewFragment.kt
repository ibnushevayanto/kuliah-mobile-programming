package com.example.pertemuan6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.R.layout.simple_list_item_1

class TabListViewFragment : Fragment() {
    private lateinit var lvMobil: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_tab_list_view, container, false)
        lvMobil = view.findViewById(R.id.lv_mobil)

        val mobil = listOf("Avanza", "Inova", "Lamborgini", "Grand Livina", "Xenia")
        lvMobil.adapter = ArrayAdapter(view.context, simple_list_item_1, mobil)
        return view
    }
}