package com.memorandum.base

import android.content.Context

interface BasePresenter<T> {

    val view: T
    val context: Context

    fun changeActivity(target: Class<*>)
}