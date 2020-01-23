package com.memorandum.contract

import android.content.Context

class LoginContract {
    interface View {
        val presenter: Presenter

        fun hideKeyboard()
        fun showToast(message: String, type: String)
        fun startActivity(target: Class<*>)

    }

    interface Presenter {
        val view: View

        fun login(context: Context, login_email: String, login_password: String, login_email_view: android.view.View, login_password_view : android.view.View)
        fun changeActivity(target: Class<*>)

    }
}