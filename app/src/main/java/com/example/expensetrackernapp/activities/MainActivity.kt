package com.example.expensetrackernapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.expensetrackernapp.R
import com.example.expensetrackernapp.databinding.ActivityMainBinding
import com.example.expensetrackernapp.fragments.AccountsFragment
import com.example.expensetrackernapp.fragments.HomeTransFragment
import com.example.expensetrackernapp.fragments.MoreFragment
import com.example.expensetrackernapp.fragments.StatsFragment
import com.example.expensetrackernapp.utils.MoveFrags

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

            MoveFrags.replaceFrags(R.id.frameLayoutMainActivity, supportFragmentManager, HomeTransFragment())


        binding.bnvMainActivity.setOnNavigationItemSelectedListener { item ->

            val selectedFragment = when (item.itemId) {

                R.id.bottomNavHomeTrans -> HomeTransFragment()
                R.id.bottomNavStats -> StatsFragment()
                R.id.bottomNavAccounts -> AccountsFragment()
                R.id.bottomNavMore -> MoreFragment()

                else -> throw IllegalArgumentException("Unknown menu item")
            }


            MoveFrags.replaceFrags(R.id.frameLayoutMainActivity, supportFragmentManager, selectedFragment)
            true
        }



    }
}