package com.memorandum.util

import android.content.Context
import android.view.View
import com.memorandum.R
import java.util.regex.Pattern

class CheckValid {
    companion object {

        private val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(  "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+" )


        fun checkValid(context: Context, email: String = "", password: String="", emailView: View, passwordView: View): Boolean {

            emailView.setBackgroundResource(R.drawable.textbox)
            passwordView.setBackgroundResource(R.drawable.textbox)

            if (email == "" && password != "") {
                Animation.shakeAnimation(emailView, "Shake")
                ToastMessage.toastMessage(context, "이메일을 입력해주세요.", "error")
            } else if (email != "" && password == "") {
                Animation.shakeAnimation(passwordView, "Shake")
                ToastMessage.toastMessage(context, "비밀번호를 입력해주세요.", "error")
            } else if (email == "" && password == "") {
                Animation.shakeAnimation(emailView, "Shake")
                Animation.shakeAnimation(passwordView, "Shake")
                ToastMessage.toastMessage(context, "이메일과 비밀번호를 입력해주세요.", "error")
            } else {
                if (checkEmail(email)) { return true } else  {
                    Animation.shakeAnimation(emailView, "Shake")
                    ToastMessage.toastMessage(context, "올바른 이메일을 입력해주세요.", "error")
                }
            }
            return false
        }

        fun checkValid(context: Context, email: String = "", emailView: View): Boolean {

            emailView.setBackgroundResource(R.drawable.textbox)

            if (email != "") {
                if (checkEmail(email)) { return true } else  {
                    Animation.shakeAnimation(emailView, "Shake")
                    ToastMessage.toastMessage(context, "올바른 이메일을 입력해주세요.", "error")
                }
            } else {
                Animation.shakeAnimation(emailView, "Shake")
                ToastMessage.toastMessage(context, "이메일을 입력해주세요.", "error")
            }
            return false
        }

        fun checkEmail(email: String): Boolean { return EMAIL_ADDRESS_PATTERN.matcher(email).matches() }
    }
}