package com.dango.chewieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dango.chewieapp.databinding.ActivityHomeBinding
import com.dango.chewieapp.home.HomeFragment
import com.dango.songlist.SongListFragment
import com.dango.songqueue.SongQueueFragment
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding
    private var clickedNavItem = 0
    private lateinit var fragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(binding.appBarMain.toolbarMain)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.menu_icon)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_song_queue, R.id.nav_song_list
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

//        Handler().postDelayed({
//            navView.setNavigationItemSelectedListener{
//
//                when (it.itemId) {
//                    R.id.nav_home -> {
//                        clickedNavItem = R.id.nav_home
//                        fragment = HomeFragment()
//                    }
//                    R.id.nav_song_queue -> {
//                        clickedNavItem = R.id.nav_song_queue
//                        fragment = SongQueueFragment()
//                    }
//                    R.id.nav_song_list -> {
//                        clickedNavItem = R.id.nav_song_list
//                        fragment = SongListFragment()
//                    }
//                }
//
//                // Closes the drawer, triggering the listener above
//                drawerLayout.closeDrawer(GravityCompat.START)
//                true
//            }
//        }, 2000)
//
//        drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
//            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {}
//            override fun onDrawerOpened(drawerView: View) {}
//            override fun onDrawerStateChanged(newState: Int) {}
//            override fun onDrawerClosed(drawerView: View) {
//                fragment.let{
//
//                    supportFragmentManager.beginTransaction().replace(R.id.container, it).commit()
//                }
//            }
//        })


//        supportActionBar?.setHomeButtonEnabled(true)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setHomeAsUpIndicator(R.drawable.piano_keys_icon)
//        supportActionBar?.elevation = 10F
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.main, menu)
//        return true
//    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}