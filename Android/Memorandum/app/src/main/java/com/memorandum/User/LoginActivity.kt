package com.memorandum.User

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.AnimationUtils
import com.google.firebase.auth.FirebaseAuth
import com.memorandum.FirebaseManager
import com.memorandum.FirebaseManager.Companion.loginUser
import com.memorandum.R
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_login)

        var animation = AnimationUtils.loadAnimation(this, R.anim.loginscreen)

        login_email.startAnimation(animation)
        login_password.startAnimation(animation)
        loginButton.startAnimation(animation)
        registerButton.startAnimation(animation)

        loginButton.setOnClickListener {
            loginUser(this, login_email.text.toString(), login_password.text.toString())
        }



    }
}
