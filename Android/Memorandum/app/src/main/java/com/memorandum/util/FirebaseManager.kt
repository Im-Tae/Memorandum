package com.memorandum.util

import android.content.Context
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.memorandum.MainActivity
import com.memorandum.user.LoginSelectActivity

class FirebaseManager {
    companion object {

        private var auth: FirebaseAuth = FirebaseAuth.getInstance()
        var user : FirebaseUser? = null
        lateinit var intent: Intent

        fun registerUser(context: Context, email: String, password: String) {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    ToastMessage.toastMessage(context, "회원가입 완료", "success")
                    user = auth.currentUser
                    intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                } else {
                    ToastMessage.toastMessage(context, "이미 가입되어 있습니다.", "error")
                }
            }
        }

        fun loginUser(context: Context, email: String, password: String) {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        ToastMessage.toastMessage(context, "로그인 완료", "success")
                        user = auth.currentUser
                        SharedPreferenceManager.setUserId(context, auth.currentUser.toString())
                        intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                    } else {
                        ToastMessage.toastMessage(context, "이메일 또는 비밀번호가 다릅니다.", "error")
                    }
                }
        }

        fun resetPassword(context: Context, email: String) {
            auth.sendPasswordResetEmail(email).addOnCompleteListener {
                if (it.isSuccessful) {
                    ToastMessage.toastMessage(context, "메일이 전송되었습니다.", "success")
                    intent = Intent(context, LoginSelectActivity::class.java)
                    context.startActivity(intent)
                } else {
                    ToastMessage.toastMessage(context, "가입 되어 있지 않은 이메일입니다.", "error")
                }
            }
        }
    }
}
