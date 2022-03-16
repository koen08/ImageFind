package com.example.imagefind.app.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.example.imagefind.R
import com.example.imagefind.app.ui.fragments.favorite.ImageLikeFragment
import com.example.imagefind.app.ui.fragments.wall.ImageWallFragment
import com.example.imagefind.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var bottomNavigation: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottomNavigation = binding.bottomNavigation

        val homeFragment = ImageWallFragment()
        val favoriteFragment = ImageLikeFragment()
        makeCurrentFragment(homeFragment)
        bottomNavigation?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> makeCurrentFragment(homeFragment)
                R.id.menu_favorite -> makeCurrentFragment(favoriteFragment)
            }
            true
        }
    }

    fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frame, fragment).addToBackStack(null)
            .commit()
    }

    fun makeCurrentFragment(fragment: Fragment, closeBottomNav: Boolean) {
        changeVisibility(closeBottomNav)
        supportFragmentManager.beginTransaction().replace(R.id.frame, fragment).addToBackStack(null)
            .commit()
    }

    private fun changeVisibility(closeBottomNav: Boolean) {
        TransitionManager.beginDelayedTransition(
            binding.root,
            Slide(Gravity.BOTTOM).excludeTarget(R.id.frame, true)
        )
        if (closeBottomNav) {
            bottomNavigation?.visibility = View.GONE
        } else {
            bottomNavigation?.visibility = View.VISIBLE
        }
    }


}