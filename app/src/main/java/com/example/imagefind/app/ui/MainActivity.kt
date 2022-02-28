package com.example.imagefind.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.imagefind.R
import com.example.imagefind.app.ui.fragments.favorite.ImageLikeFragment
import com.example.imagefind.app.ui.fragments.wall.ImageWallFragment
import com.example.imagefind.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bottomNavigation = binding.bottomNavigation

        val homeFragment = ImageWallFragment()
        val favoriteFragment = ImageLikeFragment()
        makeCurrentFragment(homeFragment)
        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
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