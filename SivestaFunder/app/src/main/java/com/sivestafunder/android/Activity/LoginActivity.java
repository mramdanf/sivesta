package com.sivestafunder.android.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.sivestafunder.android.ApiEndPoint.FunderEndPoint;
import com.sivestafunder.android.Helpers.AppConst;
import com.sivestafunder.android.Helpers.RetrofitHelper;
import com.sivestafunder.android.Helpers.Utility;
import com.sivestafunder.android.Models.Funder;
import com.sivestafunder.android.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.in_username) EditText etUsername;
    @BindView(R.id.in_password) EditText etPassword;

    private final String LOG_TAG = this.getClass().getSimpleName();
    private FunderEndPoint mFunderService;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Login");

        ButterKnife.bind(this);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.please_wait_tex));
        mProgressDialog.setCancelable(false);
    }

    private void checkLoginApi(Funder funder) {
        Log.d(LOG_TAG, "btn clik handle, uname: " + funder.getUsername());
        Log.d(LOG_TAG, "btn clik handle, pass: " + funder.getPassword());

        mProgressDialog.show();
        mFunderService = new RetrofitHelper()
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
                        mProgressDialog.dismiss();
                        Utility.setFarmerPrefs(LoginActivity.this, farmer);

                        Intent mainActIntent = new Intent(LoginActivity.this, MainActivity.class);
                        mainActIntent.putExtra(AppConst.OBJ_FUNDER, farmer);
                        startActivity(mainActIntent);
                        finish();
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        e.printStackTrace();
                        mProgressDialog.dismiss();
                        if (e.getMessage().equals("HTTP 401 Unauthorized"))
                            Utility.displayAlert(LoginActivity.this, getString(R.string.login_failed_tex));



                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    @OnClick(R.id.btn_submit_login)
    public void submitLogin(View v) {
        Funder f = new Funder();
        f.setUsername(etUsername.getText().toString());
        f.setPassword(etPassword.getText().toString());

        Log.d(LOG_TAG, "btn clik handle, uname: " + f.getUsername());
        Log.d(LOG_TAG, "btn clik handle, pass: " + f.getPassword());
        checkLoginApi(f);
    }
}
