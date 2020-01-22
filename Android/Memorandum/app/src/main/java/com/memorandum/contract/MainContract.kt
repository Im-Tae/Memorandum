package com.memorandum.contract

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.memorandum.model.Activities
import com.memorandum.model.Memo

class MainContract {
    interface View {
        val presenter: Presenter

        fun setMemo(memoList: ArrayList<Memo>)
        fun startWriteMemoActivity()
    }

    interface Presenter {
        val view: View
        var memoList: ArrayList<Memo>

        fun getMemo(context: Context)
        fun logout(context: Context)
        fun startActivity(activityName: Activities)
    }
}