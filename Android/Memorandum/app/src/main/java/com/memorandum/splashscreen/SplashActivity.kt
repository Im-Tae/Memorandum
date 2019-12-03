package com.memorandum.splashscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.AnimationUtils
import com.memorandum.R
import com.memorandum.user.LoginActivity
import com.memorandum.user.LoginSelectActivity
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)

        var textAnimation = AnimationUtils.loadAnimation(this, R.anim.text)
        var layoutAnimation = AnimationUtils.loadAnimation(this, R.anim.background)

        val splashScreen = object : Thread() {
            override fun run() {
                titleText.startAnimation(textAnimation)
                headingText.startAnimation(textAnimation)
                splashlayout.startAnimation(layoutAnimation)

                sleep(1000) //4000
                if (true) {
                    startActivity<LoginSelectActivity>()
                } else {
                    startActivity<LoginActivity>()
                }
                finish()
            }
        }
        splashScreen.start()

    }
}
