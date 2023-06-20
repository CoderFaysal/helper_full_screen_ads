package com.coderfaysal.myshariatpur.ui;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class Helper {

    private Activity activity;
    private InterstitialAd mInterstitialAd;
    private int countClicks = 0;


    public Helper(Activity activity){
        this.activity = activity;
        prepareInterstitialAd();
    }



    private void prepareInterstitialAd(){
        MobileAds.initialize(activity, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
                AdRequest adRequest = new AdRequest.Builder().build();

                InterstitialAd.load(activity,"ca-app-pub-5842733182141446/4884068503", adRequest, new InterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                super.onAdLoaded(interstitialAd);
                                mInterstitialAd = interstitialAd;
                                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {

                                    @Override
                                    public void onAdShowedFullScreenContent() {
                                        super.onAdShowedFullScreenContent();
                                        mInterstitialAd = null;
                                    }

                                    @Override
                                    public void onAdClicked() {
                                        super.onAdClicked();
                                    }

                                    @Override
                                    public void onAdDismissedFullScreenContent() {
                                        super.onAdDismissedFullScreenContent();
                                        prepareInterstitialAd();
                                    }

                                    @Override
                                    public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                        super.onAdFailedToShowFullScreenContent(adError);
                                    }

                                    @Override
                                    public void onAdImpression() {
                                        super.onAdImpression();
                                    }


                                });

                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                super.onAdFailedToLoad(loadAdError);
                                Log.v("errorAd", loadAdError.getMessage());
                                mInterstitialAd = null;
                            }
                        });

            }
        });

    }

    public void showInterstitialAd(){
        prepareInterstitialAd();
        if (mInterstitialAd != null){
            mInterstitialAd.show(activity);
        } else{
            Log.v("AD_IS_NULL", "Nothing to Show Ads");
        }

    }

    public void showCounterInterstitialAd(int check){
        countClicks++;
        prepareInterstitialAd();
        if (countClicks == check){
            showInterstitialAd();
            countClicks = 0;
        }
    }

}
