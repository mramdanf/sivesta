package com.sivestafunder.android.Adapters;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sivestafunder.android.Helpers.Utility;
import com.sivestafunder.android.Models.Artikel;
import com.sivestafunder.android.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sivesta on 10/12/2017.
 */

public class ListArtikelAdapter extends RecyclerView.Adapter<ListArtikelAdapter.RecyclerViewHolder> {

    private List<Artikel> artikelList;
    private Context mContext;

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.art_title)
        TextView artTitle;
        @BindView(R.id.art_date)
        TextView artDate;
        @BindView(R.id.art_author)
        TextView artAuthor;
        @BindView(R.id.art_content)
        TextView artContent;
        @BindView(R.id.img_artikel)
        ImageView imageArtikel;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public ListArtikelAdapter(List<Artikel> artikelList, Context c) {
        this.mContext = c;
        this.artikelList = artikelList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_artikel_item, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Artikel a = artikelList.get(position);
        holder.artTitle.setText(a.getJudul());
        holder.artAuthor.setText(a.getPenulis());
        holder.artContent.setText(Utility.getSafeSubstring(a.getKonten(), 60));
        holder.artDate.setText(a.getTglPosting());
        holder.artTitle.setText(a.getJudul());

        Picasso
                .with(mContext)
                .load(a.getImgArtikel())
                .into(holder.imageArtikel);
    }

    @Override
    public int getItemCount() {
        return artikelList.size();
    }
}
