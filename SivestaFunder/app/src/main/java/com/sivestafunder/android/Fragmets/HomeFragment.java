package com.sivestafunder.android.Fragmets;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.sivestafunder.android.Activity.KomoditasDetailActivity;
import com.sivestafunder.android.Activity.NewsDetailActivity;
import com.sivestafunder.android.Adapters.ListArtikelAdapter;
import com.sivestafunder.android.Adapters.ListKomoditasAdapter;
import com.sivestafunder.android.ApiRespWrapper.ListArtikelResp;
import com.sivestafunder.android.ApiRespWrapper.ListKomoditasResp;
import com.sivestafunder.android.Helpers.AppConst;
import com.sivestafunder.android.Helpers.RecyclerItemClickListener;
import com.sivestafunder.android.Models.Artikel;
import com.sivestafunder.android.Models.Komoditas;
import com.sivestafunder.android.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private Context mContext;
    private ListKomoditasAdapter listKomoditasAdapter;
    private ListArtikelAdapter listArtikelAdapter;
    private List<Artikel> mArtikelList;
    private List<Komoditas> mKomoditasList;

    @BindView(R.id.rec_home_kom)
    RecyclerView recyclerKomoditas;
    @BindView(R.id.rec_home_article)
    RecyclerView recyclerArticles;
    @BindView(R.id.loader_pop_seed)
    ProgressBar loaderPopSeed;
    @BindView(R.id.loader_articles)
    ProgressBar loaderArticles;
    @BindView(R.id.wrapper_pop_seed)
    LinearLayout wrapperPopSeed;
    @BindView(R.id.wrapper_artikel)
    LinearLayout wrapperArtikel;

    private HomeFragmentInf mCallback;


    public HomeFragment() {
        // Required empty public constructor
    }

    public interface HomeFragmentInf {
        void reqListKomoditas();
        void reqListArtikel();
        void homeFragmentClickListener(Bundle args);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, rootView);

        mContext = getActivity();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCallback.reqListKomoditas();
        mCallback.reqListArtikel();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (HomeFragmentInf) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + " activity must implement HomeFragmentInf");
        }
    }

    @OnClick(R.id.more_btn_artikel) public void moreArtikelClickHandle(View v) {
        Bundle args = new Bundle();
        args.putInt(AppConst.VIEW_ID, v.getId());
        mCallback.homeFragmentClickListener(args);

    }

    @OnClick(R.id.more_btn_komoditas) public void moreKomoditasClickHandle(View v) {
        Bundle args = new Bundle();
        args.putInt(AppConst.VIEW_ID, v.getId());
        mCallback.homeFragmentClickListener(args);
    }

    private void setUpRVPopKomoditas(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        recyclerKomoditas.setLayoutManager(layoutManager);
        recyclerKomoditas.setItemAnimator(new DefaultItemAnimator());
        recyclerKomoditas.addOnItemTouchListener(new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View childView, int position) {
                Intent i = new Intent(getActivity(), KomoditasDetailActivity.class);
                i.putExtra(AppConst.OBJ_KOMODITAS, mKomoditasList.get(position));
                startActivity(i);
            }

            @Override
            public void onItemLongPress(View childView, int position) {

            }
        }));
        recyclerKomoditas.setAdapter(listKomoditasAdapter);
    }

    private void setUpRVArticles(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerArticles.setLayoutManager(layoutManager);
        recyclerArticles.setItemAnimator(new DefaultItemAnimator());
        recyclerArticles.addOnItemTouchListener(new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View childView, int position) {
                Intent i = new Intent(getActivity(), NewsDetailActivity.class);
                i.putExtra(AppConst.OBJ_ARTIKEL, mArtikelList.get(position));
                startActivity(i);
            }

            @Override
            public void onItemLongPress(View childView, int position) {

            }
        }));
        recyclerArticles.setAdapter(listArtikelAdapter);
    }

    public void showKomoditas(ListKomoditasResp lk) {
        loaderPopSeed.setVisibility(View.GONE);
        wrapperPopSeed.setVisibility(View.VISIBLE);

        mKomoditasList = lk.getKomoditasList();
        listKomoditasAdapter = new ListKomoditasAdapter(mKomoditasList, mContext, true);
        setUpRVPopKomoditas();
    }

    public void showArtikel(ListArtikelResp la) {
        loaderArticles.setVisibility(View.GONE);
        wrapperArtikel.setVisibility(View.VISIBLE);

        mArtikelList = la.getArtikelList();
        listArtikelAdapter = new ListArtikelAdapter(mArtikelList, mContext);
        setUpRVArticles();
    }
}
