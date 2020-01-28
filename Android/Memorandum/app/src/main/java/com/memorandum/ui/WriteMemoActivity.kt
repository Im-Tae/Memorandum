package com.memorandum.ui

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.firestore.FirebaseFirestore
import com.memorandum.R
import com.memorandum.base.BaseActivity
import com.memorandum.contract.WriteMemoContract
import com.memorandum.presenter.WriteMemoPresenter
import kotlinx.android.synthetic.main.activity_write_memo.*

class WriteMemoActivity : BaseActivity(), WriteMemoContract.View {

    override lateinit var presenter: WriteMemoContract.Presenter
    private var fireStore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_up, R.anim.slide_up)
        setContentView(R.layout.activity_write_memo)

        title = ""

        presenter = WriteMemoPresenter(this, this)
        fireStore = FirebaseFirestore.getInstance()

    }

    override fun hideKeyboard() {}

    override fun startActivity(context: Context, target: Class<*>) {}

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.save, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId) {
            R.id.action_save -> {
                presenter.saveMemo(fireStore, writeMemoTitle.text.toString(), writeMemoContent.text.toString())

                finish()
                overridePendingTransition(R.anim.slide_down, R.anim.slide_down)
            }
        }

        return true
    }

    override fun onBackPressed() { }

}
