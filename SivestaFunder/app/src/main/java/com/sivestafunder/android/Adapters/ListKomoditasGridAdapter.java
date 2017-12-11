package com.sivestafunder.android.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sivestafunder.android.Models.Komoditas;
import com.sivestafunder.android.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sivesta on 11/12/2017.
 */

public class ListKomoditasGridAdapter extends BaseAdapter {

    private List<Komoditas> komoditasList;
    private Context mContext;

    public ListKomoditasGridAdapter(List<Komoditas> komoditasList, Context c) {
        this.komoditasList = komoditasList;
        this.mContext = c;
    }

    @Override
    public int getCount() {
        return komoditasList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Komoditas k = komoditasList.get(position);
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.list_komoditas_item, null);
        }

        TextView tvHrgKom = (TextView) convertView.findViewById(R.id.harga_kom);
        TextView tvNamaKom = (TextView) convertView.findViewById(R.id.nm_kom);

        tvHrgKom.setText(String.valueOf(k.getHarga()));
        tvNamaKom.setText(k.getNama());

        return convertView;
    }
}
