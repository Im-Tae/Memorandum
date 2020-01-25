package com.memorandum.contract

import android.content.Context
import com.memorandum.base.BasePresenter
import com.memorandum.base.BaseView

class LoginContract {
    interface View: BaseView<Presenter>

    interface Presenter: BasePresenter<View> {

        fun login(context: Context, login_email: String, login_password: String, login_email_view: android.view.View, login_password_view : android.view.View)
    }
}