package com.memorandum.user

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.AnimationUtils
import com.memorandum.MainActivity

import com.memorandum.utils.FirebaseManager.Companion.checkEmail
import com.memorandum.utils.FirebaseManager.Companion.loginUser
import com.memorandum.R
import com.memorandum.utils.AnimationUtil
import com.memorandum.utils.FirebaseManager
import com.memorandum.utils.HideKeyboard
import com.memorandum.utils.ToastMessage
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.activityManager
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_login)

        val animation = AnimationUtils.loadAnimation(this, R.anim.login_register_screen)


        login_email.startAnimation(animation)
        login_password.startAnimation(animation)
        loginButton.startAnimation(animation)
        changePasswordResetButton.startAnimation(animation)

        loginButton.setOnClickListener {

            HideKeyboard.hideKeyboard(this.currentFocus, this)

            if (login_email.text.toString() != "" && login_password.text.toString() != "") {
                if (checkEmail(login_email.text.toString().trim())) {

                    loginUser(applicationContext, login_email.text.toString().trim(), login_password.text.toString().trim())

                } else  {
                    AnimationUtil.animation(login_email, "Shake")
                    ToastMessage.toastMessage(this, "올바른 이메일을 입력해주세요.", "error")
                }
            } else {
                AnimationUtil.animation(login_email, "Shake")
                AnimationUtil.animation(login_password, "Shake")
                ToastMessage.toastMessage(this, "이메일 또는 비밀번호를 입력해주세요.", "error")
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
