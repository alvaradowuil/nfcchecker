package com.wdev.nfcchecker.core

import android.content.Context
import android.content.SharedPreferences

class MyPreferences {
    companion object {

        const val PREF_IS_CHECKED = "is_checked"

        private var sharedPreferences: SharedPreferences? = null

        private fun init(context: Context) {
            if (sharedPreferences == null) {
                sharedPreferences = context.getSharedPreferences(context.packageName,
                    Context.MODE_PRIVATE)
            }
        }

        fun setChecked(context: Context) {
            init(context)
            sharedPreferences?.edit()?.putBoolean(PREF_IS_CHECKED, true)?.apply()
        }

        fun isChecked(context: Context): Boolean {
            init(context)
            return sharedPreferences?.getBoolean(PREF_IS_CHECKED, false)!!
        }

    }
}