package com.example.furnitureshopsubmissiondicoding

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AboutMe : AppCompatActivity() {
    private lateinit var toolbarAboutMe: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_about_me)

        toolbarAboutMe = findViewById(R.id.toolbar_about_me)
        setSupportActionBar(toolbarAboutMe)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.about_page)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}