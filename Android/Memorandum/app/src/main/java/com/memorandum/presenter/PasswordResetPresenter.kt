package com.memorandum.presenter

import android.content.Context
import android.view.View
import com.memorandum.contract.PasswordResetContract
import com.memorandum.util.CheckValid
import com.memorandum.util.FirebaseManager
import com.memorandum.util.GetNetworkInfo

class PasswordResetPresenter(override val view: PasswordResetContract.View) : PasswordResetContract.Presenter {

    override fun resetPassword(context: Context, reset_email : String, reset_email_view: View) {

        view.hideKeyboard()

        if (GetNetworkInfo.networkInfo(context))
            if (CheckValid.checkValid(context, reset_email, reset_email_view)) FirebaseManager.resetPassword(context, reset_email.trim())

         else view.showToast("와이파이 연결을 확인해주세요.", "error")

    }

}