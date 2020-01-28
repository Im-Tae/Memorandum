package com.memorandum.presenter

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import com.memorandum.contract.WriteMemoContract
import com.memorandum.model.Memo
import com.memorandum.util.SharedPreferenceManager

class WriteMemoPresenter(override val view: WriteMemoContract.View, override val context: Context) : WriteMemoContract.Presenter {

    override var memoList: ArrayList<Memo> = arrayListOf()

    override fun saveMemo(fireStore: FirebaseFirestore?, writeMemoTitle: String, writeMemoContent: String) {
        if (writeMemoContent != "" && checkMemo(writeMemoTitle + writeMemoContent))
            addMemo(fireStore, writeMemoTitle, writeMemoContent)
    }

    override fun changeActivity(target: Class<*>) {}

    private fun addMemo(fireStore: FirebaseFirestore?, writeMemoTitle: String, writeMemoContent: String) {

        val memo = Memo(writeMemoTitle, writeMemoContent)

        fireStore?.collection(SharedPreferenceManager.getUserId(context).toString())?.document(writeMemoTitle + writeMemoContent)?.set(memo)
    }

    private fun checkMemo(memo: String): Boolean {
        for (i in memoList.indices)
            if (memoList[i].title + memoList[i].content == memo) return false

        return true
    }
}