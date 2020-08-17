package com.example.thecocktaildb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.thecocktaildb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDataBinding()
        setUpBottonNavigation()
    }

    private fun setUpBottonNavigation() {
        NavigationUI.setupWithNavController(
            mBinding.bottomNavigation,
            Navigation.findNavController(this, R.id.nav_host_fragment)
        )
    }

    private fun setupDataBinding(){
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}