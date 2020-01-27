package com.memorandum.presenter

import android.content.Context
import com.memorandum.contract.WriteMemoContract

class WriteMemoPresenter(override val view: WriteMemoContract.View, override val context: Context) : WriteMemoContract.Presenter {

    override fun changeActivity(target: Class<*>) {}
}