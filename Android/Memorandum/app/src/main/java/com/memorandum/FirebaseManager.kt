package com.memorandum

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.memorandum.User.LoginActivity

class FirebaseManager {
    companion object {

        private var auth: FirebaseAuth = FirebaseAuth.getInstance()
        var user = auth.currentUser

        fun createUser(context: Context, email: String, password: String) {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "회원가입 되었습니다.", Toast.LENGTH_SHORT).show()
                    verificationEmail()
                    var intent = Intent(context, LoginActivity::class.java)
                    context.startActivity(intent)

                } else {
                    Toast.makeText(context, "회원가입 실패", Toast.LENGTH_SHORT).show()
                }
            }
        }

        fun loginUser(context: Context, email: String, password: String) {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(context, "로그인 되었습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "로그인 실패", Toast.LENGTH_SHORT).show()
                }
            }
        }

        private fun verificationEmail() {
            user?.sendEmailVerification()?.addOnCompleteListener {
                    if (it.isSuccessful) {
                        user = auth.currentUser
                    }
                }
        }

    }
}
