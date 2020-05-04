package com.paavam.todoapp.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.paavam.todoapp.AppConstants

class SharedPrefUtils(context: Context) {

    companion object {
        private lateinit var preferences: SharedPreferences

        fun getInstance(context: Context): SharedPreferences{
            synchronized(SharedPrefUtils::class){
                preferences = context.getSharedPreferences(AppConstants.PREFERENCE_NAME, MODE_PRIVATE)
            }
            
            return preferences
        }
        
        fun getString(context: Context, constant: String): String? {
            return getInstance(context).getString(constant, null)
        }

        fun getBoolean(context: Context, constant: String): Boolean? {
            return getInstance(context).getBoolean(constant, false)
        }

        fun addString(context: Context, constant: String, value: String){
            getInstance(context).edit()?.putString(constant,value)?.apply()
        }

        fun addBoolean(context: Context, constant: String, value: Boolean){
            getInstance(context).edit()?.putBoolean(constant,value)?.apply()
        }
    }

}