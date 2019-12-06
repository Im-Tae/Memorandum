package com.memorandum.util

import android.content.Context
import android.preference.PreferenceManager

class SharedPreferenceManager {

    companion object {

        fun setUserId(context: Context, userId: String) {
            val pref = PreferenceManager.getDefaultSharedPreferences(context)
            val editor = pref.edit()
            editor.putString("userId", userId).apply()
        }

        fun getUserId(context: Context): String? {
            val pref = PreferenceManager.getDefaultSharedPreferences(context)

            return pref.getString("userId", "")
        }
    }
}