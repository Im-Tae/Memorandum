package com.memorandum.presenter

import android.content.Context
import android.text.Layout
import android.view.View
import com.memorandum.contract.LoginContract
import com.memorandum.util.*

class LoginPresenter(override val view: LoginContract.View) : LoginContract.Presenter {

    override fun login(context: Context, login_email: String, login_password: String, login_email_view: View, login_password_view : View) {

        view.hideKeyboard()

        if (GetNetworkInfo.networkInfo(context)) {

            if (CheckValid.checkValid(context, login_email, login_password, login_email_view, login_password_view))
                FirebaseManager.loginUser(context, login_email, login_password )

        } else view.showToast()
    }

    override fun changeActivity(target: Class<*>) = view.startActivity(target)

}