package com.memorandum.util

import android.content.Context
import android.net.ConnectivityManager

@Suppress("DEPRECATION")
class GetNetworkInfo {
    companion object {
        fun networkInfo(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo?: null

            return networkInfo != null && networkInfo.isConnected
        }
    }
}