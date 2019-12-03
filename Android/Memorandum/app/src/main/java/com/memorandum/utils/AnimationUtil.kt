package com.memorandum.utils

import android.view.View
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo

class AnimationUtil {
    companion object {
        fun animation(view: View, type: String) {

            when (type) {
                "Tada" -> YoYo.with(Techniques.Tada).duration(700).playOn(view)
                "Shake" -> YoYo.with(Techniques.Shake).duration(700).playOn(view)
            }
        }
    }
}