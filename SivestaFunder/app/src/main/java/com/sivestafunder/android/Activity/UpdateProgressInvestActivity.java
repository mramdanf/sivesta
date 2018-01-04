package com.sivestafunder.android.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.sivestafunder.android.Adapters.ListProgressAdapter;
import com.sivestafunder.android.ApiRespWrapper.ListProgressResp;
import com.sivestafunder.android.Helpers.AppConst;
import com.sivestafunder.android.Helpers.RecyclerItemClickListener;
import com.sivestafunder.android.Helpers.Utility;
import com.sivestafunder.android.Models.Funder;
import com.sivestafunder.android.Models.Komoditas;
import com.sivestafunder.android.Models.Kontrak;
import com.sivestafunder.android.Models.Progress;
import com.sivestafunder.android.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class UpdateProgressInvestActivity extends AppCompatActivity implements
        RecyclerItemClickListener.OnItemClickListener {

    @BindView(R.id.img_progress_backdrop)
    ImageView backdrop;
    @BindView(R.id.rec_progres)
    RecyclerView recProgres;
    @BindView(R.id.progress_collapsing)
    CollapsingToolbarLayout progressCollapsing;
    private Kontrak mKontrak;
    private Funder mLoggedinFunder;

    private final String LOG_TAG = this.getClass().getSimpleName();
    private List<Progress> progressList;
    private ListProgressAdapter listProgressAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_progress_invest);
        ButterKnife.bind(this);

        Intent i = getIntent();
        mKontrak = i.getParcelableExtra(AppConst.OBJ_KONTRAK);

        Komoditas k = mKontrak.getKomoditas();
        Picasso
                .with(this)
                .load(k.getImgUrl())
                .into(backdrop);
        progressCollapsing.setExpandedTitleTextAppearance(R.style.CollapsedAppBar);
        progressCollapsing.setTitle(k.getNama());

        mLoggedinFunder = Utility.getFunderPrefs(this);

        populateProgressList();


    }

    private void populateProgressList() {

        new Kontrak()
                .getProgressKontrak(
                        mKontrak,
                        mLoggedinFunder.getEmail(),
                        mLoggedinFunder.getPassword(),
                        new Kontrak.KontrakModelInf() {
                            @Override
                            public void kontrakModelInfCallback(Bundle args) {
                                ListProgressResp resp = args.getParcelable(AppConst.LIST_OBJ_PROGRESS);
                                if (resp != null) {
                                    progressList = resp.getProgressList();
                                    Log.d(LOG_TAG, "size: " + progressList.get(0).getTextProgress());
                                    listProgressAdapter = new ListProgressAdapter(UpdateProgressInvestActivity.this, progressList);
                                    setUpRecyclerView();
                                } else {
                                    Log.d(LOG_TAG, "size 0");
                                }
                            }
                        }
                );
    }

    private void setUpRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recProgres.setLayoutManager(layoutManager);
        recProgres.setItemAnimator(new DefaultItemAnimator());
        recProgres.addOnItemTouchListener(new RecyclerItemClickListener(this, this));
        recProgres.setAdapter(listProgressAdapter);
    }


    @Override
    public void onItemClick(View childView, int position) {

    }

    @Override
    public void onItemLongPress(View childView, int position) {

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @OnClick(R.id.btn_progress_selesai)
    public void onViewClicked() {
        finish();
    }
}
