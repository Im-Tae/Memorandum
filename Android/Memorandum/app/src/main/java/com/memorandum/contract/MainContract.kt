package com.memorandum.contract

import android.content.Context
import com.memorandum.model.Memo

class MainContract {
    interface View {
        val presenter: Presenter

        fun setMemo(memoList: ArrayList<Memo>)
    }

    interface Presenter {
        val view: View
        var memoList: ArrayList<Memo>

        fun getMemo(context: Context)
    }
}