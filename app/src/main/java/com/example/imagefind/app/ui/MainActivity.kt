package com.example.imagefind.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.imagefind.R
import com.example.imagefind.app.ui.fragments.ImageLikeFragment
import com.example.imagefind.app.ui.fragments.ImageWallFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        val homeFragment = ImageWallFragment()
        val favoriteFragment = ImageLikeFragment()
        makeCurrentFragment(homeFragment)
        bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_home -> makeCurrentFragment(homeFragment)
                R.id.menu_favorite -> makeCurrentFragment(favoriteFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        //Что за apply
        supportFragmentManager.beginTransaction().apply {
        replace(R.id.frame, fragment)
        commit()
    }
}