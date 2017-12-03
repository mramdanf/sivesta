package com.sivesta.androidfarmer.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.sivesta.androidfarmer.ApiEndPoint.FarmerEndPoint;
import com.sivesta.androidfarmer.Helpers.AppConst;
import com.sivesta.androidfarmer.Helpers.RetrofitHelper;
import com.sivesta.androidfarmer.Helpers.Utility;
import com.sivesta.androidfarmer.Models.Farmer;
import com.sivesta.androidfarmer.R;

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
                Farmer farmer = Utility.getFarmerPrefs(SplashActivity.this);
                if (farmer.getUsername() != null && !farmer.getUsername().equals("")) {
                    checkLoginApi(farmer);
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }

            }
        }, SPLASH_DISPLAY_LENGTH);


    }

    private void checkLoginApi(Farmer farmer) {
        FarmerEndPoint mFarmerService = new RetrofitHelper()
                .getFarmerService(farmer.getUsername(), farmer.getPassword());
        Observable<Farmer> loginFarmer = mFarmerService.checkLogin();
        loginFarmer
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Farmer>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull Farmer farmer) {
                        Intent mainActIntent = new Intent(SplashActivity.this, MainActivity.class);
                        mainActIntent.putExtra(AppConst.OBJ_FARMER, farmer);
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

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
