package com.sivestafunder.android.Fragmets;


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

import com.sivestafunder.android.Adapters.ListNewSeedsAdapter;
import com.sivestafunder.android.ApiRespWrapper.ListNewSeeds;
import com.sivestafunder.android.Helpers.AppConst;
import com.sivestafunder.android.Helpers.RecyclerItemClickListener;
import com.sivestafunder.android.Helpers.Utility;
import com.sivestafunder.android.Models.Funder;
import com.sivestafunder.android.Models.Kontrak;
import com.sivestafunder.android.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewSeedFragment extends Fragment implements
    RecyclerItemClickListener.OnItemClickListener {

    @BindView(R.id.rec_new_seed)
    RecyclerView recNewSeed;

    private Funder mFunder;
    private ListNewSeedsAdapter listNewSeedsAdapter;
    private final String LOG_TAG = this.getClass().getSimpleName();


    public NewSeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_seed, container, false);
        ButterKnife.bind(this, rootView);

        mFunder = Utility.getFunderPrefs(getActivity());
        getNewSeedFromApi();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onresume");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "On Start");
    }

    private void getNewSeedFromApi() {
        /*new Kontrak().getKontrakMySeeds(
                mFunder.getIdFunder(),
                mFunder.getEmail(),
                mFunder.getPassword(),
                new Kontrak.KontrakModelInf() {
                    @Override
                    public void kontrakModelInfCallback(Bundle args) {
                        ListNewSeeds listNewSeeds = args.getParcelable(AppConst.LIST_OBJ_KONTRAK);
                        if (listNewSeeds != null) {
                            //Log.d(NewSeedFragment.class.getSimpleName(), "size: " + listNewSeeds.getKontrakList().size());
                            List<Kontrak> kontrakList = listNewSeeds.getKontrakList();
                            listNewSeedsAdapter = new ListNewSeedsAdapter(kontrakList, getActivity());
                            setUpRecyclerView();
                        } else {
                            Log.d(NewSeedFragment.class.getSimpleName(), "no new seeds");
                        }


                    }
                });*/
    }

    private void setUpRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recNewSeed.setLayoutManager(layoutManager);
        recNewSeed.setItemAnimator(new DefaultItemAnimator());
        recNewSeed.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), this));
        recNewSeed.setAdapter(listNewSeedsAdapter);
    }

    @Override
    public void onItemClick(View childView, int position) {

    }

    @Override
    public void onItemLongPress(View childView, int position) {

    }
}
