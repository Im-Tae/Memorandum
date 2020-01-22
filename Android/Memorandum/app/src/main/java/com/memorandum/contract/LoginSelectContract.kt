package com.memorandum.contract

class LoginSelectContract {
    interface View {
        val presenter: Presenter

        fun startLoginActivity()

        fun startRegisterActivity()

    }

    interface Presenter {
        val view: View

    }
}