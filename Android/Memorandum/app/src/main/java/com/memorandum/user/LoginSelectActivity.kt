package com.memorandum.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.AnimationUtils
import com.memorandum.R
import com.memorandum.utils.ToastMessage
import kotlinx.android.synthetic.main.activity_login_select.*
import org.jetbrains.anko.startActivity

class LoginSelectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_login_select)

        var animation = AnimationUtils.loadAnimation(this, R.anim.login_register_screen)


        changeLoginButton.startAnimation(animation)
        changeRegisterButton.startAnimation(animation)
        goolgeLoginButton.startAnimation(animation)
        text.startAnimation(animation)


        changeLoginButton.setOnClickListener {
            startActivity<LoginActivity>()
        }

        changeRegisterButton.setOnClickListener {
            startActivity<RegisterActivity>()
        }

    }

    private var lastTimeBackPressed: Long = 0

    override fun onBackPressed() {

        if (System.currentTimeMillis() - lastTimeBackPressed < 2500) {
            finishAffinity()
        } else {
            ToastMessage.toastMessage(this, "한번 더 누르면 종료합니다.", "info")
            lastTimeBackPressed = System.currentTimeMillis()
        }
    }

}
