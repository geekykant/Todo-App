package com.paavam.todoapp.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.paavam.todoapp.AppConstants
import com.paavam.todoapp.R
import com.paavam.todoapp.SharedPrefUtils

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_layout)

        checkLoginStatus()
    }

    private fun checkLoginStatus() {
        val isLoggedIn = SharedPrefUtils.getBoolean(this, AppConstants.IS_LOGGED_IN) as Boolean
        if (isLoggedIn)
            startActivity(Intent(applicationContext, NotesActivity::class.java))
        else
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        finish()
    }
}