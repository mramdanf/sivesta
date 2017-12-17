package com.sivestafunder.android.Helpers;

import android.app.Application;


import com.sivestafunder.android.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Ramdan Firdaus on 13/2/2017.
 */

public class CustomApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(getString(R.string.alegreya_sans_regular))
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
