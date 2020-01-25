package com.memorandum.base

interface BasePresenter<T> {

    val view: T

    fun changeActivity(target: Class<*>)
}