package com.sivestafunder.android.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.sivestafunder.android.ApiEndPoint.FunderEndPoint;
import com.sivestafunder.android.Helpers.AppConst;
import com.sivestafunder.android.Helpers.RetrofitHelper;
import com.sivestafunder.android.Helpers.Utility;
import com.sivestafunder.android.Models.Funder;
import com.sivestafunder.android.R;

import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class SplashActivity extends AppCompatActivity {

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

        int SPLASH_DISPLAY_LENGTH = 3000;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Funder farmer = Utility.getFunderPrefs(SplashActivity.this);
                if (farmer.getUsername() != null && !farmer.getUsername().equals("")) {
                    checkLoginApi(farmer);
                } else {
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

    private void checkLoginApi(Funder funder) {
        FunderEndPoint mFunderService = new RetrofitHelper()
                .getFunderService(funder.getUsername(), funder.getPassword());
        Observable<Funder> loginFarmer = mFunderService.checkLogin();
        loginFarmer
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Funder>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull Funder farmer) {
                        Intent mainActIntent = new Intent(SplashActivity.this, MainActivity.class);
                        mainActIntent.putExtra(AppConst.OBJ_FUNDER, farmer);
                        startActivity(mainActIntent);
                        finish();
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        e.printStackTrace();
                        if (e.getMessage().equals("HTTP 401 Unauthorized"))
                            Utility.displayAlert(SplashActivity.this, getString(R.string.login_failed_tex));

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
}
