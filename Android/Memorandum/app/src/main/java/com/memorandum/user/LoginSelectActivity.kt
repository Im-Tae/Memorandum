package com.memorandum.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.AnimationUtils
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.memorandum.MainActivity
import com.memorandum.R
import com.memorandum.util.GetNetworkInfo
import com.memorandum.util.ToastMessage
import kotlinx.android.synthetic.main.activity_login_select.*
import org.jetbrains.anko.startActivity

class LoginSelectActivity : AppCompatActivity() {

    lateinit var auth : FirebaseAuth
    lateinit var authListener : FirebaseAuth.AuthStateListener
    lateinit var googleSigneInClient : GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fade_none, R.anim.fade_none)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_login_select)

        var animation = AnimationUtils.loadAnimation(this, R.anim.appear)


        var googleSignInOptions=GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSigneInClient = GoogleSignIn.getClient(this,googleSignInOptions)


        changeLoginButton.startAnimation(animation)
        changeRegisterButton.startAnimation(animation)
        goolgeLoginButton.startAnimation(animation)
        text.startAnimation(animation)


        changeLoginButton.setOnClickListener {
            startActivity<LoginActivity>()
        }

        changeRegisterButton.setOnClickListener {
            startActivity<RegisterActivity>()
        }

        goolgeLoginButton.setOnClickListener {
            googleSignIn()
        }
    }

    private fun googleSignIn(){
        val signInIntent = googleSigneInClient.signInIntent
        startActivityForResult(signInIntent,100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result.isSuccess) {
                val account = result.signInAccount
                val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
                FirebaseAuth.getInstance().signInWithCredential(credential)
                if (GetNetworkInfo.networkInfo(this)) {
                    startActivity<MainActivity>()
                    ToastMessage.toastMessage(this, "로그인 완료", "success")
                } else {
                    ToastMessage.toastMessage(this, "와이파이 연결을 확인해주세요.", "error")
                }
            }
        }
    }


    override fun onBackPressed() { finishAffinity() }

}
