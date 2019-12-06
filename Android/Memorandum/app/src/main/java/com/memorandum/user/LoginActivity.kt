package com.memorandum.user

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.memorandum.util.FirebaseManager.Companion.loginUser
import com.memorandum.R
import com.memorandum.util.*
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_login)

        Animation.appearAnimation(this, login_email, login_password, loginButton, changePasswordResetButton)

        loginButton.setOnClickListener {

            HideKeyboard.hideKeyboard(this.currentFocus, this)

            if (GetNetworkInfo.networkInfo(this)) {
                if (CheckValid.checkValid(this, login_email.text.toString().trim(), login_password.text.toString().trim() , login_email, login_password)) {
                    loginUser(applicationContext, login_email.text.toString().trim(), login_password.text.toString().trim())
                }
            } else {
                ToastMessage.toastMessage(this, "와이파이 연결을 확인해주세요.", "error")
            }
        }

        changePasswordResetButton.setOnClickListener {
            startActivity<PasswordResetActivity>()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        return
    }
}
