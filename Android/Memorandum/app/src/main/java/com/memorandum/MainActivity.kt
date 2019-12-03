package com.memorandum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.memorandum.utils.FirebaseManager
import com.memorandum.utils.ToastMessage
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ttext.text = FirebaseManager.user.toString()

    }

    private var lastTimeBackPressed: Long = 0

    override fun onBackPressed() {

        if (System.currentTimeMillis() - lastTimeBackPressed < 2500) {
            finishAffinity()
        } else {
            ToastMessage.toastMessage(this, "한번 더 누르면 종료합니다.", "info")
            lastTimeBackPressed = System.currentTimeMillis()
        }
    }
}
