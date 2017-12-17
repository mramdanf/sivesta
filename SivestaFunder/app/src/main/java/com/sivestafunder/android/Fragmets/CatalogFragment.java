package com.sivestafunder.android.Fragmets;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
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
    private ListKomoditasAdapter mListKomoditasAdapter;
    private List<Komoditas> mKomoditasList;
    private Context mContext;
    private CataglogFragmentInf mCallback;

    @BindView(R.id.rec_grid_kom)
    RecyclerView recGridKom;


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

        getActivity().setTitle("Catalog");

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
        mListKomoditasAdapter = new ListKomoditasAdapter(mKomoditasList, mContext, false);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mContext, 2);
        recGridKom.setLayoutManager(mLayoutManager);
        recGridKom.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recGridKom.setItemAnimator(new DefaultItemAnimator());
        recGridKom.setAdapter(mListKomoditasAdapter);
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
