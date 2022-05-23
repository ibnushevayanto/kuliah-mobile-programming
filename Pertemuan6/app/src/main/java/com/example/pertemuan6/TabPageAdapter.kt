package com.example.myapplication.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pertemuan6.TabAlertToastFragment
import com.example.pertemuan6.TabListViewFragment
import com.example.pertemuan6.TabProjectActivityFragment

class TabPageAdapter (activity: FragmentActivity, private val tabCount: Int)
    : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = tabCount

    override fun createFragment(position: Int): Fragment {
        return when (position)
        {
            0 -> TabAlertToastFragment()
            1 -> TabListViewFragment()
            2 -> TabProjectActivityFragment()
            else -> TabAlertToastFragment()
        }
    }
}