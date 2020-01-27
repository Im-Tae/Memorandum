package com.memorandum.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.memorandum.R
import com.memorandum.contract.MainContract
import com.memorandum.presenter.MainPresenter
import com.memorandum.model.Memo
import com.memorandum.adapter.MemoAdapter
import com.memorandum.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainContract.View {

    override lateinit var presenter : MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this, this)

        presenter.getMemo()

        swipeRefreshLayout.setOnRefreshListener { presenter.refreshMemo(swipeRefreshLayout) }
    }

    override fun layoutRefresh() = swipeRefreshLayout.setRefreshing(false)

    override fun setMemo(memoList: ArrayList<Memo>) {
        recyclerView.adapter?.notifyDataSetChanged()
        val memoAdapter = MemoAdapter(this, memoList)
        recyclerView.adapter = memoAdapter
    }

    override fun startActivity(context: Context, target: Class<*>) = startActivity(Intent(context, target))

    override fun hideKeyboard() {}

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add, menu)
        menuInflater.inflate(R.menu.logout, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId) {
            R.id.action_addMemo -> presenter.changeActivity(WriteMemoActivity::class.java)

            R.id.action_logout -> presenter.logout()
        }

        return true
    }

    override fun finishAffinityActivity() = finishAffinity()

    override fun onBackPressed() = presenter.backPressed()
}
