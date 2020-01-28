package com.memorandum.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.AnimationUtils
import com.memorandum.R
import com.memorandum.contract.SplashContract
import com.memorandum.presenter.SplashPresenter
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), SplashContract.View {

    override lateinit var presenter: SplashContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)

        presenter = SplashPresenter(this, this)

        presenter.splash()
    }

    override fun showAnimation() {

        val textAnimation = AnimationUtils.loadAnimation(this, R.anim.text)
        val layoutAnimation = AnimationUtils.loadAnimation(this, R.anim.background)

        titleText.startAnimation(textAnimation)
        headingText.startAnimation(textAnimation)
        splashlayout.startAnimation(layoutAnimation)
    }

    override fun hideKeyboard() {}

    override fun finishActivity() = finish()

    override fun startActivity(context: Context, target: Class<*>) = startActivity(Intent(context, target))
}
