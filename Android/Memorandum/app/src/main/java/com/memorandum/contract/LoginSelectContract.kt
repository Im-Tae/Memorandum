package com.memorandum.contract

import android.content.Intent
import com.memorandum.base.BasePresenter
import com.memorandum.base.BaseView

class LoginSelectContract {
    interface View: BaseView<Presenter> {

        fun startActivityForGoogleSignInResult(signInIntent: Intent)
    }

    interface Presenter: BasePresenter<View> {

        fun googleLogin(requestCode: Int, resultCode: Int, data: Intent?)
        fun googleSignIn(signInIntent : Intent)
    }
}