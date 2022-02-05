package com.example.myapplication2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.myapplication2.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
            with(binding) {
                setContentView(root)

                val navHostFragment = supportFragmentManager
                    .findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
                navController = navHostFragment.navController

                NavigationUI.setupWithNavController(this.navView, navController)
                setupActionBarWithNavController(navController, drawerLayout)
            }
        }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            navController,
            binding.drawerLayout
        )
    }
}