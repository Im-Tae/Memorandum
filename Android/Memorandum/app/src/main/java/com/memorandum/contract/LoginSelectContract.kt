package com.memorandum.contract

import android.content.Context
import android.content.Intent

class LoginSelectContract {
    interface View {
        val presenter: Presenter

        fun showToast(message: String, type: String)
        fun startActivity(target: Class<*>)
        fun startActivityForGoogleSignInResult(signInIntent: Intent)

    }

    interface Presenter {
        val view: View

        fun changeActivity(target: Class<*>)
        fun googleLogin(context: Context, requestCode: Int, resultCode: Int, data: Intent?)
        fun googleSignIn(signInIntent : Intent)
    }
}