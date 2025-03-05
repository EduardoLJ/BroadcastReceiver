package com.example.broadcastreceiver

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesHelper {
    private const val PREFS_NAME = "AutoReplyPrefs"
    private const val KEY_PHONE_NUMBER = "phoneNumber"
    private const val KEY_MESSAGE = "message"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun savePhoneNumber(context: Context, phoneNumber: String) {
        getPreferences(context).edit().putString(KEY_PHONE_NUMBER, phoneNumber).apply()
    }

    fun saveMessage(context: Context, message: String) {
        getPreferences(context).edit().putString(KEY_MESSAGE, message).apply()
    }

    fun getPhoneNumber(context: Context): String {
        return getPreferences(context).getString(KEY_PHONE_NUMBER, "") ?: ""
    }

    fun getMessage(context: Context): String {
        return getPreferences(context).getString(KEY_MESSAGE, "Lo siento, no puedo contestar en este momento.") ?: ""
    }
}
