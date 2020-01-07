package com.example.foryoudicodingkadesubtwo.helper

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit


class setIDleague {
    companion object {

        private val PREF_NAME = "prefs"

        private fun getPrefs(context: Context): SharedPreferences {
            return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        }


        fun getToken(context: Context): String? {
            return getPrefs(
                context
            ).getString("Token", "")
        }

        fun setToken(context: Context, input: String) {
//            val editor = getPrefs(context).edit()
//            editor.putString("Token", input)
//            editor.commit()

            getPrefs(context).edit {
                putString("Token", input)
            }
        }

        fun clearToken(context: Context) {
            val editor = getPrefs(
                context
            ).edit()
            editor.clear().commit()
        }
    }
}// Blank
