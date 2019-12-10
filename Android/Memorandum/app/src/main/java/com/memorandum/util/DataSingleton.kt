package com.memorandum.util

class DataSingleton {
    companion object {
        private var instance: DataSingleton? = null

        fun getInstance(): DataSingleton? {
            if (instance == null) instance = DataSingleton()

            return instance
        }

    }

    var title: String? = null
    var content: String? = null
}