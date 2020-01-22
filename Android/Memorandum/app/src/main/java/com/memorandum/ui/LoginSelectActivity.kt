package com.memorandum.ui

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.memorandum.R
import com.memorandum.contract.LoginSelectContract
import com.memorandum.presenter.LoginSelectPresenter
import com.memorandum.util.*
import kotlinx.android.synthetic.main.activity_login_select.*
import org.jetbrains.anko.startActivity

class LoginSelectActivity : AppCompatActivity(), LoginSelectContract.View {

    override lateinit var presenter: LoginSelectContract.Presenter
    private lateinit var googleSignedInClient : GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fade_none, R.anim.fade_none)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_login_select)

        presenter = LoginSelectPresenter(this)

        val googleSignInOptions= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()

        googleSignedInClient = GoogleSignIn.getClient(this,googleSignInOptions)

        Animation.appearAnimation(this, changeLoginButton, changeRegisterButton, goolgeLoginButton, text)

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
        val signInIntent = googleSignedInClient.signInIntent
        startActivityForResult(signInIntent,100)
    }

    override fun startLoginActivity() = startActivity(Intent(this, LoginActivity::class.java))

    override fun startRegisterActivity() = startActivity(Intent(this, RegisterActivity::class.java))

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result.isSuccess) {
                val account = result.signInAccount
                val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
                FirebaseAuth.getInstance().signInWithCredential(credential)
                if (GetNetworkInfo.networkInfo(this)) {
                    SharedPreferenceManager.setUserId(this, account?.email.toString())
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
