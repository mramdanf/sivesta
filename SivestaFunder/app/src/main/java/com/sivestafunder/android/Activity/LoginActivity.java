package com.sivestafunder.android.Activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.sivestafunder.android.Fragmets.CreateAccountFragment;
import com.sivestafunder.android.Fragmets.LoginFragment;
import com.sivestafunder.android.Helpers.AppConst;
import com.sivestafunder.android.Models.Funder;
import com.sivestafunder.android.R;

import butterknife.ButterKnife;
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
                        args.getString(AppConst.PRF_TAG_EMAIL),
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
                mFunder = args.getParcelable(AppConst.OBJ_FUNDER);
                new Funder(this)
                        .createAccountApi(mFunder, new Funder.FunderModelInf() {
                            @Override
                            public void funderModelApiCallback(Bundle args) {
                                String msg = args.getString(AppConst.TAG_MSG);
                                if (msg.equals(AppConst.TAG_SUCCESS)) {
                                    CreateAccountFragment ca = (CreateAccountFragment) getSupportFragmentManager()
                                            .findFragmentById(R.id.login_container);
                                    ca.showCreateAccountResult(mFunder);
                                }
                            }
                        });
                break;
            case AppConst.SHOW_LOGIN:
                setUpFragment(new LoginFragment());
                break;
        }
    }

    /* Funder Model - CheckLogin Callback */
    @Override
    public void funderModelApiCallback(Bundle args) {
        mFunder = args.getParcelable(AppConst.OBJ_FUNDER);

        LoginFragment lf = (LoginFragment) getSupportFragmentManager()
                .findFragmentById(R.id.login_container);
        lf.displayLoginResult(mFunder);
    }

    private void setUpFragment(Fragment f) {
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.login_container, f);
        fragmentTransaction.commitAllowingStateLoss();
    }

}
