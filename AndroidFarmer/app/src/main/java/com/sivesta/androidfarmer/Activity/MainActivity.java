package com.sivesta.androidfarmer.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.sivesta.androidfarmer.ApiEndPoint.KomoditasEndPoint;
import com.sivesta.androidfarmer.ApiRespWrapper.ListKomoditasResp;
import com.sivesta.androidfarmer.Helpers.AppConst;
import com.sivesta.androidfarmer.Fragments.KomoditasFragment;
import com.sivesta.androidfarmer.Helpers.RetrofitHelper;
import com.sivesta.androidfarmer.Models.Farmer;
import com.sivesta.androidfarmer.Models.Komoditas;
import com.sivesta.androidfarmer.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements
        KomoditasFragment.KomoditasFragmentInf {


    private Farmer mLogedInFarmer;
    private ProgressDialog mProgressDialog;
    private KomoditasEndPoint mKomoditasService;

    private final String LOG_TAG = this.getClass().getSimpleName();
    private final int REQ_UPDATE_KOMODITAS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        mLogedInFarmer = i.getParcelableExtra(AppConst.OBJ_FARMER);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.please_wait_tex));
        mProgressDialog.setCancelable(false);

        getKomoditasByFarmer();

    }

    private void setUpFragment(Fragment f) {
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.fragment_container, f);
        String tag = fragmentTransaction.toString();
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void getKomoditasByFarmer() {
        mProgressDialog.show();
        mKomoditasService = new RetrofitHelper()
                .komoditasService(mLogedInFarmer.getUsername(), mLogedInFarmer.getPassword());
        final Observable<ListKomoditasResp> listKomoditas = mKomoditasService
                .getKomoditasByFarmerService(mLogedInFarmer.getIdPetani());
        listKomoditas
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListKomoditasResp>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ListKomoditasResp listKomoditasResp) {
                        mProgressDialog.dismiss();
                        Bundle args = new Bundle();
                        args.putParcelable(AppConst.LIST_OBJ_KOMODITAS, listKomoditasResp);

                        KomoditasFragment kf = new KomoditasFragment();
                        kf.setArguments(args);
                        setUpFragment(kf);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                        e.printStackTrace();
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

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0)
            getSupportFragmentManager().popBackStack();
        else
            super.onBackPressed();
    }

    @Override
    public void komoditasFragmentListener(Bundle args) {
        int viewId = args.getInt(AppConst.VIEW_ID);
        Intent formKomoditas = new Intent(MainActivity.this, FormKomoditasActivity.class);
        formKomoditas.putExtra(AppConst.OBJ_FARMER, mLogedInFarmer);

        switch (viewId) {
            case R.id.btn_add_komoditas:
                startActivityForResult(formKomoditas, REQ_UPDATE_KOMODITAS);
                break;
            case AppConst.LIST_CLICK_ID:
                Komoditas komoditas = args.getParcelable(AppConst.OBJ_KOMODITAS);
                formKomoditas.putExtra(AppConst.OBJ_KOMODITAS, komoditas);
                startActivityForResult(formKomoditas, REQ_UPDATE_KOMODITAS);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQ_UPDATE_KOMODITAS) {
            getKomoditasByFarmer();
        }
    }
}

