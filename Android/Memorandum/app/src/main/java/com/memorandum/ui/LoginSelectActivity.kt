package com.memorandum.ui

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.memorandum.R
import com.memorandum.contract.LoginSelectContract
import com.memorandum.presenter.LoginSelectPresenter
import com.memorandum.util.*
import kotlinx.android.synthetic.main.activity_login_select.*

class LoginSelectActivity : AppCompatActivity(), LoginSelectContract.View {

    override lateinit var presenter: LoginSelectContract.Presenter
    private lateinit var googleSignInClient : GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fade_none, R.anim.fade_none)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_login_select)

        val googleSignInOptions= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()

        googleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions)

        presenter = LoginSelectPresenter(this)

        Animation.appearAnimation(this, changeLoginButton, changeRegisterButton, goolgeLoginButton, text)

        changeLoginButton.setOnClickListener { presenter.changeActivity(LoginActivity::class.java) }

        changeRegisterButton.setOnClickListener { presenter.changeActivity(RegisterActivity::class.java) }

        goolgeLoginButton.setOnClickListener { presenter.googleSignIn(googleSignInClient.signInIntent) }
    }

    override fun startActivityForGoogleSignInResult(signInIntent: Intent) = startActivityForResult(signInIntent,100)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) = presenter.googleLogin(this, requestCode, resultCode, data)

    override fun startActivity(target: Class<*>) = startActivity(Intent(this, target))

    override fun showToast(message: String, type: String) = ToastMessage.toastMessage(this, message, type)

    override fun onBackPressed() { finishAffinity() }

}
