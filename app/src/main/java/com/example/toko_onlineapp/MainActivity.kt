package com.example.toko_onlineapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.toko_onlineapp.fragment.AkunFragment
import com.example.toko_onlineapp.fragment.HomeFragment
import com.example.toko_onlineapp.fragment.KeranjangFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val fragmentHome: Fragment = HomeFragment()
    private val fragmentAkun: Fragment = AkunFragment()
    private var fragmentKeranjang: Fragment = KeranjangFragment()
    private val fM: FragmentManager = supportFragmentManager
    private var active: Fragment = fragmentHome

    private lateinit var menu: Menu
    private lateinit var menuItem: MenuItem
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fM.beginTransaction().add(R.id.container, fragmentHome).show(fragmentHome).commit()
        fM.beginTransaction().add(R.id.container, fragmentAkun).hide(fragmentAkun).commit()
        fM.beginTransaction().add(R.id.container, fragmentKeranjang).hide(fragmentKeranjang).commit()

        bottomNavigationView = findViewById(R.id.nav_view)
        menu = bottomNavigationView.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->

            when(item.itemId){
                R.id.navigation_home -> {
                    //Log.d("Response","Home")
                    callFragment(0, fragmentHome)
                }
                R.id.navigation_keranjang-> {
                    //og.d("Response","Home")
                    callFragment(1,fragmentKeranjang)
                }
                R.id.navigation_akun -> {
                    //Log.d("Response","Home")
                    callFragment(2,fragmentAkun)
                }

            }

            false
        }

    }

    fun callFragment(int: Int, fragment: Fragment){
        menuItem = menu.getItem(int)
        menuItem.isChecked = true
        fM.beginTransaction().hide(active).show(fragment).commit()
        active = fragment
    }

}