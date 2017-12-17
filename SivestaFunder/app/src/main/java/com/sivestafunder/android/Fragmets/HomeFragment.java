package com.sivestafunder.android.Fragmets;


import android.content.Context;
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

import com.sivestafunder.android.Adapters.ListArtikelAdapter;
import com.sivestafunder.android.Adapters.ListKomoditasAdapter;
import com.sivestafunder.android.ApiRespWrapper.ListArtikelResp;
import com.sivestafunder.android.ApiRespWrapper.ListKomoditasResp;
import com.sivestafunder.android.Helpers.RecyclerItemClickListener;
import com.sivestafunder.android.Models.Artikel;
import com.sivestafunder.android.Models.Komoditas;
import com.sivestafunder.android.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements
        RecyclerItemClickListener.OnItemClickListener {

    private Context mContext;
    private ListKomoditasAdapter listKomoditasAdapter;
    private ListArtikelAdapter listArtikelAdapter;

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
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, rootView);

        mContext = getActivity();
        //getActivity().setTitle("Home");

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCallback.reqListKomoditas();
        mCallback.reqListArtikel();
    }

    @Override
    public void onItemClick(View childView, int position) {

    }

    @Override
    public void onItemLongPress(View childView, int position) {

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

    private void setUpRVPopKomoditas(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        recyclerKomoditas.setLayoutManager(layoutManager);
        recyclerKomoditas.setItemAnimator(new DefaultItemAnimator());
        recyclerKomoditas.addOnItemTouchListener(new RecyclerItemClickListener(mContext, this));
        recyclerKomoditas.setAdapter(listKomoditasAdapter);
    }

    private void setUpRVArticles(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerArticles.setLayoutManager(layoutManager);
        recyclerArticles.setItemAnimator(new DefaultItemAnimator());
        recyclerArticles.addOnItemTouchListener(new RecyclerItemClickListener(mContext, this));
        recyclerArticles.setAdapter(listArtikelAdapter);
    }

    public void showKomoditas(ListKomoditasResp lk) {
        loaderPopSeed.setVisibility(View.GONE);
        wrapperPopSeed.setVisibility(View.VISIBLE);

        List<Komoditas> komoditasList = lk.getKomoditasList();
        listKomoditasAdapter = new ListKomoditasAdapter(komoditasList, mContext, true);
        setUpRVPopKomoditas();
    }

    public void showArtikel(ListArtikelResp la) {
        loaderArticles.setVisibility(View.GONE);
        wrapperArtikel.setVisibility(View.VISIBLE);

        List<Artikel> artikelList = la.getArtikelList();
        listArtikelAdapter = new ListArtikelAdapter(artikelList, mContext);
        setUpRVArticles();
    }
}
