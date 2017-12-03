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
import android.widget.Toast;

import com.sivesta.androidfarmer.ApiEndPoint.KomoditasEndPoint;
import com.sivesta.androidfarmer.ApiRespWrapper.ModifyResp;
import com.sivesta.androidfarmer.Helpers.AppConst;
import com.sivesta.androidfarmer.Helpers.RetrofitHelper;
import com.sivesta.androidfarmer.Helpers.Utility;
import com.sivesta.androidfarmer.Models.Farmer;
import com.sivesta.androidfarmer.Models.Komoditas;
import com.sivesta.androidfarmer.Models.KomoditasPerenial;
import com.sivesta.androidfarmer.Models.KomoditasTahunan;
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
    @BindView(R.id.wrapper_tahunan)
    LinearLayout wrapperTahunan;
    @BindView(R.id.in_id_komoditas)
    EditText etInIdKomoditas;
    @BindView(R.id.in_lebar)
    EditText etLebar;
    @BindView(R.id.in_panjang)
    EditText etPanjang;

    private Farmer mLoggedInFarmer;
    private final String LOG_TAG = this.getClass().getSimpleName();
    private ProgressDialog mProgressDialog;
    private KomoditasEndPoint mKomoditasService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_komoditas);
        ButterKnife.bind(this);

        setTitle("Add Komoditas");

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.please_wait_tex));
        mProgressDialog.setCancelable(false);

        listenToSpinnerChange();

        Intent i = getIntent();
        mLoggedInFarmer = i.getParcelableExtra(AppConst.OBJ_FARMER);


        if (i.hasExtra(AppConst.OBJ_KOMODITAS)) { // Update komoditas
            Komoditas komoditas = i.getParcelableExtra(AppConst.OBJ_KOMODITAS);
            populateUpdateData(komoditas);
        }

    }

    private void populateUpdateData(Komoditas k) {
        etNamaKomoditas.setText(k.getNama());
        etHargaKom.setText(String.valueOf(k.getHarga()));
        etStokKom.setText(String.valueOf(k.getStok()));
        etLokasiKom.setText(k.getLokasi());
        etInIdKomoditas.setText(k.getIdKomoditas());

        KomoditasPerenial kp = k.getParenial();
        KomoditasTahunan kt = k.getTahunan();
        if (kp != null) {
            showWrapperParenial();
            typeKom.setSelection(1);
            etJmlPohon.setText(String.valueOf(kp.getJmlPohon()));
        } else if (kt != null) {
            showWrapperTahunan();
            typeKom.setSelection(2);
            etPanjang.setText(String.valueOf(kt.getPanjang()));
            etLebar.setText(String.valueOf(kt.getLebar()));
        }

    }

    private void listenToSpinnerChange() {
        typeKom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    wrapperPerenial.setVisibility(View.VISIBLE);
                    wrapperTahunan.setVisibility(View.GONE);
                } else if (position == 2) {
                    wrapperPerenial.setVisibility(View.GONE);
                    wrapperTahunan.setVisibility(View.VISIBLE);
                }

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
                0, // longitude
                k.getTahunan().getPanjang(),
                k.getTahunan().getLebar()
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
                        Toast.makeText(
                                FormKomoditasActivity.this,
                                modifyResp.getMsg(),
                                Toast.LENGTH_SHORT
                        ).show();
                        setResult(RESULT_OK);
                        finish();
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

    private void updateKomoditasApi(Komoditas k) {
        mProgressDialog.show();
        mKomoditasService = new RetrofitHelper()
                .komoditasService(mLoggedInFarmer.getUsername(), mLoggedInFarmer.getPassword());
        Observable<ModifyResp> submitKomoditas = mKomoditasService.updateKomoditasService(
                k.getNama(),
                k.getHarga(),
                k.getStok(),
                k.getLokasi(),
                k.getKomoditasType(),
                k.getParenial().getJmlPohon(),
                k.getIdKomoditas(),
                0, // latitude
                0, // longitude
                k.getTahunan().getPanjang(),
                k.getTahunan().getLebar()
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
                        Toast.makeText(
                                FormKomoditasActivity.this,
                                modifyResp.getMsg(),
                                Toast.LENGTH_SHORT
                        ).show();
                        setResult(RESULT_OK);
                        finish();
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

    private void showWrapperParenial() {
        wrapperPerenial.setVisibility(View.VISIBLE);
        wrapperTahunan.setVisibility(View.GONE);
    }

    private void showWrapperTahunan() {
        wrapperTahunan.setVisibility(View.VISIBLE);
        wrapperPerenial.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_submit_kom)
    public void submitKomoditas(View v) {

        if (typeKom.getSelectedItemPosition() == 0) {
            Utility.displayAlert(this, "Anda belum memilih jenis komoditas (parenial/tahunan).");
            return;
        }

        Komoditas komoditas = new Komoditas();
        komoditas.setNama(etNamaKomoditas.getText().toString());
        komoditas.setHarga(Integer.valueOf(etHargaKom.getText().toString()));
        komoditas.setStok(Integer.valueOf(etStokKom.getText().toString()));
        komoditas.setLokasi(etLokasiKom.getText().toString());
        komoditas.setKomoditasType(typeKom.getSelectedItemPosition());
        komoditas.setIdKomoditas(etInIdKomoditas.getText().toString());

        KomoditasPerenial kp = new KomoditasPerenial();
        kp.setJmlPohon(Integer.valueOf(etJmlPohon.getText().toString()));
        komoditas.setParenial(kp);

        KomoditasTahunan kt = new KomoditasTahunan();
        kt.setLebar(Integer.valueOf(etLebar.getText().toString()));
        kt.setPanjang(Integer.valueOf(etPanjang.getText().toString()));
        komoditas.setTahunan(kt);

        if (komoditas.getIdKomoditas() != null && !komoditas.getIdKomoditas().equals("")) { // Update
            updateKomoditasApi(komoditas);
        } else { // Add
            submitKomoditasApi(komoditas);
        }


    }
}
