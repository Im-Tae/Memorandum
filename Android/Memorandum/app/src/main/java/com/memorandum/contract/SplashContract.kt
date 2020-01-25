package com.memorandum.contract

class SplashContract {
    interface View {
        val presenter: Presenter

    }

    interface Presenter {
        val view: View

    }
}