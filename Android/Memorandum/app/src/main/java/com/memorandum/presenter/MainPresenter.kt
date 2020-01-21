package com.memorandum.presenter

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import com.memorandum.contract.MainContract
import com.memorandum.util.SharedPreferenceManager
import com.memorandum.model.Memo

class MainPresenter(override val view: MainContract.View) : MainContract.Presenter {

    override var memoList: ArrayList<Memo> = arrayListOf()

    override fun getMemo(context: Context) {
        FirebaseFirestore.getInstance().collection(SharedPreferenceManager.getUserId(context).toString()).addSnapshotListener { querySnapshot, firebaseFirestoreException ->

            memoList.clear()
            if (querySnapshot != null) {
                for (item in querySnapshot.documents) {
                    var userDTO = item.toObject(Memo::class.java)
                    memoList.add(userDTO!!)
                }
            }
    }
        view.setMemo(memoList)
    }
}