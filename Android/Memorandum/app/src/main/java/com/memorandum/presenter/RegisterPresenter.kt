package com.memorandum.presenter

import android.content.Context
import android.view.View
import com.memorandum.contract.RegisterContract
import com.memorandum.util.CheckValid
import com.memorandum.util.FirebaseManager
import com.memorandum.util.GetNetworkInfo

class RegisterPresenter(override val view: RegisterContract.View) : RegisterContract.Presenter {

    override fun register(context: Context, register_email: String, register_password: String, register_email_view: View, register_password_view: View) {

        view.hideKeyboard()

        if (GetNetworkInfo.networkInfo(context))
            if (CheckValid.checkValid(context, register_email, register_password, register_email_view, register_password_view))
                FirebaseManager.registerUser(context, register_email, register_password)

        else view.showToast("와이파이 연결을 확인해주세요.", "error")
    }
}