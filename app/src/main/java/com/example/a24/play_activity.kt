package com.example.a24

import android.os.Bundle
import java.util.Random
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class play_activity : AppCompatActivity() {

    private lateinit var countDownTimer: CountDownTimer
    private lateinit var playTimer: TextView
    private lateinit var roundCount: TextView
    private lateinit var passBtn: Button
    private var round = 0
    private val random = Random()
    private val totalTimeinMillis: Long = 300000 // 5 minute in milliseconds
    private val intervalInMillis: Long = 1000

    private val imageViews = arrayOfNulls<ImageView>(4)
    private val cards = mutableListOf(
        R.drawable.as_club,
        R.drawable.as_diamond,
        R.drawable.as_spade,
        R.drawable.as_heart,
        R.drawable.club2,
        R.drawable.diamond2,
        R.drawable.spade2,
        R.drawable.heart2,
        R.drawable.club3,
        R.drawable.diamond3,
        R.drawable.spade3,
        R.drawable.heart3,
        R.drawable.club4,
        R.drawable.diamond4,
        R.drawable.spade4,
        R.drawable.heart4,
        R.drawable.club5,
        R.drawable.diamond5,
        R.drawable.spade5,
        R.drawable.heart5,
        R.drawable.club6,
        R.drawable.diamond6,
        R.drawable.spade6,
        R.drawable.heart6,
        R.drawable.club7,
        R.drawable.diamond7,
        R.drawable.spade7,
        R.drawable.heart7,
        R.drawable.club8,
        R.drawable.spade8,
        R.drawable.diamond8,
        R.drawable.heart8,
        R.drawable.club9,
        R.drawable.diamond9,
        R.drawable.spade9,
        R.drawable.heart9,
        R.drawable.club10,
        R.drawable.diamond10,
        R.drawable.spade10,
        R.drawable.heart10,
    )
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.playpage)

        playTimer = findViewById(R.id.playTimer)
        roundCount = findViewById(R.id.round_count)
        passBtn = findViewById(R.id.passbtn)

        imageViews[0] = findViewById(R.id.playcard1)
        imageViews[1] = findViewById(R.id.playcard2)
        imageViews[2] = findViewById(R.id.playcard3)
        imageViews[3] = findViewById(R.id.playcard4)

        passBtn.setOnClickListener {
            passButtonClicked()
        }

        startTimer()
        displayNextSet()
    }

    private fun passButtonClicked() {
        if (currentIndex >= 4) {
            currentIndex -= 4
        }
        for (i in 3 downTo 0) {
            val lastCardIndex = currentIndex + i
            if (lastCardIndex >= 0 && lastCardIndex < cards.size) {
                imageViews[i]?.setImageResource(cards[lastCardIndex])
                cards.add(cards.removeAt(lastCardIndex))
            }
        }
        currentIndex += 4
        displayNextSet()
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(totalTimeinMillis, intervalInMillis) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                playTimer.text = getString(R.string.timer_format,seconds / 60, seconds % 60)
            }

            override fun onFinish() {
                playTimer.text = getString(R.string.timer_finish)
            }
        }.start()
    }

    private fun displayNextSet() {
        for (i in 0 until 4) {
            val randomIndex = random.nextInt(cards.size)
            imageViews[i]?.setImageResource(cards[randomIndex])
        }
        round++
        roundCount.text = round.toString()
    }
}
