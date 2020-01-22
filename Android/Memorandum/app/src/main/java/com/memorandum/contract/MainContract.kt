package com.memorandum.contract

import android.content.Context
import com.memorandum.model.Memo

class MainContract {
    interface View {
        val presenter: Presenter

        fun setMemo(memoList: ArrayList<Memo>)
        fun startActivity(target: Class<*>)
    }

    interface Presenter {
        val view: View
        var memoList: ArrayList<Memo>

        fun getMemo(context: Context)
        fun logout(context: Context)
        fun changeActivity(target: Class<*>)
    }
}