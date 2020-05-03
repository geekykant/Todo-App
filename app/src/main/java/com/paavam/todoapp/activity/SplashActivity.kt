package com.paavam.todoapp.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.paavam.todoapp.AppConstants
import com.paavam.todoapp.R

class SplashActivity: AppCompatActivity() {

    lateinit var preferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_layout)

        setupSharedPreferences()
        checkLoginStatus()
    }

    private fun checkLoginStatus() {
        val isLoggedIn = preferences.getBoolean(AppConstants.IS_LOGGED_IN, false)
        if(isLoggedIn)
            startActivity(Intent(applicationContext, NotesActivity::class.java))
        else
            startActivity(Intent(applicationContext, LoginActivity ::class.java))
    }

    private fun setupSharedPreferences() {
        preferences = getSharedPreferences(AppConstants.PREFERENCE_NAME, Context.MODE_PRIVATE)
    }
}