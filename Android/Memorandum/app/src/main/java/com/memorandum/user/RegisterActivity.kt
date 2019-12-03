package com.memorandum.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.AnimationUtils
import com.memorandum.utils.FirebaseManager
import com.memorandum.utils.ToastMessage
import com.memorandum.R
import com.memorandum.utils.AnimationUtil
import com.memorandum.utils.HideKeyboard
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_register)

        val animation = AnimationUtils.loadAnimation(this, R.anim.login_register_screen)

        register_email.startAnimation(animation)
        register_password.startAnimation(animation)
        registerButton.startAnimation(animation)

        registerButton.setOnClickListener {

            HideKeyboard.hideKeyboard(this.currentFocus, this)

            if (register_email.text.toString() != "" && register_password.text.toString() != "") {
                if (FirebaseManager.checkEmail(register_email.text.toString().trim())) {
                    FirebaseManager.registerUser(this, register_email.text.toString().trim(), register_password.text.toString().trim())
                } else  {
                    AnimationUtil.animation(register_email, "Shake")
                    ToastMessage.toastMessage(this, "올바른 이메일을 입력해주세요.", "error")
                }
            } else {
                AnimationUtil.animation(register_email, "Shake")
                AnimationUtil.animation(register_password, "Shake")
                ToastMessage.toastMessage(this, "이메일 또는 비밀번호를 입력해주세요.", "error")
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        return
    }
}
