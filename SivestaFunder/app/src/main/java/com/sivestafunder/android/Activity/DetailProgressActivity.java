package com.sivestafunder.android.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.sivestafunder.android.Helpers.AppConst;
import com.sivestafunder.android.Models.Progress;
import com.sivestafunder.android.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DetailProgressActivity extends AppCompatActivity {

    @BindView(R.id.imgbackdrop_detail_progress)
    ImageView imgbackdropDetailProgress;
    @BindView(R.id.detail_progress_collapsing)
    CollapsingToolbarLayout detailProgressCollapsing;
    @BindView(R.id.progress_detail_postedat)
    TextView progressDetailPostedat;
    @BindView(R.id.detail_progress_comments)
    TextView detailProgressComments;


    private Progress mProgressInvestasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_progress);
        ButterKnife.bind(this);

        Intent i = getIntent();
        mProgressInvestasi = i.getParcelableExtra(AppConst.OBJ_PROGRESS);
        populateDetailProgress();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void populateDetailProgress() {
        Picasso
                .with(this)
                .load(mProgressInvestasi.getImgUrl())
                .into(imgbackdropDetailProgress);

        Log.d(this.getClass().getSimpleName(), "progress: " + mProgressInvestasi.getPostedAt());
        progressDetailPostedat.setText(mProgressInvestasi.getPostedAt());
        detailProgressComments.setText(Html.fromHtml(mProgressInvestasi.getTextProgress()));
        detailProgressCollapsing.setExpandedTitleTextAppearance(R.style.CollapsedAppBar);
        detailProgressCollapsing.setTitle("Progress Detail");
    }

    @OnClick(R.id.btn_finish_detail)
    public void onViewClicked() {
        finish();
    }
}
