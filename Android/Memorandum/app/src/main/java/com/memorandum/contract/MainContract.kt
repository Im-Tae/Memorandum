package com.memorandum.contract

import com.baoyz.widget.PullRefreshLayout
import com.memorandum.base.BasePresenter
import com.memorandum.base.BaseView
import com.memorandum.model.Memo

class MainContract {
    interface View: BaseView<Presenter> {

        fun setMemo(memoList: ArrayList<Memo>)
        fun finishAffinityActivity()
        fun layoutRefresh()
    }

    interface Presenter: BasePresenter<View> {

        var memoList: ArrayList<Memo>
        var lastTimeBackPressed: Long

        fun getMemo()
        fun logout()
        fun backPressed()
        fun refreshMemo(swipeRefreshLayout: PullRefreshLayout)
    }
}