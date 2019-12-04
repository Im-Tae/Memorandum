package com.memorandum.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

class HideKeyboard {
    companion object {
        fun hideKeyboard(view: View?, context: Context) {
            if (view != null) {
                val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

                Thread.sleep(100)
            }
        }
    }
}