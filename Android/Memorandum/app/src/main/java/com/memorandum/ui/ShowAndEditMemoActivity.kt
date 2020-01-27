package com.memorandum.ui

import  android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import com.google.firebase.firestore.FirebaseFirestore
import com.memorandum.R
import com.memorandum.base.BaseActivity
import com.memorandum.contract.ShowAndEditMemoContract
import com.memorandum.presenter.ShowAndEditMemoPresenter
import kotlinx.android.synthetic.main.activity_show_and_edit_memo.*

class ShowAndEditMemoActivity : BaseActivity(), ShowAndEditMemoContract.View {

    private var fireStore: FirebaseFirestore? = null
    override lateinit var presenter: ShowAndEditMemoContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_up, R.anim.slide_up)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_show_and_edit_memo)

        title = ""

        presenter = ShowAndEditMemoPresenter(this, this)
        fireStore = FirebaseFirestore.getInstance()

        presenter.getDataForShowMemo()
    }

    override fun changeShowMemoEnable() {
        showMemoTitle.isEnabled = true
        showMemoContent.isEnabled = true
    }

    override fun setDataForShowMemo(title: String?, content: String?) {
        showMemoTitle.setText(title)
        showMemoContent.setText(content)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit, menu)
        menuInflater.inflate(R.menu.save, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId) {
            R.id.action_edit -> presenter.editMemo()

            R.id.action_save -> {
                presenter.saveMemo(fireStore, showMemoTitle.text.toString(), showMemoContent.text.toString())

                finish()
                overridePendingTransition(R.anim.slide_down, R.anim.slide_down)
            }
        }

        return true
    }

    override fun hideKeyboard() {}

    override fun startActivity(target: Class<*>) {}

    override fun onBackPressed() { }
}
