package com.memorandum.ui

import android.content.Context
import android.os.Bundle
import com.memorandum.R
import com.memorandum.base.BaseActivity
import com.memorandum.contract.PasswordResetContract
import com.memorandum.presenter.PasswordResetPresenter
import com.memorandum.util.*
import kotlinx.android.synthetic.main.activity_password_reset.*

class PasswordResetActivity : BaseActivity(), PasswordResetContract.View {

    override lateinit var presenter: PasswordResetContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_reset)

        Animation.appearAnimation(this, reset_email, resetButton)

        presenter = PasswordResetPresenter(this, this)

        resetButton.setOnClickListener { presenter.resetPassword(reset_email.text.toString().trim(), reset_email) }
    }

    override fun hideKeyboard() = HideKeyboard.hideKeyboard(this.currentFocus, this)

    override fun showToast(context: Context, message: String, type: String) = ToastMessage.toastMessage(context, message, type)

    override fun startActivity(context: Context, target: Class<*>) {}
}
