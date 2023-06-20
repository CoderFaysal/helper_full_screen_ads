package com.coderfaysal.myshariatpur;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;

import papaya.in.admobopenads.AppOpenManager;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MobileAds.initialize(this);
        new AppOpenManager(this, "ca-app-pub-5842733182141446/2417956769");
    }
}
