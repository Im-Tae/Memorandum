package com.memorandum.contract

import com.memorandum.base.BasePresenter
import com.memorandum.base.BaseView

class LoginContract {
    interface View: BaseView<Presenter>

    interface Presenter: BasePresenter<View> {

        fun login(login_email: String, login_password: String, login_email_view: android.view.View, login_password_view : android.view.View)
    }
}