package com.memorandum.contract

class ShowAndEditMemoContract {
    interface View {
        val presenter: Presenter
    }

    interface Presenter {
        val view: View
    }
}