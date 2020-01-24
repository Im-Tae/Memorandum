package com.memorandum.contract

import android.content.Context

class PasswordResetContract {
    interface View {
        val presenter: Presenter

        fun hideKeyboard()
        fun showToast(message: String, type: String)
    }

    interface Presenter {
        val view: View

        fun resetPassword(context: Context, reset_email : String, reset_email_view: android.view.View)
    }
}