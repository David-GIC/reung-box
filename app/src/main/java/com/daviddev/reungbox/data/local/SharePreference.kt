package com.daviddev.reungbox.data.local

import android.content.Context
import androidx.core.content.edit

object CustomSharePreference {
    val preferenceContext = "rb_prefs"
    val OnboardKey = "is_onboard"

    suspend fun saveOnboardState(context: Context, isOnboard: Boolean) {
        val sharedPreferences = context.getSharedPreferences(preferenceContext, Context.MODE_PRIVATE)
        sharedPreferences.edit { putBoolean(OnboardKey, isOnboard) }
    }

    suspend fun getOnboardState(context: Context) : Boolean {
        val sharedPreferences = context.getSharedPreferences(preferenceContext, Context.MODE_PRIVATE)
        val value = sharedPreferences.getBoolean(OnboardKey, false)
        return value
    }

}
