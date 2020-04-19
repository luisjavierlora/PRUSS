package com.example.pruss

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ActivityNewChat : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_chat)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
