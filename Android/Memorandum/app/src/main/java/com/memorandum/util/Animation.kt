package com.memorandum.util

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.memorandum.R

class Animation {
    companion object {
        fun shakeAnimation(view: View, type: String) {

            when (type) {
                "Tada" -> YoYo.with(Techniques.Tada).duration(700).playOn(view)
                "Shake" -> YoYo.with(Techniques.Shake).duration(700).playOn(view)
            }

            view.setBackgroundResource(R.drawable.error_textbox)
        }

        fun appearAnimation(context: Context, view1: View? = null, view2: View? = null, view3: View? = null, view4: View? = null) {
            val animation = AnimationUtils.loadAnimation(context, R.anim.appear)

            view1?.startAnimation(animation)
            view2?.startAnimation(animation)
            view3?.startAnimation(animation)
            view4?.startAnimation(animation)
        }

    }
}