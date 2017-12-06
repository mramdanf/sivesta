package com.sivestafunder.android.Fragmets;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sivestafunder.android.Adapters.ListKomoditasAdapter;
import com.sivestafunder.android.ApiRespWrapper.ListKomoditasResp;
import com.sivestafunder.android.Helpers.AppConst;
import com.sivestafunder.android.Helpers.RecyclerItemClickListener;
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
    private List<Komoditas> komoditasList;
    private ListKomoditasAdapter listKomoditasAdapter;

    @BindView(R.id.rec_home_kom)
    RecyclerView recyclerKomoditas;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, rootView);

        mContext = getActivity();
        getActivity().setTitle("Home");

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            ListKomoditasResp res = args.getParcelable(AppConst.LIST_OBJ_KOMODITAS);

            komoditasList = res.getKomoditasList();
            listKomoditasAdapter = new ListKomoditasAdapter(komoditasList, mContext);
            setUpRecyclerView();
        }
    }

    private void setUpRecyclerView(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        recyclerKomoditas.setLayoutManager(layoutManager);
        recyclerKomoditas.setItemAnimator(new DefaultItemAnimator());
        recyclerKomoditas.addOnItemTouchListener(new RecyclerItemClickListener(mContext, this));
        recyclerKomoditas.setAdapter(listKomoditasAdapter);
    }

    @Override
    public void onItemClick(View childView, int position) {

    }

    @Override
    public void onItemLongPress(View childView, int position) {

    }
}
