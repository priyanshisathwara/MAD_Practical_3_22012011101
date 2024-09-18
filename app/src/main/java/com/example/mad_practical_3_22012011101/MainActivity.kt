package com.example.mad_practical_3_22012011101

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.CallLog
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val loginButton = findViewById<Button>(R.id.button7)
        loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
                .putExtra("username","Hello")
                .putExtra("password","123456")
                .also {
                    startActivity(it)
                }
        }
        implicitIntent()
        val username = intent.getStringExtra("username")
        val password = intent.getStringExtra("password")
        Toast.makeText(this, "Logged in as $username", Toast.LENGTH_LONG).show()
    }
    override fun onStart() {
        super.onStart()
    }
    override fun onPause() {
        super.onPause()
    }
    private fun implicitIntent(){
        val urlEditText = findViewById<EditText>(R.id.editTextText)
        val phoneEditText = findViewById<EditText>(R.id.editTextPhone)

        findViewById<Button>(R.id.button).setOnClickListener {
            val url = urlEditText.text.toString()
            if (url.isNotEmpty()) {
                val fullUrl = if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    "https://$url"
                } else {
                    url
                }
                Intent(Intent.ACTION_VIEW).setData(Uri.parse(fullUrl)).also { startActivity(it) }
            } else {
                Toast.makeText(this, "Please enter a URL", Toast.LENGTH_SHORT).show()
            }
        }
        //phone
        findViewById<Button>(R.id.button2).setOnClickListener {
            val phoneNumber = phoneEditText.text.toString()
            if (phoneNumber.isNotEmpty()) {
                Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:$phoneNumber")).also { startActivity(it) }
            } else {
                Toast.makeText(this, "Please enter a phone number", Toast.LENGTH_SHORT).show()
            }
        }
        //call log
        findViewById<Button>(R.id.button3).setOnClickListener {
            Intent(Intent.ACTION_VIEW).setType(CallLog.Calls.CONTENT_TYPE).also { startActivity(it) }
        }
        //gallery
        findViewById<Button>(R.id.button4).setOnClickListener {
            Intent(Intent.ACTION_VIEW).setType("image/*").also { startActivity(it) }
        }
        //camera
        findViewById<Button>(R.id.button5).setOnClickListener {
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { startActivity(it) }
        }
        //alarm
        findViewById<Button>(R.id.button6).setOnClickListener {
            Intent(AlarmClock.ACTION_SHOW_ALARMS).also { startActivity(it) }
        }
    }
}

