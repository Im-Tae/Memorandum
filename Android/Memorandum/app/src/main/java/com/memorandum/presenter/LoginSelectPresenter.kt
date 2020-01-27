package com.memorandum.presenter

import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.Auth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.memorandum.contract.LoginSelectContract
import com.memorandum.ui.MainActivity
import com.memorandum.util.GetNetworkInfo
import com.memorandum.util.SharedPreferenceManager

class LoginSelectPresenter(override val view: LoginSelectContract.View, override val context: Context) : LoginSelectContract.Presenter {

    override fun changeActivity(target: Class<*>) = view.startActivity(context, target)

    override fun googleSignIn(signInIntent : Intent) = view.startActivityForGoogleSignInResult(signInIntent)

    override fun googleLogin(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == 100) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result.isSuccess) {
                val account = result.signInAccount
                val credential = GoogleAuthProvider.getCredential(account?.idToken, null)

                FirebaseAuth
                    .getInstance()
                    .signInWithCredential(credential)

                if (GetNetworkInfo.networkInfo(context)) {
                    SharedPreferenceManager.setUserId(context, account?.email.toString())
                    view.startActivity(context, MainActivity::class.java)
                    view.showToast(context,"로그인 완료", "success")

                } else view.showToast(context, "와이파이 연결을 확인해주세요.", "error")
            }
        }
    }
}