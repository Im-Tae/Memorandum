package com.memorandum.presenter

import android.content.Context
import com.memorandum.contract.SplashContract
import com.memorandum.ui.LoginSelectActivity
import com.memorandum.ui.MainActivity
import com.memorandum.util.SharedPreferenceManager

class SplashPresenter(override val view: SplashContract.View, override val context: Context) : SplashContract.Presenter {

    override fun changeActivity(target: Class<*>) = view.startActivity(context, target)

    override fun splash() {

        val splashScreen = object : Thread() {
            override fun run() {

            view.showAnimation()

                sleep(3000)

                if (SharedPreferenceManager.getUserId(context) != "")
                    view.startActivity(context, MainActivity::class.java)
                else
                    view.startActivity(context, LoginSelectActivity::class.java)

                view.finishActivity()
            }
        }
        splashScreen.start()
    }
}