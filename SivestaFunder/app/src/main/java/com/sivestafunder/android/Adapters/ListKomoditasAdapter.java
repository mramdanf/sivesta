package com.sivestafunder.android.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sivestafunder.android.Helpers.Utility;
import com.sivestafunder.android.Models.Komoditas;
import com.sivestafunder.android.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ListKomoditasAdapter extends RecyclerView.Adapter<ListKomoditasAdapter.RecyclerViewHolder> {

    private List<Komoditas> komoditasList;
    private Context context;

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.nm_kom)
        TextView namaKomoditas;
        @BindView(R.id.harga_kom)
        TextView hargaKom;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public ListKomoditasAdapter(List<Komoditas> komoditasList, Context context) {
        this.komoditasList = komoditasList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_komoditas_item, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Komoditas k = komoditasList.get(position);
        holder.namaKomoditas.setText(Utility.getSafeSubstring(k.getNama(), 12));
        holder.hargaKom.setText(String.valueOf(k.getHarga()));
    }

    @Override
    public int getItemCount() {
        return komoditasList.size();
    }
}
