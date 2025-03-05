package com.example.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.telephony.SmsManager
import android.util.Log

class CallReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == TelephonyManager.ACTION_PHONE_STATE_CHANGED) {
            val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
            val incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)

            if (state == TelephonyManager.EXTRA_STATE_RINGING && incomingNumber != null) {
                val savedNumber = SharedPreferencesHelper.getPhoneNumber(context!!)
                if (incomingNumber == savedNumber) {
                    sendAutoReplySMS(context, incomingNumber)
                }
            }
        }
    }

    private fun sendAutoReplySMS(context: Context, phoneNumber: String) {
        val autoReplyMessage = SharedPreferencesHelper.getMessage(context)

        try {
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNumber, null, autoReplyMessage, null, null)
            Log.d("CallReceiver", "SMS enviado a: $phoneNumber")
        } catch (e: Exception) {
            Log.e("CallReceiver", "Error enviando SMS", e)
        }
    }
}
