package com.sivestafunder.android.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sivestafunder.android.Fragmets.ArticleFragment;
import com.sivestafunder.android.Fragmets.CatalogFragment;
import com.sivestafunder.android.Fragmets.CreateAccountFragment;
import com.sivestafunder.android.Fragmets.HomeFragment;
import com.sivestafunder.android.Fragmets.LoginFragment;
import com.sivestafunder.android.Fragmets.MySeedsFragment;
import com.sivestafunder.android.Fragmets.ProfileFragment;
import com.sivestafunder.android.Helpers.AppConst;
import com.sivestafunder.android.Models.Funder;
import com.sivestafunder.android.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity implements
        Funder.FunderModelInf,
        LoginFragment.LoginFragmentInf,
        CreateAccountFragment.CreateAccountFragmentInf {

    private final String LOG_TAG = this.getClass().getSimpleName();
    private Funder mFunder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        setUpFragment(new LoginFragment());
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void loginFragmentClickListerner(Bundle args) {
        int viewId = args.getInt(AppConst.VIEW_ID);
        switch (viewId) {
            case R.id.btn_create_account:
                setUpFragment(new CreateAccountFragment());
                break;
            case R.id.btn_submit_login:
                mFunder = args.getParcelable(AppConst.OBJ_FUNDER);
                new Funder(this).checkLoginApi(
                        args.getString(AppConst.PRF_TAG_UNAME),
                        args.getString(AppConst.PRF_TAG_PASS),
                        this
                );
                break;
        }
    }

    @Override
    public void createAccountClickListener(Bundle args) {
        int viewId = args.getInt(AppConst.VIEW_ID);
        switch (viewId) {
            case R.id.btn_back_login:
                setUpFragment(new LoginFragment());
                break;
            case R.id.btn_submit_create_acc:
                break;
        }
    }

    /* Funder Model - CheckLogin Callback */
    @Override
    public void checkLoginApiCallback(Bundle args) {

        mFunder = args.getParcelable(AppConst.OBJ_FUNDER);
        if (args.getString(AppConst.TAG_MSG).equals(AppConst.TAG_SUCCESS)) {

            LoginFragment lf = (LoginFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.login_container);
            lf.displayLoginResult(mFunder);

        } else {
            Toast.makeText(this, getString(R.string.login_failed_tex), Toast.LENGTH_SHORT).show();
        }
    }

    private void setUpFragment(Fragment f) {
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.login_container, f);
        fragmentTransaction.commitAllowingStateLoss();
    }

}
