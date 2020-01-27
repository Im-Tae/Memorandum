package com.memorandum.contract

import com.memorandum.base.BasePresenter
import com.memorandum.base.BaseView

class SplashContract {
    interface View: BaseView<Presenter> {

        fun showAnimation()
        fun finishActivity()
    }

    interface Presenter: BasePresenter<View> {

        fun splash()
    }
}