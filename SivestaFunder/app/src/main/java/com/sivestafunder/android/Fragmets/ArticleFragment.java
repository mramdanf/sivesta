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

import com.sivestafunder.android.Adapters.ListArtikelAdapter;
import com.sivestafunder.android.ApiRespWrapper.ListArtikelResp;
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

    private Context mContext;
    private ListArtikelAdapter listArtikelAdapter;

    private ArticleFragmentInf mCallback;

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

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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

    }

    @Override
    public void onItemLongPress(View childView, int position) {

    }

    private void setUpRVArticles(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        recArticle.setLayoutManager(layoutManager);
        recArticle.setItemAnimator(new DefaultItemAnimator());
        recArticle.addOnItemTouchListener(new RecyclerItemClickListener(mContext, this));
        recArticle.setAdapter(listArtikelAdapter);
    }

    public void showFullArtikel(ListArtikelResp la) {
        List<Artikel> artikelList = la.getArtikelList();
        listArtikelAdapter = new ListArtikelAdapter(artikelList, mContext);
        setUpRVArticles();
    }
}
