package com.wdev.nfcchecker

import android.app.Application
import com.google.android.gms.ads.MobileAds

class NfcCheckerApp: Application() {
    override fun onCreate() {
        super.onCreate()

        MobileAds.initialize(this)
    }
}