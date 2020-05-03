package com.paavam.todoapp

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class SharedPrefUtils(context: Context) {

    companion object {
        private fun getSharedPreferences(context: Context): SharedPreferences? {
            return context.getSharedPreferences(AppConstants.PREFERENCE_NAME, MODE_PRIVATE)
        }

        public fun getString(context: Context, constant: String): String? {
            return getSharedPreferences(context)?.getString(constant, null)
        }

        public fun getBoolean(context: Context, constant: String): Boolean? {
            return getSharedPreferences(context)?.getBoolean(constant, false)
        }
    }

}