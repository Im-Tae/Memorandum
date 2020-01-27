package com.memorandum.contract

import com.memorandum.base.BasePresenter
import com.memorandum.base.BaseView

class WriteMemoContract {
    interface View: BaseView<Presenter> {

    }

    interface Presenter: BasePresenter<View> {

    }
}