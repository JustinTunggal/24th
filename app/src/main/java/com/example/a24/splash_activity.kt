package com.example.a24

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class splash_activity : AppCompatActivity() {

    private lateinit var firsttext: TextView
    private lateinit var secondtext: TextView
    private lateinit var thirdtext: TextView
    private val delayMillis: Long = 3000 // Delay duration (5 seconds)
    private val fadeDuration: Long = 1000 // Fade animation duration (1 second)

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)

        firsttext = findViewById(R.id.text1)
        secondtext = findViewById(R.id.text2)
        thirdtext = findViewById(R.id.text3)

        lifecycleScope.launch {
            delay(delayMillis)
            fadeOut(firsttext, secondtext) {
                fadeIn(thirdtext) {
                    lifecycleScope.launch {
                        delay(delayMillis)
                        fadeOut2(thirdtext) {
                            nextActivity()
                        }
                    }
                }
            }
        }
    }

    private fun fadeOut(view1: View, view2: View, onEnd: () -> Unit) {
        view1.animate()
            .alpha(0f)
            .setDuration(fadeDuration)
            .start()
        view2.animate()
            .alpha(0f)
            .setDuration(fadeDuration)
            .withEndAction {
                view1.visibility = View.GONE
                view2.visibility = View.GONE
                onEnd()
            }
            .start()
    }

    private fun fadeIn(view: View, onEnd: () -> Unit) {
        view.alpha = 0f
        view.visibility = View.VISIBLE
        view.animate()
            .alpha(1f)
            .setDuration(fadeDuration)
            .withEndAction(onEnd)
            .start()
    }

    private fun fadeOut2(view: View, onEnd: () -> Unit) {
        view.animate()
            .alpha(0f)
            .setDuration(fadeDuration)
            .withEndAction {
                view.visibility = View.GONE
                onEnd()
            }
            .start()
    }

    private fun nextActivity() {
        val intent = Intent(this, homepage_activity::class.java)
        startActivity(intent)
        finish()
    }
}
