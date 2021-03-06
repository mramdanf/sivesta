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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sivestafunder.android.Activity.KomoditasDetailActivity;
import com.sivestafunder.android.Activity.UpdateProgressInvestActivity;
import com.sivestafunder.android.Adapters.ListMySeedsAdapter;
import com.sivestafunder.android.ApiRespWrapper.ListNewSeeds;
import com.sivestafunder.android.Helpers.AppConst;
import com.sivestafunder.android.Helpers.RecyclerItemClickListener;
import com.sivestafunder.android.Models.Kontrak;
import com.sivestafunder.android.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MySeedsFragment extends Fragment implements
        RecyclerItemClickListener.OnItemClickListener,
        AdapterView.OnItemSelectedListener {

    @BindView(R.id.rec_my_seeds)
    RecyclerView recMySeeds;
    @BindView(R.id.spinner_filter)
    Spinner spinnerFilter;
    @BindView(R.id.img_no_data)
    ImageView imgNoData;
    @BindView(R.id.tv_myseeds_nodata)
    TextView tvMyseedsNodata;
    @BindView(R.id.wrapper_no_data)
    LinearLayout wrapperNoData;
    @BindView(R.id.swiper_my_seeds)
    SwipeRefreshLayout swiperMySeeds;

    private List<Kontrak> kontrakList;
    private ListMySeedsAdapter listMySeedsAdapter;
    private MySeedsFragmentInf mCallback;
    private ProgressDialog mProgressDialog;
    private int mFilterType;
    private final String LOG_TAG = this.getClass().getSimpleName();


    public MySeedsFragment() {
        // Required empty public constructor
    }


    public interface MySeedsFragmentInf {
        void reqListMySeeds(String filter);

        void mySeedsFragmentClickListener(Bundle args);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_my_seeds, container, false);
        ButterKnife.bind(this, rootView);

        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage(getString(R.string.please_wait_tex));
        mProgressDialog.setCancelable(false);

        spinnerFilter.setOnItemSelectedListener(this);

        swiperMySeeds.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                switch (mFilterType) {
                    case 0:
                        mCallback.reqListMySeeds("new_seeds");
                        break;
                    case 1:
                        mCallback.reqListMySeeds("in_progress");
                        break;
                    case 2:
                        mCallback.reqListMySeeds("harvested");
                        break;
                }


            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mProgressDialog.show();
        mCallback.reqListMySeeds("new_seeds");
    }

    @Override
    public void onItemClick(View childView, int position) {
        Kontrak kontrak = kontrakList.get(position);
        if (kontrak.getStatusKontrak() == 1 || kontrak.getStatusKontrak() == 2 || kontrak.getStatusKontrak() == 4) {
            Intent intentKomDetail = new Intent(getActivity(), KomoditasDetailActivity.class);
            intentKomDetail.putExtra(AppConst.OBJ_KOMODITAS, kontrakList.get(position).getKomoditas());
            intentKomDetail.putExtra(AppConst.TAG_INTENT_SRC, this.getClass().getSimpleName());

            startActivity(intentKomDetail);
        } else if (kontrak.getStatusKontrak() == 3) {
            Intent progressIntent = new Intent(getActivity(), UpdateProgressInvestActivity.class);
            progressIntent.putExtra(AppConst.OBJ_KONTRAK, kontrak);
            startActivity(progressIntent);
        }

    }

    @Override
    public void onItemLongPress(View childView, int position) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (MySeedsFragmentInf) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + " Activity should implement MySeedsFragmentInf");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mProgressDialog.show();
        mFilterType = position;
        switch (position) {
            case 0:
                mCallback.reqListMySeeds("new_seeds");
                break;
            case 1:
                mCallback.reqListMySeeds("in_progress");
                break;
            case 2:
                mCallback.reqListMySeeds("harvested");
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @OnClick(R.id.tv_view_seeds)
    public void onViewClicked(View view) {
        Bundle args = new Bundle();
        args.putInt(AppConst.VIEW_ID, view.getId());
        mCallback.mySeedsFragmentClickListener(args);
    }

    public void showMySeedsList(ListNewSeeds listNewSeeds) {

        if (listNewSeeds != null && !mProgressDialog.isShowing())
            Toast.makeText(getActivity(), "Data updated.", Toast.LENGTH_SHORT).show();

        if (mProgressDialog.isShowing())
            mProgressDialog.dismiss();

        swiperMySeeds.setRefreshing(false);

        if (listNewSeeds != null) {

            kontrakList = listNewSeeds.getKontrakList();
            if (kontrakList.size() > 0) {
                showRecycler();
                listMySeedsAdapter = new ListMySeedsAdapter(kontrakList, getActivity());
                setUpRecyclerView();
            } else {
                showNoData();
            }
        }
    }

    private void setUpRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recMySeeds.setLayoutManager(layoutManager);
        recMySeeds.setItemAnimator(new DefaultItemAnimator());
        recMySeeds.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), this));
        recMySeeds.setAdapter(listMySeedsAdapter);
    }

    private void showRecycler() {
        recMySeeds.setVisibility(View.VISIBLE);
        wrapperNoData.setVisibility(View.GONE);
    }

    private void showNoData() {
        recMySeeds.setVisibility(View.GONE);
        wrapperNoData.setVisibility(View.VISIBLE);

        switch (mFilterType) {
            case 0:
                tvMyseedsNodata.setText("There are no new seeds");
                break;
            case 1:
                tvMyseedsNodata.setText("There are no in progress seeds");
                break;
            case 2:
                tvMyseedsNodata.setText("There are no harvested seeds");
                break;
        }
    }



}
