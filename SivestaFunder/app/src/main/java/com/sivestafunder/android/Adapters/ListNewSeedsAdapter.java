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
 * Created by Sivesta on 1/1/2018.
 */

public class ListNewSeedsAdapter extends RecyclerView.Adapter<ListNewSeedsAdapter.RecyclerViewHolder> {

    private List<Kontrak> kontrakList;
    private Context mContext;

    public ListNewSeedsAdapter(List<Kontrak> kontrakList, Context mContext) {
        this.kontrakList = kontrakList;
        this.mContext = mContext;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ns_img)
        ImageView nsImg;
        @BindView(R.id.ns_nama_kom)
        TextView nsNamaKom;
        @BindView(R.id.ns_order_status)
        TextView nsOrderStatus;
        @BindView(R.id.ns_order_date)
        TextView nsOrderDate;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_newseeds_item, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Kontrak kontrak = kontrakList.get(position);
        holder.nsNamaKom.setText(kontrak.getKomoditas().getNama());

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        holder.nsOrderDate.setText("Ordered: "+String.valueOf(dateFormat.format(kontrak.getTglMulaiKontrak())));

        if (kontrak.getStatusKontrak() == 1) {
            holder.nsOrderStatus.setText("Payment Pending");
            holder.nsOrderStatus.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.jam_red_48, 0);
            holder.nsOrderStatus.setTextColor(mContext.getResources().getColor(R.color.red));
        } else if (kontrak.getStatusKontrak() == 2) {
            holder.nsOrderStatus.setText("Assigning Surveyor");
            holder.nsOrderStatus.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.man_user_48_green, 0);
            holder.nsOrderStatus.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        }

        Picasso
                .with(mContext)
                .load(kontrak.getKomoditas().getImgUrl())
                .into(holder.nsImg);
    }

    @Override
    public int getItemCount() {
        return kontrakList.size();
    }
}
