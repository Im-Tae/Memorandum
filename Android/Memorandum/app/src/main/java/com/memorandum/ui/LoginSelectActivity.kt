package com.memorandum.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.memorandum.R
import com.memorandum.base.BaseActivity
import com.memorandum.contract.LoginSelectContract
import com.memorandum.presenter.LoginSelectPresenter
import com.memorandum.util.*
import kotlinx.android.synthetic.main.activity_login_select.*

class LoginSelectActivity : BaseActivity(), LoginSelectContract.View {

    override lateinit var presenter: LoginSelectContract.Presenter
    private lateinit var googleSignInClient : GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fade_none, R.anim.fade_none)
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

    override fun showToast(context: Context, message: String, type: String) = ToastMessage.toastMessage(context, message, type)

    override fun hideKeyboard() {}

    override fun onBackPressed() { finishAffinity() }

}
