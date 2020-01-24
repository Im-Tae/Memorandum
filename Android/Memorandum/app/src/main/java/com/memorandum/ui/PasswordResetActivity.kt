package com.memorandum.ui

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.memorandum.R
import com.memorandum.contract.PasswordResetContract
import com.memorandum.presenter.PasswordResetPresenter
import com.memorandum.util.*
import kotlinx.android.synthetic.main.activity_password_reset.*

class PasswordResetActivity : AppCompatActivity(), PasswordResetContract.View {

    override lateinit var presenter: PasswordResetContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_password_reset)

        Animation.appearAnimation(this, reset_email, resetButton)

        presenter = PasswordResetPresenter(this)

        resetButton.setOnClickListener { presenter.resetPassword(this, reset_email.text.toString().trim(), reset_email) }
    }

    override fun hideKeyboard() = HideKeyboard.hideKeyboard(this.currentFocus, this)

    override fun showToast(message: String, type: String) = ToastMessage.toastMessage(this, message, type)


    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        return
    }
}
