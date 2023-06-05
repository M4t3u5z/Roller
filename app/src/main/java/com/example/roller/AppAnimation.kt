package com.example.roller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView

class AppAnimation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_animation)

        val logoAnimation = findViewById<ImageView>(R.id.imageView_animation)

        logoAnimation.animation = AnimationUtils.loadAnimation(this, R.anim.shake_animation_roller)
        logoAnimation.animate().setDuration(1500).alpha(1f).withEndAction{
            val i = Intent(this,Logging::class.java)
            startActivity(i)
//            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
//            finish()
        }

    }
}