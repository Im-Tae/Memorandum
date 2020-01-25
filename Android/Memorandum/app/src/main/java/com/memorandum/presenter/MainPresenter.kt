package com.memorandum.presenter

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import com.memorandum.contract.MainContract
import com.memorandum.util.SharedPreferenceManager
import com.memorandum.model.Memo
import com.memorandum.util.FirebaseManager

class MainPresenter(override val view: MainContract.View) : MainContract.Presenter {

    override var memoList: ArrayList<Memo> = arrayListOf()
    override var lastTimeBackPressed: Long = 0

    override fun getMemo(context: Context) {
        FirebaseFirestore
            .getInstance()
            .collection(SharedPreferenceManager.getUserId(context).toString())
            .addSnapshotListener { querySnapshot, _ ->

            memoList.clear()
            if (querySnapshot != null) {
                for (item in querySnapshot.documents) {
                    val userDTO = item.toObject(Memo::class.java)
                    memoList.add(userDTO!!)
                }
            }
    }
        view.setMemo(memoList)
    }

    override fun backPressed(context: Context) {

        if (System.currentTimeMillis() - lastTimeBackPressed < 2500)
            view.finishAffinityActivity()
        else {
            view.showToast(context, "한번 더 누르면 종료합니다.", "info")
            lastTimeBackPressed = System.currentTimeMillis()
        }
    }

    override fun changeActivity(target: Class<*>) = view.startActivity(target)

    override fun logout(context: Context) = FirebaseManager.logout(context)
}