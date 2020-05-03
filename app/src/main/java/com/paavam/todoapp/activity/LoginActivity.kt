package com.paavam.todoapp.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.paavam.todoapp.AppConstants
import com.paavam.todoapp.R

lateinit var fullnameLayout: TextInputLayout
lateinit var usernameLayout: TextInputLayout

lateinit var fullnameEdt: EditText
lateinit var usernameEdt: EditText

lateinit var submit: Button

public class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)

        init()

    }

    private fun init() {
        fullnameEdt = findViewById(R.id.full_name_edttext)
        usernameEdt = findViewById(R.id.user_name_edttext)

        fullnameLayout = findViewById(R.id.full_name_layout)
        usernameLayout = findViewById(R.id.user_name_layout)

        submit = findViewById(R.id.submit)

        submit.setOnClickListener {
            if (checkValidation()) {
                intent = Intent(applicationContext, NotesActivity::class.java)
                intent.putExtra(AppConstants.FULL_NAME, fullnameEdt.text.toString())
                intent.putExtra(AppConstants.USER_NAME, usernameEdt.text.toString())
                startActivity(intent)
            }
        }
    }

    private fun checkValidation(): Boolean {
        if (TextUtils.isEmpty(fullnameEdt.text)) {
            fullnameLayout.error = "Required"
            return false
        }

        if (TextUtils.isEmpty(usernameEdt.text)) {
            usernameLayout.error = "Required"
            return false
        }

        return true;
    }

}