package com.memorandum.contract

import android.content.Context
import com.memorandum.base.BasePresenter
import com.memorandum.base.BaseView
import com.memorandum.model.Memo

class MainContract {
    interface View: BaseView<Presenter> {

        fun setMemo(memoList: ArrayList<Memo>)
        fun finishAffinityActivity()
    }

    interface Presenter: BasePresenter<View> {

        var memoList: ArrayList<Memo>
        var lastTimeBackPressed: Long

        fun getMemo(context: Context)
        fun logout(context: Context)
        fun backPressed(context: Context)
    }
}