package com.example.navigationdrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.navigationdrawer.fragments.ChatFragment
import com.example.navigationdrawer.fragments.ProfileFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawer: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this@MainActivity,
            drawer,
            R.string.open,
            R.string.close
        )

        drawer.addDrawerListener(toggle)

        toggle.syncState()



        changeFragment(ProfileFragment())   // by default it will display profile fragment



        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.miProfile -> {
                    changeFragment(ProfileFragment())
                }

                R.id.miChat -> {
                    changeFragment(ChatFragment())
                }

                R.id.miSend -> {
                    Toast.makeText(
                        this@MainActivity,
                        "Send Selected",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                R.id.miShare -> {
                    Toast.makeText(
                        this@MainActivity,
                        "Share Selected",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            drawer.closeDrawer(GravityCompat.START)
            true
        }

    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}