package com.santimattius.android.startup.service

import android.util.Log

class CrashTrackerService {

    var isInitialized: Boolean = false
        private set

    fun initialize() {
        isInitialized = true
    }

    fun log(tag: String?, data: String) {
        Log.i(tag, data)
    }
}