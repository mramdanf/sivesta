package com.sivestafunder.android.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sivestafunder.android.Helpers.AppConst;
import com.sivestafunder.android.Helpers.Utility;
import com.sivestafunder.android.Models.Funder;
import com.sivestafunder.android.Models.Komoditas;
import com.sivestafunder.android.Models.Kontrak;
import com.sivestafunder.android.R;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class KomoditasDetailActivity extends AppCompatActivity {

    @BindView(R.id.main_backdrop)
    ImageView mainBackdrop;
    @BindView(R.id.main_appbar)
    AppBarLayout mainAppBar;
    @BindView(R.id.main_collapsing)
    CollapsingToolbarLayout mainCollapsing;
    @BindView(R.id.kd_price)
    TextView tvKdPrice;
    @BindView(R.id.kd_stock)
    TextView tvKdStock;
    @BindView(R.id.kd_location)
    TextView tvKdLocation;
    @BindView(R.id.kd_profit)
    TextView tvKdProfit;
    @BindView(R.id.kd_harvest)
    TextView tvKdHarvest;
    @BindView(R.id.kd_contract)
    TextView tvKdContract;
    @BindView(R.id.kd_planted)
    TextView tvKdPlanted;
    @BindView(R.id.kd_kom_detail)
    TextView tvKdDeskripsi;


    private Komoditas mKomoditas;
    private String mCollapsingTitle;
    private Funder mFunder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komoditas_detail);
        ButterKnife.bind(this);

        Intent i = getIntent();
        mKomoditas = i.getParcelableExtra(AppConst.OBJ_KOMODITAS);

        mFunder = Utility.getFunderPrefs(this);
        populateKomoditasData();

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @OnClick(R.id.btn_invest_now) public void btnInvestNowClickHandle(View v) {
        /*Kontrak kontrak = new Kontrak();
        kontrak.setIdKomoditas(mKomoditas.getIdKomoditas());
        kontrak.setIdFunder(mFunder.getIdFunder());
        kontrak.setStatusKontrak(1); // blm bayar
        kontrak.setBiayaTotal(1000000);
        new Kontrak().createKontrakApi(
                kontrak,
                mFunder.getEmail(),
                mFunder.getPassword(),
                new Kontrak.KontrakModelInf() {
                    @Override
                    public void kontrakModelInfCallback(Bundle args) {
                        String msg = args.getString(AppConst.TAG_MSG);
                        Log.d(KomoditasDetailActivity.class.getSimpleName(), "tag msg: " + msg);
                    }
                }
        );*/
        Intent i = new Intent(KomoditasDetailActivity.this, InvestNowActivity.class);
        i.putExtra(AppConst.OBJ_KOMODITAS, mKomoditas);
        startActivity(i);
        finish();

    }

    @OnClick(R.id.btn_go_simulation) public void btnGoSimulationClickHandle(View v) {
        Intent i = new Intent(KomoditasDetailActivity.this, SimulationActivity.class);
        i.putExtra(AppConst.OBJ_KOMODITAS, mKomoditas);
        startActivity(i);
    }

    private void populateKomoditasData() {
        mainCollapsing.setExpandedTitleTextAppearance(R.style.CollapsedAppBar);
        mainCollapsing.setTitle(mKomoditas.getNama());
        tvKdPrice.setText(mKomoditas.getHargaText());
        tvKdStock.setText(String.valueOf(mKomoditas.getStok()));
        tvKdLocation.setText(mKomoditas.getLokasi());
        tvKdProfit.setText(String.valueOf(mKomoditas.getProfit()));
        tvKdContract.setText(String.valueOf(mKomoditas.getMinKontrak()));
        tvKdPlanted.setText(mKomoditas.getPlanted());
        tvKdDeskripsi.setText(Html.fromHtml(mKomoditas.getDeskripsi()));
        Picasso
                .with(this)
                .load(mKomoditas.getImgUrl())
                .into(mainBackdrop);
    }
}
