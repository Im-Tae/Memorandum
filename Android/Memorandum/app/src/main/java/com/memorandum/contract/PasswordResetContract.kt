package com.memorandum.contract

import com.memorandum.base.BasePresenter
import com.memorandum.base.BaseView

class PasswordResetContract {
    interface View: BaseView<Presenter>

    interface Presenter: BasePresenter<View> {

        fun resetPassword(reset_email : String, reset_email_view: android.view.View)
    }
}