package com.memorandum.contract

class PasswordResetContract {
    interface View {
        val presenter: Presenter
    }

    interface Presenter {
        val view: View


    }
}