package com.memorandum.contract

import android.content.Context

class LoginContract {
    interface View {
        val presenter: Presenter

        fun startPasswordResetActivity()
        fun hideKeyboard()
        fun showToast()

    }

    interface Presenter {
        val view: View

        fun login(context: Context, login_email: String, login_password: String, login_email_view: android.view.View, login_password_view : android.view.View)

    }
}