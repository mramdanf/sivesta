package com.sivestafunder.android.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

public class LoginActivity extends AppCompatActivity implements
        Funder.FunderModelInf {

    @BindView(R.id.in_username) EditText etUsername;
    @BindView(R.id.in_password) EditText etPassword;

    private final String LOG_TAG = this.getClass().getSimpleName();
    private ProgressDialog mProgressDialog;
    private Funder mFunder;

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

    @OnClick(R.id.btn_submit_login)
    public void submitLogin(View v) {
        mProgressDialog.show();
        new Funder().checkLoginApi(
                etUsername.getText().toString(),
                etPassword.getText().toString(),
                this
        );
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mProgressDialog != null)
            mProgressDialog.dismiss();
    }

    /* Funder Model - CheckLogin Callback */
    @Override
    public void checkLoginApiCallback(Bundle args) {
        mProgressDialog.dismiss();
        mFunder = args.getParcelable(AppConst.OBJ_FUNDER);
        if (args.getString(AppConst.TAG_MSG).equals(AppConst.TAG_SUCCESS)) {
            // Simpan data funder di prf
            Utility.setFarmerPrefs(this, mFunder);
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            i.putExtra(AppConst.OBJ_FUNDER, mFunder);
            startActivity(i);
            finish();
        } else {
            Toast.makeText(this, getString(R.string.login_failed_tex), Toast.LENGTH_SHORT).show();
        }
    }
}
