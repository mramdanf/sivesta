package com.sivestafunder.android.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sivestafunder.android.Models.Kontrak;
import com.sivestafunder.android.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sivesta on 3/1/2018.
 */

public class ListMySeedsAdapter extends RecyclerView.Adapter<ListMySeedsAdapter.RecyclerViewHolder> {


    private List<Kontrak> kontrakList;
    private Context mContext;

    public ListMySeedsAdapter(List<Kontrak> kontrakList, Context mContext) {
        this.kontrakList = kontrakList;
        this.mContext = mContext;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ms_img)
        ImageView msImg;
        @BindView(R.id.ms_nama_kom)
        TextView msNamaKom;
        @BindView(R.id.ms_order_status)
        TextView msOrderStatus;
        @BindView(R.id.ms_order_date)
        TextView msOrderDate;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_myseeds_item, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Kontrak kontrak = kontrakList.get(position);
        holder.msNamaKom.setText(kontrak.getKomoditas().getNama());

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        holder.msOrderDate.setText("Ordered: "+String.valueOf(dateFormat.format(kontrak.getTglMulaiKontrak())));

        if (kontrak.getStatusKontrak() == 1) {
            holder.msOrderStatus.setText("Payment Pending");
            holder.msOrderStatus.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.jam_red_48, 0);
            holder.msOrderStatus.setTextColor(mContext.getResources().getColor(R.color.red));
        } else if (kontrak.getStatusKontrak() == 2) {
            holder.msOrderStatus.setText("Assigning Surveyor");
            holder.msOrderStatus.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.man_user_48_green, 0);
            holder.msOrderStatus.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        }

        Picasso
                .with(mContext)
                .load(kontrak.getKomoditas().getImgUrl())
                .into(holder.msImg);
    }

    @Override
    public int getItemCount() {
        return kontrakList.size();
    }

}
