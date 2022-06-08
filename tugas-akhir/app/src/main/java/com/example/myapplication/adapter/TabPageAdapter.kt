package com.example.myapplication.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.tabs.TabHome
import com.example.myapplication.tabs.TabTransaksi

class TabPageAdapter (activity: FragmentActivity, private val tabCount: Int)
    : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = tabCount

    override fun createFragment(position: Int): Fragment {
        return when (position)
        {
         0 -> TabHome()
         1 -> TabTransaksi()
         else -> TabHome()
        }
    }
}