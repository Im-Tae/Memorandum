package com.memorandum.ui

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.memorandum.util.FirebaseManager.Companion.loginUser
import com.memorandum.R
import com.memorandum.contract.LoginContract
import com.memorandum.presenter.LoginPresenter
import com.memorandum.util.*
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity

class LoginActivity : AppCompatActivity(), LoginContract.View {

    override lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_login)

        Animation.appearAnimation(this, login_email, login_password, loginButton, changePasswordResetButton)

        presenter = LoginPresenter(this)

        loginButton.setOnClickListener { presenter.login(this, login_email.text.toString().trim(), login_password.text.toString().trim(), login_email, login_password) }

        changePasswordResetButton.setOnClickListener { startPasswordResetActivity() }
    }

    override fun hideKeyboard() = HideKeyboard.hideKeyboard(this.currentFocus, this)

    override fun startPasswordResetActivity() =  startActivity(Intent(this, PasswordResetActivity::class.java))

    override fun showToast() = ToastMessage.toastMessage(this, "와이파이 연결을 확인해주세요.", "error")

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        return
    }
}
