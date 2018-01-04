package com.sivestafunder.android.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.sivestafunder.android.Helpers.AppConst;
import com.sivestafunder.android.Models.Artikel;
import com.sivestafunder.android.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetailActivity extends AppCompatActivity {

    @BindView(R.id.coll_article_detail)
    CollapsingToolbarLayout collArticleDetail;
    @BindView(R.id.appbar_news_detail)
    AppBarLayout appbarArticleDetail;
    @BindView(R.id.news_detail_img)
    ImageView newDetailImg;
//    @BindView(R.id.wb_news_detail)
//    WebView wbNewsDetail;
    @BindView(R.id.nd_tgl)
    TextView tvTglArtikel;
    @BindView(R.id.nd_author)
    TextView tvAuthorArtikel;
    @BindView(R.id.nd_title)
    TextView tvNdTitle;
    @BindView(R.id.artikel_kontent)
    TextView tvArtikelKontent;


    private String mCollapsingTitle;
    private Artikel mArtikel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
        setFullScreen();

        Intent intent = getIntent();
        mArtikel = intent.getParcelableExtra(AppConst.OBJ_ARTIKEL);
        populateNewsData();

        appbarArticleDetail.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collArticleDetail.setTitle(mCollapsingTitle);
                    isShow = true;
                } else if(isShow) {
                    collArticleDetail.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    private void populateNewsData() {
        /*wbNewsDetail.loadUrl(mArtikel.getWebViewUrl());
        WebSettings webSettings = wbNewsDetail.getSettings();
        webSettings.setJavaScriptEnabled(true);*/

        mCollapsingTitle = mArtikel.getJudul();
        tvTglArtikel.setText(mArtikel.getTglPosting());
        tvAuthorArtikel.setText(mArtikel.getPenulis());
        tvNdTitle.setText(mArtikel.getJudul());
        tvArtikelKontent.setText(Html.fromHtml(mArtikel.getFullContent()));
        Picasso
                .with(this)
                .load(mArtikel.getImgArtikel())
                .into(newDetailImg);
    }

    private void setFullScreen() {

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
