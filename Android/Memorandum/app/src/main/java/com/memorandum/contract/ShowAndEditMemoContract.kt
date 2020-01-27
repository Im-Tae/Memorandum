package com.memorandum.contract

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import com.memorandum.base.BasePresenter
import com.memorandum.base.BaseView

class ShowAndEditMemoContract {
    interface View: BaseView<Presenter> {

        fun setDataForShowMemo(title: String?, content: String?)

        fun changeShowMemoEnable()
    }

    interface Presenter: BasePresenter<View> {

        fun saveMemo(fireStore: FirebaseFirestore?, showMemoTitle: String, showMemoContent: String)

        fun editMemo()

        fun getDataForShowMemo()
    }
}