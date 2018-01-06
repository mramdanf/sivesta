package com.sivestafunder.android.Fragmets;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sivestafunder.android.Activity.NewsDetailActivity;
import com.sivestafunder.android.Adapters.ListArtikelAdapter;
import com.sivestafunder.android.ApiRespWrapper.ListArtikelResp;
import com.sivestafunder.android.Helpers.AppConst;
import com.sivestafunder.android.Helpers.RecyclerItemClickListener;
import com.sivestafunder.android.Models.Artikel;
import com.sivestafunder.android.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleFragment extends Fragment implements
        RecyclerItemClickListener.OnItemClickListener {

    @BindView(R.id.swiper_artikel)
    SwipeRefreshLayout swiperArtikel;
    private Context mContext;
    private ListArtikelAdapter listArtikelAdapter;

    private ArticleFragmentInf mCallback;
    private List<Artikel> artikelList;
    private ProgressDialog progressDialog;

    @BindView(R.id.rec_article)
    RecyclerView recArticle;


    public ArticleFragment() {
        // Required empty public constructor
    }

    public interface ArticleFragmentInf {
        void reqFullListArticle();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_article, container, false);
        ButterKnife.bind(this, rootView);

        mContext = getActivity();

        getActivity().setTitle("Articles");

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.please_wait_tex));
        progressDialog.setCancelable(false);

        swiperArtikel.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCallback.reqFullListArticle();
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressDialog.show();
        mCallback.reqFullListArticle();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (ArticleFragmentInf) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + " Acitivity should implement ArticlesFragmentInf");
        }
    }

    @Override
    public void onItemClick(View childView, int position) {
        Intent i = new Intent(getActivity(), NewsDetailActivity.class);
        i.putExtra(AppConst.OBJ_ARTIKEL, artikelList.get(position));
        startActivity(i);
    }

    @Override
    public void onItemLongPress(View childView, int position) {

    }

    private void setUpRVArticles() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        recArticle.setLayoutManager(layoutManager);
        recArticle.setItemAnimator(new DefaultItemAnimator());
        recArticle.addOnItemTouchListener(new RecyclerItemClickListener(mContext, this));
        recArticle.setAdapter(listArtikelAdapter);
    }

    public void showFullArtikel(ListArtikelResp la) {

        if (la != null && !progressDialog.isShowing()) // Triggred by swiper
            Toast.makeText(getActivity(), "Data updated.", Toast.LENGTH_SHORT).show();

        if (progressDialog.isShowing())
            progressDialog.dismiss();

        swiperArtikel.setRefreshing(false);
        artikelList = la.getArtikelList();
        listArtikelAdapter = new ListArtikelAdapter(artikelList, mContext);
        setUpRVArticles();
    }
}
