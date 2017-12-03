package com.sivesta.androidfarmer.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.sivesta.androidfarmer.ApiEndPoint.FarmerEndPoint;
import com.sivesta.androidfarmer.Helpers.AppConst;
import com.sivesta.androidfarmer.Helpers.RetrofitHelper;
import com.sivesta.androidfarmer.Helpers.Utility;
import com.sivesta.androidfarmer.Models.Farmer;
import com.sivesta.androidfarmer.R;

import org.reactivestreams.Subscription;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.in_username) EditText etUsername;
    @BindView(R.id.in_password) EditText etPassword;

    private final String LOG_TAG = this.getClass().getSimpleName();
    private FarmerEndPoint mFarmerService;
    private ProgressDialog mProgressDialog;

    @NonNull
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.please_wait_tex));
        mProgressDialog.setCancelable(false);

    }

    private void checkLoginApi(Farmer farmer) {
        mProgressDialog.show();
        mFarmerService = new RetrofitHelper()
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
                        mProgressDialog.dismiss();
                        Utility.setFarmerPrefs(LoginActivity.this, farmer);

                        Intent mainActIntent = new Intent(LoginActivity.this, MainActivity.class);
                        mainActIntent.putExtra(AppConst.OBJ_FARMER, farmer);
                        startActivity(mainActIntent);
                        finish();
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        mProgressDialog.dismiss();
                        if (e.getMessage().equals("HTTP 401 Unauthorized"))
                            Utility.displayAlert(LoginActivity.this, getString(R.string.login_failed_tex));



                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mProgressDialog != null)
            mProgressDialog.dismiss();
    }

    @OnClick(R.id.btn_submit_login)
    public void submitLogin(View v) {
        Farmer f = new Farmer();
        f.setUsername(etUsername.getText().toString());
        f.setPassword(etPassword.getText().toString());
        checkLoginApi(f);
    }
}

