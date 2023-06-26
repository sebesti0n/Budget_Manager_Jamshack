package com.example.budgetmanager

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.budgetmanager.Fragment.DashboardFragment
import com.example.budgetmanager.Fragment.HomeFragment
import com.example.budgetmanager.Fragment.ProfileFragment
import com.example.budgetmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val homeFragment =HomeFragment()
        val dashboardFragment = DashboardFragment()
        val profileFragment =ProfileFragment()
        setCurrentFragment(homeFragment)
        val navView: BottomNavigationView = binding.navView
        navView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_home->setCurrentFragment(homeFragment)
                R.id.navigation_profile->setCurrentFragment(profileFragment)
                R.id.navigation_dashboard->setCurrentFragment(dashboardFragment)
            }
            true
        }


    }
    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment_activity_main,fragment)
            commit()
        }
}