package com.memorandum.view

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import com.google.firebase.firestore.FirebaseFirestore
import com.memorandum.R
import com.memorandum.util.SharedPreferenceManager
import kotlinx.android.synthetic.main.activity_write_memo.*

class WriteMemoActivity : AppCompatActivity() {

    var firestore: FirebaseFirestore? = null
    var memoList = arrayListOf<Memo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(
            R.anim.slide_up,
            R.anim.slide_up
        )
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_write_memo)

        title = ""
        firestore = FirebaseFirestore.getInstance()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.save, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId) {
            R.id.action_save -> {
                if (writeMemoContent.text.toString() != "" && checkMemo(writeMemoTitle.text.toString() + writeMemoContent.text.toString())) {
                    AddMemo()
                }
                finish()
                overridePendingTransition(
                    R.anim.slide_down,
                    R.anim.slide_down
                )
            }
        }

        return true
    }

    private fun AddMemo() {
        val memo = Memo(
            writeMemoTitle.text.toString(),
            writeMemoContent.text.toString()
        )
        firestore?.collection(SharedPreferenceManager.getUserId(this).toString())?.document(writeMemoTitle.text.toString() + writeMemoContent.text.toString())?.set(memo)
    }

    private fun checkMemo(memo: String): Boolean {
        for (i in memoList.indices) {
            if (memoList[i].title + memoList[i].content == memo) return false
        }
        return true
    }

    override fun onBackPressed() { }

}