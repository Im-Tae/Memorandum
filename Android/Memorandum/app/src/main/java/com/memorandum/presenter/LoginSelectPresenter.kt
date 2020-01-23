package com.memorandum.presenter

import android.content.Context
import android.content.Intent
import androidx.core.app.ActivityCompat.startActivityForResult
import com.google.android.gms.auth.api.Auth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.memorandum.contract.LoginSelectContract
import com.memorandum.ui.MainActivity
import com.memorandum.util.GetNetworkInfo
import com.memorandum.util.SharedPreferenceManager

class LoginSelectPresenter(override val view: LoginSelectContract.View) : LoginSelectContract.Presenter {

    override fun changeActivity(target: Class<*>) = view.startActivity(target)

    override fun googleSignIn(signInIntent : Intent) = view.startActivityForGoogleSignInResult(signInIntent)

    override fun googleLogin(context: Context, requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == 100) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result.isSuccess) {
                val account = result.signInAccount
                val credential = GoogleAuthProvider.getCredential(account?.idToken, null)

                FirebaseAuth.getInstance().signInWithCredential(credential)

                if (GetNetworkInfo.networkInfo(context)) {
                    SharedPreferenceManager.setUserId(context, account?.email.toString())
                    view.startActivity(MainActivity::class.java)
                    view.showToast("로그인 완료", "success")
                } else {
                    view.showToast("와이파이 연결을 확인해주세요.", "error")
                }
            }
        }
    }


}