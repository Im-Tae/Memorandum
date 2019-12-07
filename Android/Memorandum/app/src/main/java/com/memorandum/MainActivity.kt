package com.memorandum

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import com.memorandum.util.FirebaseManager
import com.memorandum.util.ToastMessage
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    var memoList = arrayListOf<Memo>(
        Memo("테스트", "테스트입니다."),
        Memo("테스트", "테스트입니다."),
        Memo("테스트", "테스트입니다."),
        Memo("테스트", "테스트입니다.")

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        val memoAdapter = MemoAdapter(this, memoList)
        recyclerView.adapter = memoAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add, menu)
        menuInflater.inflate(R.menu.logout, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId) {
            R.id.action_addMemo -> {
                startActivity<WriteMemoActivity>()
            }

            R.id.action_logout -> {
                FirebaseManager.logout(this)
            }
        }

        return true
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
