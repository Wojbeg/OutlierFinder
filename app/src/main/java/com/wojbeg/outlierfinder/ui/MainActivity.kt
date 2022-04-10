package com.wojbeg.outlierfinder.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.wojbeg.outlierfinder.R
import com.wojbeg.outlierfinder.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initNavigation()
    }

    private fun initNavigation() {
        navController = findNavController(R.id.fragment_nav_host)
        //initialize bottom navigation, navigation bar or drawer
    }

}