package com.memorandum.contract

import com.google.firebase.firestore.FirebaseFirestore
import com.memorandum.base.BasePresenter
import com.memorandum.base.BaseView
import com.memorandum.model.Memo

class WriteMemoContract {
    interface View: BaseView<Presenter>

    interface Presenter: BasePresenter<View> {
        var memoList: ArrayList<Memo>

        fun saveMemo(fireStore: FirebaseFirestore?, writeMemoTitle: String, writeMemoContent: String)
    }
}