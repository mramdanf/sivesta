package com.sivestafunder.android.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sivestafunder.android.Helpers.AppConst;
import com.sivestafunder.android.Helpers.Utility;
import com.sivestafunder.android.Models.Funder;
import com.sivestafunder.android.Models.Komoditas;
import com.sivestafunder.android.Models.Kontrak;
import com.sivestafunder.android.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class InvestNowActivity extends AppCompatActivity {

    @BindView(R.id.inv_nama_kom)
    TextView invNamaKom;
    @BindView(R.id.inv_harga_kom)
    TextView invHargaKom;
    @BindView(R.id.inv_profit_kom)
    TextView invProfitKom;
    @BindView(R.id.inv_min_kontrak)
    TextView invMinKontrak;
    @BindView(R.id.inv_desc_popular_kom)
    TextView invDescPopularKom;
    @BindView(R.id.item_units)
    TextView itemUnits;
    @BindView(R.id.inv_total_biaya)
    TextView invTotalBiaya;
    @BindView(R.id.inv_img_kom)
    ImageView invImgKom;
    @BindView(R.id.card_detail_pembayaran)
    CardView cardDetailPembayaran;
    @BindView(R.id.inv_submit_invest)
    Button invSubmitInvest;
    @BindView(R.id.inv_selesai)
    Button invSelesai;
    @BindView(R.id.inv_total_biaya_hasil)
    TextView invTotalBiayaHasil;
    @BindView(R.id.inv_virtual_account)
    TextView invVirtualAccount;

    private int countJmlItem;
    private Komoditas mKomoditas;
    private Funder mFunder;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invest_now);
        ButterKnife.bind(this);

        setTitle("Investment");

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.please_wait_tex));
        mProgressDialog.setCancelable(false);

        Intent i = getIntent();
        mKomoditas = i.getParcelableExtra(AppConst.OBJ_KOMODITAS);
        populateKomoditasDetail();

        mFunder = Utility.getFunderPrefs(this);

        countJmlItem = Integer.valueOf(itemUnits.getText().toString());
        calculateJmlTotal();


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @OnClick(R.id.minus_btn)
    public void onMinusBtnClicked() {
        if (countJmlItem > 1) {
            itemUnits.setText(Integer.toString(--countJmlItem));
            calculateJmlTotal();
        }

    }

    @OnClick(R.id.plus_btn)
    public void onPlusBtnClicked() {
        itemUnits.setText(Integer.toString(++countJmlItem));
        calculateJmlTotal();
    }

    @OnClick(R.id.inv_submit_invest)
    public void onInvSubmitInvestClicked() {
        Kontrak kontrak = new Kontrak();

        kontrak.setIdKomoditas(mKomoditas.getIdKomoditas());
        kontrak.setIdFunder(mFunder.getIdFunder());
        kontrak.setStatusPembayaran(1); // blm bayar
        kontrak.setBiayaTotal(countJmlItem * mKomoditas.getHarga());

        mProgressDialog.show();
        new Kontrak().createKontrakApi(
                kontrak,
                mFunder.getEmail(),
                mFunder.getPassword(),
                new Kontrak.KontrakModelInf() {
                    @Override
                    public void createKontrakApiCallback(Bundle args) {
                        mProgressDialog.dismiss();
                        Kontrak resKontrak = args.getParcelable(AppConst.OBJ_KONTRAK);
                        if (resKontrak != null) {
                            Toast.makeText(
                                    InvestNowActivity.this,
                                    resKontrak.getMsg(),
                                    Toast.LENGTH_LONG).show();

                            invTotalBiayaHasil.setText(Utility.formRupiah(resKontrak.getBiayaTotal()));
                            invVirtualAccount.setText(resKontrak.getVirtualAccount());
                            showSuccessOrder();
                        } else {
                            Toast.makeText(
                                    InvestNowActivity.this,
                                    "Terjadi error crate kontrak",
                                    Toast.LENGTH_LONG).show();
                        }

                    }
                }
        );
    }

    @OnClick(R.id.inv_selesai)
    public void onInvSelesaiClicked() {
        finish();
    }

    private void populateKomoditasDetail() {
        invNamaKom.setText(mKomoditas.getNama());
        invHargaKom.setText(mKomoditas.getHargaText() + "/Units");
        invProfitKom.setText(String.valueOf(mKomoditas.getProfit()) + " %");
        invMinKontrak.setText(String.valueOf(mKomoditas.getMinKontrak()) + " year(s)");
        Picasso
                .with(this)
                .load(mKomoditas.getImgUrl())
                .into(invImgKom);
    }

    private void calculateJmlTotal() {
        int value = Integer.valueOf(itemUnits.getText().toString());
        int subTotal = value * mKomoditas.getHarga();
        String subTotalText = Utility.formRupiah(subTotal);
        invTotalBiaya.setText(subTotalText);
    }

    private void showSuccessOrder() {
        cardDetailPembayaran.setVisibility(View.VISIBLE);
        invSelesai.setVisibility(View.VISIBLE);
        invSubmitInvest.setVisibility(View.GONE);
    }

    private void hideSuccessOrder() {
        cardDetailPembayaran.setVisibility(View.GONE);
        invSelesai.setVisibility(View.GONE);
        invSubmitInvest.setVisibility(View.VISIBLE);
    }
}
