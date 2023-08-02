package com.example.navigationdrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
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



        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, ProfileFragment())
            .commit()
        navigationView.setCheckedItem(R.id.miProfile)




        navigationView.setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when(item.itemId) {
                    R.id.miProfile -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_container, ProfileFragment())
                            .commit()
                    }
                    R.id.miChat -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_container, ChatFragment())
                            .commit()
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
                return true
            }

        })

    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else{
            super.onBackPressed()
        }
    }
}