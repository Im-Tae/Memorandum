package com.memorandum.ui

import android.content.Intent
import android.os.Bundle
import com.memorandum.R
import com.memorandum.base.BaseActivity
import com.memorandum.contract.LoginContract
import com.memorandum.presenter.LoginPresenter
import com.memorandum.util.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginContract.View {

    override lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Animation.appearAnimation(this, login_email, login_password, loginButton, changePasswordResetButton)

        presenter = LoginPresenter(this)

        loginButton.setOnClickListener { presenter.login(this, login_email.text.toString().trim(), login_password.text.toString().trim(), login_email, login_password) }

        changePasswordResetButton.setOnClickListener { presenter.changeActivity(PasswordResetActivity::class.java) }
    }

    override fun hideKeyboard() = HideKeyboard.hideKeyboard(this.currentFocus, this)

    override fun startActivity(target: Class<*>) =  startActivity(Intent(this, target))

    override fun showToast(message: String, type: String) = ToastMessage.toastMessage(this, message, type)
}
