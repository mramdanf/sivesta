package com.sivestafunder.android.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sivestafunder.android.Adapters.ListSimulationAdapter;
import com.sivestafunder.android.ApiRespWrapper.ListSimulationResp;
import com.sivestafunder.android.Helpers.AppConst;
import com.sivestafunder.android.Helpers.RecyclerItemClickListener;
import com.sivestafunder.android.Helpers.Utility;
import com.sivestafunder.android.Models.Komoditas;
import com.sivestafunder.android.Models.Simulation;
import com.sivestafunder.android.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SimulationActivity extends AppCompatActivity implements
        RecyclerItemClickListener.OnItemClickListener {

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
    @BindView(R.id.rec_simulation)
    RecyclerView recSimulation;
    @BindView(R.id.sim_img_komoditas)
    ImageView simImgKomoditas;

    private int countJmlItem;
    private Komoditas mKomoditas;
    private final String LOG_TAG = this.getClass().getSimpleName();
    private ProgressDialog mProgressDialog;
    private ListSimulationAdapter listSimulationAdapter;
    private List<Simulation> simulationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation);
        ButterKnife.bind(this);

        setTitle("Simulation");

        Intent i = getIntent();
        mKomoditas = i.getParcelableExtra(AppConst.OBJ_KOMODITAS);
        populateKomoditasDetail();

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.please_wait_tex));
        mProgressDialog.setCancelable(false);

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

    @OnClick(R.id.btn_calculate_simulation)
    public void onViewClicked() {
        mProgressDialog.show();
        new Komoditas()
                .getSimulation(
                        mKomoditas.getIdKomoditas(),
                        Integer.valueOf(tvJmlItem.getText().toString()),
                        new Komoditas.KomoditasModelInf() {
                            @Override
                            public void komoditasModelApiCallback(Bundle args) {
                                mProgressDialog.dismiss();
                                ListSimulationResp resp = args.getParcelable(AppConst.LIST_OBJ_SIMULATION);
                                if (resp != null) {
                                    Log.d(LOG_TAG, "size: " + resp.getSimulationList().size());
                                    simulationList = resp.getSimulationList();
                                    listSimulationAdapter = new ListSimulationAdapter(simulationList, SimulationActivity.this);
                                    setupRecyclerview();
                                } else {
                                    Log.d(LOG_TAG, "no resp");
                                }

                            }
                        }
                );
    }

    @Override
    public void onItemClick(View childView, int position) {

    }

    @Override
    public void onItemLongPress(View childView, int position) {

    }

    private void populateKomoditasDetail() {
        simNamaKom.setText(mKomoditas.getNama());
        simHargaKom.setText(mKomoditas.getHargaText() + "/Units");
        simProfitKom.setText(String.valueOf(mKomoditas.getProfit()) + " %");
        simMinKontrak.setText(String.valueOf(mKomoditas.getMinKontrak()) + " year(s)");
        Picasso
                .with(this)
                .load(mKomoditas.getImgUrl())
                .into(simImgKomoditas);
    }

    private void calculateJmlTotal() {
        int value = Integer.valueOf(tvJmlItem.getText().toString());
        int subTotal = value * mKomoditas.getHarga();
        String subTotalText = Utility.formRupiah(subTotal);
        simTotalBiaya.setText(subTotalText);
    }

    private void setupRecyclerview() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recSimulation.setLayoutManager(layoutManager);
        recSimulation.setItemAnimator(new DefaultItemAnimator());
        recSimulation.addOnItemTouchListener(new RecyclerItemClickListener(this, this));
        recSimulation.setAdapter(listSimulationAdapter);
    }

    @OnClick(R.id.sim_invest_nowbtn)
    public void simInvestNowClickHandle() {
        Intent intent = new Intent(this, InvestNowActivity.class);
        intent.putExtra(AppConst.OBJ_KOMODITAS, mKomoditas);
        startActivity(intent);
        finish();
    }
}
