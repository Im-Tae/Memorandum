package com.memorandum.base

interface BaseView<T> {

    val presenter: T

    fun showToast(message: String, type: String)
    fun startActivity(target: Class<*>)
    fun hideKeyboard()
}