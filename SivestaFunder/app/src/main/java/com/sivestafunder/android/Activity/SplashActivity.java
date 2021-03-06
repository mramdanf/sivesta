package com.sivestafunder.android.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TabHost;


import com.sivestafunder.android.Helpers.AppConst;
import com.sivestafunder.android.Helpers.Utility;
import com.sivestafunder.android.Models.Funder;
import com.sivestafunder.android.R;

import java.util.Timer;
import java.util.TimerTask;


public class SplashActivity extends AppCompatActivity {

    private final String LOG_TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        changeStatusBarColor();

        int SPLASH_DISPLAY_LENGTH = 2000;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Funder savedFunder = Utility.getFunderPrefs(SplashActivity.this);
                Log.d(LOG_TAG, "RUN");
                if (savedFunder.getEmail() != null && !savedFunder.getEmail().equals("")) {
                    Funder f = new Funder(SplashActivity.this);
                    f.checkLoginApi(
                            savedFunder.getEmail(),
                            savedFunder.getPassword(),
                            new Funder.FunderModelInf() {
                                @Override
                                public void funderModelApiCallback(Bundle args) {
                                    Funder funderFromApi = args.getParcelable(AppConst.OBJ_FUNDER);
                                    if (args.getString(AppConst.TAG_MSG).equals(AppConst.TAG_SUCCESS)) {
                                        Intent i = new Intent(SplashActivity.this, MainActivity.class);
                                        i.putExtra(AppConst.OBJ_FUNDER, funderFromApi);
                                        startActivity(i);
                                        finish();
                                    }else {
                                        Log.d(LOG_TAG, "login failed");
                                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                                        finish();
                                    }
                                }
                            }
                    );

                } else {
                    Log.d(LOG_TAG, "no saved");
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }


            }
        }, SPLASH_DISPLAY_LENGTH);


    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
