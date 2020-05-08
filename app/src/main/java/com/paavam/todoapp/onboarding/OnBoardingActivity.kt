package com.paavam.todoapp.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.paavam.todoapp.AppConstants
import com.paavam.todoapp.R
import com.paavam.todoapp.activity.LoginActivity
import com.paavam.todoapp.util.SharedPrefUtils

class OnBoardingActivity : AppCompatActivity(), OnBoardingFragment1.OnNextClick, OnBoardingFragment2.OnOptionClick {

    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.on_boarding_layout)

        init()
    }

    private fun init() {
        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = FragmentAdapter(supportFragmentManager)
    }

    override fun onClick() {
        viewPager.currentItem = 1
    }

    override fun onOptionBack() {
        viewPager.currentItem = 0
    }

    override fun onOptionDone() {
        SharedPrefUtils.addBoolean(this, AppConstants.ON_BOARDED_COMPLETED, true)
        val intent = Intent(this@OnBoardingActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
