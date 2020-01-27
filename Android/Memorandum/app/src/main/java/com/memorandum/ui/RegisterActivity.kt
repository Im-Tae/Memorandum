package com.memorandum.ui

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.WindowManager
import com.memorandum.R
import com.memorandum.base.BaseActivity
import com.memorandum.contract.RegisterContract
import com.memorandum.presenter.RegisterPresenter
import com.memorandum.util.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity(), RegisterContract.View {

    override lateinit var presenter: RegisterContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_register)

        Animation.appearAnimation(this, register_email, register_password, registerButton)

        presenter = RegisterPresenter(this, this)

        registerButton.setOnClickListener { presenter.register(register_email.text.toString().trim(), register_password.text.toString().trim(), register_email, register_password) }
    }

    override fun hideKeyboard() = HideKeyboard.hideKeyboard(this.currentFocus, this)

    override fun showToast(context: Context, message: String, type: String) = ToastMessage.toastMessage(context, message, type)

    override fun startActivity(target: Class<*>) {}

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        return
    }
}
