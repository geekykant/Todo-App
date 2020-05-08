package com.paavam.todoapp.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import com.paavam.todoapp.AppConstants
import com.paavam.todoapp.R
import com.paavam.todoapp.onboarding.OnBoardingActivity
import com.paavam.todoapp.util.SharedPrefUtils

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_layout)

        checkLoginStatus()
        getFCMToken()
    }

    private fun getFCMToken() {
        FirebaseMessaging.getInstance().subscribeToTopic(getString(R.string.default_notification_channel_id))

        FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.i("SplashActivity", "token retrieving failed .., .//")
                        return@addOnCompleteListener
                    }

                    val token = task.result?.token
                    Log.i("SplashActivity", "token $token")
                }
    }

    private fun checkLoginStatus() {
        val isLoggedIn = SharedPrefUtils.getBoolean(this, AppConstants.IS_LOGGED_IN) as Boolean
        val isBoardingCompleted = SharedPrefUtils.getBoolean(this, AppConstants.ON_BOARDED_COMPLETED) as Boolean

        if(!isBoardingCompleted){
            startActivity(Intent(applicationContext, OnBoardingActivity::class.java))
            finish()
            return
        }

        if (isLoggedIn)
            startActivity(Intent(applicationContext, NotesActivity::class.java))
        else
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        finish()
    }
}