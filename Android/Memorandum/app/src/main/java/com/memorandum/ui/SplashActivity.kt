package com.memorandum.ui

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.AnimationUtils
import com.memorandum.R
import com.memorandum.contract.SplashContract
import com.memorandum.presenter.SplashPresenter
import com.memorandum.util.SharedPreferenceManager
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), SplashContract.View {

    override lateinit var presenter: SplashContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)

        presenter = SplashPresenter(this)

        val textAnimation = AnimationUtils.loadAnimation(this, R.anim.text)
        val layoutAnimation = AnimationUtils.loadAnimation(this, R.anim.background)

        val splashScreen = object : Thread() {
            override fun run() {
                titleText.startAnimation(textAnimation)
                headingText.startAnimation(textAnimation)
                splashlayout.startAnimation(layoutAnimation)

                sleep(3000)
                if (SharedPreferenceManager.getUserId(applicationContext) != "") {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                } else {
                    startActivity(Intent(applicationContext, LoginSelectActivity::class.java))
                }
                finish()
            }
        }
        splashScreen.start()

    }
}
