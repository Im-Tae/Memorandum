package com.memorandum.utils

import android.content.Context
import android.widget.Toast
import es.dmoral.toasty.Toasty

class ToastMessage {
    companion object {
        fun toastMessage(context: Context, message: String, type: String) {

            when (type) {
                "error" -> Toasty.error(context, message, Toast.LENGTH_SHORT, true).show()
                "success" -> Toasty.success(context, message, Toast.LENGTH_SHORT, true).show()
                "info" -> Toasty.info(context, message, Toast.LENGTH_SHORT, true).show()
            }

        }
    }
}