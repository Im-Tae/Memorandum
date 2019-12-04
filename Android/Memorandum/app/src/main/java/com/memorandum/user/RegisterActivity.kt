package com.memorandum.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.AnimationUtils
import com.memorandum.R
import com.memorandum.util.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_register)

        val animation = AnimationUtils.loadAnimation(this, R.anim.appear)

        register_email.startAnimation(animation)
        register_password.startAnimation(animation)
        registerButton.startAnimation(animation)

        registerButton.setOnClickListener {

            HideKeyboard.hideKeyboard(this.currentFocus, this)

            if (GetNetworkInfo.networkInfo(this)) {
                if (CheckValidUtil.checkValid(this, register_email.text.toString().trim(), register_password.text.toString().trim(), register_email, register_password)) {
                    FirebaseManager.registerUser(this, register_email.text.toString().trim(), register_password.text.toString().trim())
                }
            } else {
                ToastMessage.toastMessage(this, "와이파이 연결을 확인해주세요.", "error")
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        return
    }
}
