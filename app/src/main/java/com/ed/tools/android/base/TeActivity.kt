package com.ed.tools.android.base

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.tools.EasyWebView

class TeActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv)
        val web=findViewById<EasyWebView>(R.id.web)
       // web.goAntoPath("",th)
    }
}