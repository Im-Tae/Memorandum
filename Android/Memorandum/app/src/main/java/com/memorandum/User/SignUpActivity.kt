package com.memorandum.User

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.memorandum.FirebaseManager
import com.memorandum.R
import kotlinx.android.synthetic.main.activity_sign_up.button
import kotlinx.android.synthetic.main.activity_sign_up.email
import kotlinx.android.synthetic.main.activity_sign_up.password

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_sign_up)

    }
}
