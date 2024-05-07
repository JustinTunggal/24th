package com.example.a24

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class homepage_activity : AppCompatActivity() {

    private lateinit var shopBtn: Button
    private lateinit var barracksBtn: Button
    private lateinit var playBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)

        shopBtn = findViewById(R.id.home_btnshop)
        barracksBtn = findViewById(R.id.home_btnbarracks)
        playBtn = findViewById(R.id.playbutton)

        shopBtn.setOnClickListener{
            val intent = Intent(this, shop_activity::class.java)
            startActivity(intent)
        }

        barracksBtn.setOnClickListener{
            val intent = Intent(this, barracks_activity::class.java)
            startActivity(intent)
        }

        playBtn.setOnClickListener{
            val intent = Intent(this, play_activity::class.java)
            startActivity(intent)
        }
    }
}
