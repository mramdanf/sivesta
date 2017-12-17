package com.sivestafunder.android.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.sivestafunder.android.Helpers.Utility;
import com.sivestafunder.android.Models.Komoditas;
import com.sivestafunder.android.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ListKomoditasAdapter extends RecyclerView.Adapter<ListKomoditasAdapter.RecyclerViewHolder> {

    private List<Komoditas> komoditasList;
    private Context context;
    private boolean isHorizontal;

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.nm_kom)
        TextView namaKomoditas;
        @BindView(R.id.harga_kom)
        TextView hargaKom;
        @BindView(R.id.img_komoditas)
        ImageView imgKomoditas;
        @BindView(R.id.card_komoditas)
        CardView cardKomoditas;


        public RecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public ListKomoditasAdapter(List<Komoditas> komoditasList, Context context, boolean isHorizontal) {
        this.komoditasList = komoditasList;
        this.context = context;
        this.isHorizontal = isHorizontal;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_komoditas_item, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Komoditas k = komoditasList.get(position);
        holder.namaKomoditas.setText(Utility.getSafeSubstring(k.getNama(), 12));
        holder.hargaKom.setText(String.valueOf(k.getHarga()));
        Picasso
                .with(context)
                .load(k.getImgUrl())
                .into(holder.imgKomoditas);
        if (isHorizontal) {
            final float scale = context.getResources().getDisplayMetrics().density;

            CardView.LayoutParams cardLayoutParams = new CardView.LayoutParams(
                    (int) (130 * scale + 0.5f),
                    CardView.LayoutParams.MATCH_PARENT
            );
            cardLayoutParams.setMargins(0, 0, (int) (10 * scale + 0.5f), 0);
            holder.cardKomoditas.setLayoutParams(cardLayoutParams);

        }


    }

    @Override
    public int getItemCount() {
        return komoditasList.size();
    }
}
