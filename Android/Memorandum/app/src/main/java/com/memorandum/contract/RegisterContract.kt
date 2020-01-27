package com.memorandum.contract

import android.content.Context
import com.memorandum.base.BasePresenter
import com.memorandum.base.BaseView

class RegisterContract {
    interface View: BaseView<Presenter>

    interface Presenter: BasePresenter<View> {

        fun register(register_email: String, register_password: String, register_email_view: android.view.View, register_password_view: android.view.View)
    }
}