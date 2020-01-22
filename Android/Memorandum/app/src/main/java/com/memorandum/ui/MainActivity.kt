package com.memorandum.ui

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import com.google.firestore.v1.Write
import com.memorandum.R
import com.memorandum.contract.MainContract
import com.memorandum.presenter.MainPresenter
import com.memorandum.util.FirebaseManager
import com.memorandum.util.ToastMessage
import com.memorandum.model.Memo
import com.memorandum.adapter.MemoAdapter
import com.memorandum.model.Activities
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), MainContract.View {

    override lateinit var presenter : MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this)

        presenter.getMemo(this)

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.postDelayed( Runnable { swipeRefreshLayout.setRefreshing(false) }, 1000)
            presenter.getMemo(this)
        }
    }

    override fun setMemo(memoList: ArrayList<Memo>) {
        recyclerView.adapter?.notifyDataSetChanged()
        val memoAdapter = MemoAdapter(this, memoList)
        recyclerView.adapter = memoAdapter
    }

    override fun startWriteMemoActivity() = startActivity(Intent(this, WriteMemoActivity::class.java))

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add, menu)
        menuInflater.inflate(R.menu.logout, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId) {
            R.id.action_addMemo -> presenter.startActivity(Activities.WriteMemoActivity)

            R.id.action_logout -> presenter.logout(this)
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
