package com.sivesta.androidfarmer.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.sivesta.androidfarmer.ApiEndPoint.KomoditasEndPoint;
import com.sivesta.androidfarmer.ApiRespWrapper.ModifyResp;
import com.sivesta.androidfarmer.Helpers.AppConst;
import com.sivesta.androidfarmer.Helpers.RetrofitHelper;
import com.sivesta.androidfarmer.Models.Farmer;
import com.sivesta.androidfarmer.Models.Komoditas;
import com.sivesta.androidfarmer.Models.KomoditasPerenial;
import com.sivesta.androidfarmer.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FormKomoditasActivity extends AppCompatActivity {

    @BindView(R.id.in_nama_komoditas)
    EditText etNamaKomoditas;
    @BindView(R.id.in_harga_kom)
    EditText etHargaKom;
    @BindView(R.id.in_stok_kom)
    EditText etStokKom;
    @BindView(R.id.in_lokasi_kom)
    EditText etLokasiKom;
    @BindView(R.id.in_jml_pohon)
    EditText etJmlPohon;
    @BindView(R.id.spin_type_kom)
    Spinner typeKom;
    @BindView(R.id.wrapper_perenial)
    LinearLayout wrapperPerenial;

    private Farmer mLoggedInFarmer;
    private final String LOG_TAG = this.getClass().getSimpleName();
    private ProgressDialog mProgressDialog;
    private KomoditasEndPoint mKomoditasService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_komoditas);
        ButterKnife.bind(this);

        Intent i = getIntent();
        mLoggedInFarmer = i.getParcelableExtra(AppConst.OBJ_FARMER);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.please_wait_tex));
        mProgressDialog.setCancelable(false);

        listenToSpinnerChange();

    }

    private void listenToSpinnerChange() {
        typeKom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0)
                    wrapperPerenial.setVisibility(View.VISIBLE);
                else
                    wrapperPerenial.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void submitKomoditasApi(Komoditas k) {
        mProgressDialog.show();
        mKomoditasService = new RetrofitHelper()
                .komoditasService(mLoggedInFarmer.getUsername(), mLoggedInFarmer.getPassword());
        Observable<ModifyResp> submitKomoditas = mKomoditasService.addKomoditasService(
                k.getNama(),
                k.getHarga(),
                k.getStok(),
                k.getLokasi(),
                k.getKomoditasType(),
                k.getParenial().getJmlPohon(),
                mLoggedInFarmer.getIdPetani(),
                0, // latitude
                0 // longitude
        );

        submitKomoditas
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ModifyResp>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ModifyResp modifyResp) {
                        mProgressDialog.dismiss();
                        Log.d(LOG_TAG, "msg: " + modifyResp.getMsg());
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

    @OnClick(R.id.btn_submit_kom)
    public void submitKomoditas(View v) {
        Komoditas komoditas = new Komoditas();
        komoditas.setNama(etNamaKomoditas.getText().toString());
        komoditas.setHarga(Integer.valueOf(etHargaKom.getText().toString()));
        komoditas.setStok(Integer.valueOf(etStokKom.getText().toString()));
        komoditas.setLokasi(etLokasiKom.getText().toString());
        komoditas.setKomoditasType(typeKom.getSelectedItemPosition());

        KomoditasPerenial kp = new KomoditasPerenial();
        kp.setJmlPohon(Integer.valueOf(etJmlPohon.getText().toString()));
        komoditas.setParenial(kp);

        Bundle args = new Bundle();
        args.putParcelable(AppConst.OBJ_KOMODITAS, komoditas);
        args.putInt(AppConst.VIEW_ID, v.getId());

        submitKomoditasApi(komoditas);
    }
}
