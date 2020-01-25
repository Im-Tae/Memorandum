package com.memorandum.contract

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore

class ShowAndEditMemoContract {
    interface View {
        val presenter: Presenter

        fun setDataForShowMemo(title: String?, content: String?)

        fun changeShowMemoEnable()
    }

    interface Presenter {
        val view: View

        fun saveMemo(context: Context, fireStore: FirebaseFirestore?, showMemoTitle: String, showMemoContent: String)

        fun editMemo()

        fun getDataForShowMemo()
    }
}