package com.sivestafunder.android.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.sivestafunder.android.Helpers.AppConst;
import com.sivestafunder.android.Helpers.Utility;
import com.sivestafunder.android.Models.Komoditas;
import com.sivestafunder.android.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SimulationActivity extends AppCompatActivity {

    @BindView(R.id.item_units)
    TextView tvJmlItem;
    @BindView(R.id.sim_nama_kom)
    TextView simNamaKom;
    @BindView(R.id.sim_harga_kom)
    TextView simHargaKom;
    @BindView(R.id.sim_profit_kom)
    TextView simProfitKom;
    @BindView(R.id.sim_min_kontrak)
    TextView simMinKontrak;
    @BindView(R.id.sim_desc_popular_kom)
    TextView simDescPopularKom;
    @BindView(R.id.sim_total_biaya)
    TextView simTotalBiaya;

    private int countJmlItem;
    private Komoditas mKomoditas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation);
        ButterKnife.bind(this);

        setTitle("Simulation");

        Intent i = getIntent();
        mKomoditas = i.getParcelableExtra(AppConst.OBJ_KOMODITAS);
        populateKomoditasDetail();

        countJmlItem = Integer.valueOf(tvJmlItem.getText().toString());
        calculateJmlTotal();

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @OnClick(R.id.plus_btn)
    public void plusBtnClick() {
        tvJmlItem.setText(Integer.toString(++countJmlItem));
        calculateJmlTotal();
    }

    @OnClick(R.id.minus_btn)
    public void minusBtnClick() {
        tvJmlItem.setText(Integer.toString(--countJmlItem));
        calculateJmlTotal();
    }

    private void populateKomoditasDetail() {
        simNamaKom.setText(mKomoditas.getNama());
        simHargaKom.setText(mKomoditas.getHargaText() + "/Units");
        simProfitKom.setText(String.valueOf(mKomoditas.getProfit()) + " %");
        simMinKontrak.setText(String.valueOf(mKomoditas.getMinKontrak()) + " year(s)");
    }

    private void calculateJmlTotal() {
        int value = Integer.valueOf(tvJmlItem.getText().toString());
        int subTotal = value * mKomoditas.getHarga();
        String subTotalText = Utility.formRupiah(subTotal);
        simTotalBiaya.setText(subTotalText);
    }

}
