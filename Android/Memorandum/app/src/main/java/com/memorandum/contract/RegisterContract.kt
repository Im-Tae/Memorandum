package com.memorandum.contract

import android.content.Context

class RegisterContract {
    interface View {
        val presenter: Presenter


        fun hideKeyboard()
        fun showToast(message: String, type: String)
    }

    interface Presenter {
        val view: View

        fun register(context: Context, register_email: String, register_password: String, register_email_view: android.view.View, register_password_view: android.view.View)
    }
}