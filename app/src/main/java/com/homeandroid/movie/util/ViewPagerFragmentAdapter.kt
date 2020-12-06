package com.homeandroid.movie.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.homeandroid.movie.ui.home.HomeFragment
import com.homeandroid.movie.ui.search.SearchFragment

class ViewPagerFragmentAdapter(private val list:List<Fragment>,private val fm: FragmentActivity): FragmentStateAdapter(fm) {
    override fun getItemCount()=2

    override fun createFragment(position: Int): Fragment {
      // return list[position]
        when(position){
            0->return HomeFragment()
            1->return SearchFragment()
        }
        return HomeFragment()
    }


}