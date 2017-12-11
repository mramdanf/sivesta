package com.sivestafunder.android.Fragmets;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.sivestafunder.android.Adapters.ListKomoditasAdapter;
import com.sivestafunder.android.Adapters.ListKomoditasGridAdapter;
import com.sivestafunder.android.ApiRespWrapper.ListKomoditasResp;
import com.sivestafunder.android.Models.Komoditas;
import com.sivestafunder.android.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CatalogFragment extends Fragment {

    private ListKomoditasGridAdapter mListKomoditasGridAdapter;
    private List<Komoditas> mKomoditasList;
    private Context mContext;
    private CataglogFragmentInf mCallback;

    @BindView(R.id.grid_catalog)
    GridView gridCatalog;


    public CatalogFragment() {
        // Required empty public constructor
    }

    public interface CataglogFragmentInf {
        void reqFullListKomoditas();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_catalog, container, false);
        ButterKnife.bind(this, rootView);

        mContext = getActivity();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCallback.reqFullListKomoditas();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (CataglogFragmentInf) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + " Activity shoul implement CatalogFragmentInf");
        }
    }

    public void showAllKomoditas(ListKomoditasResp l) {
        mKomoditasList = l.getKomoditasList();
        mListKomoditasGridAdapter = new ListKomoditasGridAdapter(mKomoditasList, mContext);
        gridCatalog.setAdapter(mListKomoditasGridAdapter);
    }
}
