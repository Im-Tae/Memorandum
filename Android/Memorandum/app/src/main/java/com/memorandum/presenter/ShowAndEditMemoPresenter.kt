package com.memorandum.presenter

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import com.memorandum.contract.ShowAndEditMemoContract
import com.memorandum.model.Memo
import com.memorandum.util.DataSingleton
import com.memorandum.util.SharedPreferenceManager

class ShowAndEditMemoPresenter(override val view: ShowAndEditMemoContract.View, override val context: Context) : ShowAndEditMemoContract.Presenter {

    override fun editMemo() = view.changeShowMemoEnable()

    override fun getDataForShowMemo() {
        if (DataSingleton.getInstance()?.content != "")
            view.setDataForShowMemo(DataSingleton.getInstance()?.title, DataSingleton.getInstance()?.content)
    }

    override fun saveMemo(fireStore: FirebaseFirestore?, showMemoTitle: String, showMemoContent: String) {
        if(DataSingleton.getInstance()?.title != showMemoTitle || DataSingleton.getInstance()?.content != showMemoContent)
            updateMemo(fireStore, showMemoTitle, showMemoContent)
    }

    override fun changeActivity(target: Class<*>) {}

    private fun updateMemo(fireStore: FirebaseFirestore?, showMemoTitle: String, showMemoContent: String)
    {
        fireStore
            ?.collection(SharedPreferenceManager.getUserId(context).toString())
            ?.document(DataSingleton.getInstance()?.title + DataSingleton.getInstance()?.content)
            ?.delete()
            ?.addOnCompleteListener {

            val memo = Memo(showMemoTitle, showMemoContent)

            fireStore
                .collection(SharedPreferenceManager.getUserId(context).toString())
                .document(showMemoTitle + showMemoContent)
                .set(memo)
        }
    }

}