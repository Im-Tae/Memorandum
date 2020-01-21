package com.memorandum.ui

import  android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import com.google.firebase.firestore.FirebaseFirestore
import com.memorandum.R
import com.memorandum.util.DataSingleton
import com.memorandum.util.SharedPreferenceManager
import com.memorandum.model.Memo
import kotlinx.android.synthetic.main.activity_show_and_edit_memo.*

class ShowAndEditMemoActivity : AppCompatActivity() {

    var firestore: FirebaseFirestore? = null
    var memoList = arrayListOf<Memo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(
            R.anim.slide_up,
            R.anim.slide_up
        )
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_show_and_edit_memo)

        title = ""
        firestore = FirebaseFirestore.getInstance()

        if (DataSingleton.getInstance()?.content != "") {
            showMemoTitle.setText(DataSingleton.getInstance()?.title)
            showMemoContent.setText(DataSingleton.getInstance()?.content)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit, menu)
        menuInflater.inflate(R.menu.save, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId) {
            R.id.action_edit -> {
                showMemoTitle.isEnabled = true
                showMemoContent.isEnabled = true
            }

            R.id.action_save -> {
                if(DataSingleton.getInstance()?.title != showMemoTitle.text.toString() || DataSingleton.getInstance()?.content != showMemoContent.text.toString()) {
                    UpdateMemo()
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

    private fun UpdateMemo() {

        firestore?.collection(SharedPreferenceManager.getUserId(this).toString())?.document(DataSingleton.getInstance()?.title + DataSingleton.getInstance()?.content)?.delete()?.addOnCompleteListener {

            val memo = Memo(
                showMemoTitle.text.toString(),
                showMemoContent.text.toString()
            )

            firestore?.collection(SharedPreferenceManager.getUserId(this).toString())?.document(showMemoTitle.text.toString() + showMemoContent.text.toString())
                ?.set(memo)
        }
    }

    override fun onBackPressed() { }
}
