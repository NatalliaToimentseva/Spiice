package com.example.spiice.repositoty

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

private const val APP_PREFERENCES = "app_pref"
private const val USER_PREFERENCES = "user_pref"
private const val IS_FIRST_LAUNCH = "isFirstLaunch"
private const val EMAIL = "email"

object SharedPreferencesRepository {

    private var preferences: SharedPreferences? = null
    private var userPreferences: SharedPreferences? = null

    fun init(context: Context) {
        preferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        userPreferences = context.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE)
    }

    fun isFirstLaunch(): Boolean {
        return preferences?.getBoolean(IS_FIRST_LAUNCH, true) ?: true
    }

    fun setFirstLaunch() {
        preferences?.edit { putBoolean(IS_FIRST_LAUNCH, false) }
    }

    fun setEmail(email: String) {
        userPreferences?.edit { putString(EMAIL, email) }
    }

    fun getEmail(): String? {
        return userPreferences?.getString(EMAIL, null)
    }

    fun clearUserData() {
        userPreferences?.edit {
            clear()
        }
    }
}