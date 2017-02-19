package com.example.bowenzheng.classassistant;

import android.app.Application;

import mobi.inthepocket.android.beacons.ibeaconscanner.IBeaconScanner;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
/**
 * Created by bowenzheng on 2/18/17.
 */

public class AppApplication extends Application {

    @Override
    public void onCreate()
    {
        super.onCreate();

        // initialize LeakCanary
        if (LeakCanary.isInAnalyzerProcess(this))
        {
            return;
        }
        LeakCanary.install(this);

        // initialize Facebook Stetho
        Stetho.initializeWithDefaults(this);

        // initialize In The Pockets iBeaconScanner
        IBeaconScanner.initialize(IBeaconScanner.newInitializer(this).build());
    }
}
