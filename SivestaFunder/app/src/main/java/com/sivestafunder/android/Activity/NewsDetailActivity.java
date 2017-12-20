package com.sivestafunder.android.Activity;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sivestafunder.android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetailActivity extends AppCompatActivity {

    @BindView(R.id.coll_article_detail)
    CollapsingToolbarLayout collArticleDetail;
    @BindView(R.id.appbar_news_detail)
    AppBarLayout appbarArticleDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);

        appbarArticleDetail.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collArticleDetail.setTitle("Artikel");
                    isShow = true;
                } else if(isShow) {
                    collArticleDetail.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }
}
