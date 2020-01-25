package com.memorandum.base

import android.content.Context
import com.memorandum.util.ToastMessage

interface BaseView<T> {

    val presenter: T

    fun showToast(context: Context, message: String, type: String) = ToastMessage.toastMessage(context, message, type)
    fun startActivity(target: Class<*>)
    fun hideKeyboard()
}