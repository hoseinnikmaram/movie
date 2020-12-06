package com.homeandroid.movie .ui

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.homeandroid.movie.R
import com.homeandroid.movie.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    lateinit var navhost:NavHostFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //   setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.bottomNavigation.setOnNavigationItemSelectedListener(this);

         navhost = (supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment?)!!
        navController = navhost.navController



    }

override fun onNavigationItemSelected(item: MenuItem): Boolean {

when (item.itemId) {
   R.id.navigation_search -> {

       if(navController.popBackStack(R.id.searchFragment, false)) {
       } else {
           navController.navigate(R.id.searchFragment)
       }
       return true
   }

   R.id.navigation_home -> {
       if(navController.popBackStack(R.id.homeFragment, false) || navController.currentDestination?.getId()  ==R.id.homeFragment ) {
       }
       else {

           navController.navigate(R.id.homeFragment)
       }

       return true
   }

}
return false
}



override fun onBackPressed() {


    if(navController.currentDestination?.getId()  ==R.id.searchFragment) {
        binding.bottomNavigation.selectedItemId = R.id.navigation_home


    }
    else
    super.onBackPressed()


}
}

