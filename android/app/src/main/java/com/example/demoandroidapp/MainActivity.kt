package com.example.demoandroidapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val value = intent.extras?.getString(VALUE_KEY) ?: "Value is empty!"
        val textView = findViewById<TextView>(R.id.valueTextView)
        textView?.text = value

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this@MainActivity, MyReactActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {

        const val VALUE_KEY = "value_key"
    }
}