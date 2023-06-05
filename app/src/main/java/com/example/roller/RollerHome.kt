package com.example.roller

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.AnimatedImageDrawable
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.core.graphics.convertTo
import kotlinx.coroutines.NonCancellable.start
import kotlinx.coroutines.delay
import kotlin.random.Random

class RollerHome : AppCompatActivity() {

    lateinit var rzucgorna: ImageView
    lateinit var rzucdolna: ImageView
    lateinit var rollButtom: Button

    lateinit var animationDrawable: AnimationDrawable
    lateinit var animationDrawable1: AnimationDrawable

    var isStart = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roller_home)

        rzucgorna = findViewById(R.id.rzuc_image_roller2)
        rzucdolna = findViewById(R.id.rzuc_image_roller)
        rollButtom = findViewById(R.id.rzuc_button_roller)
        rzucgorna.setImageResource(R.drawable.animate_dice)
        rzucdolna.setImageResource(R.drawable.animate_dice)
        animationDrawable = rzucgorna.drawable as AnimationDrawable
        animationDrawable1 = rzucdolna.drawable as AnimationDrawable
        rollButtom.setBackgroundColor(Color.GREEN)

        // set animation Start
        rollButtom.setOnClickListener {
            if (!isStart) {
                animationDrawable.run()
                animationDrawable1.run()
                rollButtom.text = "stop"
                isStart = true
                rollButtom.setBackgroundColor(Color.RED)
                val randomInt = Random.nextInt(6) + 1
                val wynikRzutu = when (randomInt){
                    1 -> R.drawable.d1
                    2 -> R.drawable.d2
                    3 -> R.drawable.d3
                    4 -> R.drawable.d4
                    5 -> R.drawable.d5
                    else -> R.drawable.d6
                }
                rzucgorna.setImageResource(wynikRzutu)
                rzucgorna.setImageDrawable(animationDrawable)
            }else{
                animationDrawable.stop()
                animationDrawable1.stop()
                rollButtom.text = "Rzuć"
                isStart = false
                rollButtom.setBackgroundColor(Color.GREEN)
            }
        }

    }

}



