package com.sivesta.androidfarmer.Fragments;



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

import com.sivesta.androidfarmer.Adapters.ListKomoditasAdapter;
import com.sivesta.androidfarmer.ApiRespWrapper.ListKomoditasResp;
import com.sivesta.androidfarmer.Helpers.AppConst;
import com.sivesta.androidfarmer.Helpers.RecyclerItemClickListener;
import com.sivesta.androidfarmer.Models.Komoditas;
import com.sivesta.androidfarmer.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class KomoditasFragment extends Fragment implements
        RecyclerItemClickListener.OnItemClickListener {

    private KomoditasFragmentInf mCallback;
    private List<Komoditas> komoditasList;
    private ListKomoditasAdapter listKomoditasAdapter;
    private Context mContext;

    @BindView(R.id.recycler_komoditas)
    RecyclerView recyclerKomoditas;


    public KomoditasFragment() {
        // Required empty public constructor
    }

    public interface KomoditasFragmentInf {
        void komoditasFragmentListener(Bundle args);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_komoditas, container, false);

        ButterKnife.bind(this, rootView);
        mContext = getActivity();
        getActivity().setTitle("Komoditas");

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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (KomoditasFragmentInf) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + " activity must implemnt EditProfileInf.");
        }
    }

    @OnClick(R.id.btn_add_komoditas) public void goToAddKomoditas(View v) {
        Bundle args = new Bundle();
        args.putInt(AppConst.VIEW_ID, v.getId());
        mCallback.komoditasFragmentListener(args);
    }

    @Override
    public void onItemClick(View childView, int position) {
        Bundle args = new Bundle();
        args.putParcelable(AppConst.OBJ_KOMODITAS, komoditasList.get(position));
        args.putInt(AppConst.VIEW_ID, AppConst.LIST_CLICK_ID);
        mCallback.komoditasFragmentListener(args);
    }

    @Override
    public void onItemLongPress(View childView, int position) {

    }

    private void setUpRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerKomoditas.setLayoutManager(layoutManager);
        recyclerKomoditas.setItemAnimator(new DefaultItemAnimator());
        recyclerKomoditas.addOnItemTouchListener(new RecyclerItemClickListener(mContext, this));
        recyclerKomoditas.setAdapter(listKomoditasAdapter);
    }

}
