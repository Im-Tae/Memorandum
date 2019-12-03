package com.memorandum.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.AnimationUtils
import com.memorandum.R
import com.memorandum.utils.AnimationUtil
import com.memorandum.utils.FirebaseManager
import com.memorandum.utils.HideKeyboard
import com.memorandum.utils.ToastMessage
import kotlinx.android.synthetic.main.activity_password_reset.*

class PasswordResetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_password_reset)

        val animation = AnimationUtils.loadAnimation(this, R.anim.login_register_screen)

        reset_email.startAnimation(animation)
        resetButton.startAnimation(animation)

        resetButton.setOnClickListener {

            HideKeyboard.hideKeyboard(this.currentFocus, this)

            if (reset_email.text.toString() != "") {
                if (FirebaseManager.checkEmail(reset_email.text.toString().trim())) {
                    FirebaseManager.resetPassword(this, reset_email.text.toString().trim())
                } else {
                    AnimationUtil.animation(reset_email, "Shake")
                    ToastMessage.toastMessage(this, "올바른 이메일을 입력해주세요.", "error")
                }
            } else {
                AnimationUtil.animation(reset_email, "Shake")
                ToastMessage.toastMessage(this, "이메일을 입력해주세요.", "error")
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        return
    }
}
