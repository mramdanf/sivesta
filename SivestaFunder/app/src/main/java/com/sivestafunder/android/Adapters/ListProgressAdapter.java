package com.sivestafunder.android.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sivestafunder.android.Helpers.Utility;
import com.sivestafunder.android.Models.Progress;
import com.sivestafunder.android.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sivesta on 4/1/2018.
 */

public class ListProgressAdapter extends RecyclerView.Adapter<ListProgressAdapter.RecyclerViewHolder> {


    private Context mContext;
    private List<Progress> progressList;

    public ListProgressAdapter(Context mContext, List<Progress> progressList) {
        this.mContext = mContext;
        this.progressList = progressList;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_progress)
        ImageView imgProgress;
        @BindView(R.id.tv_prog_desc)
        TextView tvProgDesc;
        @BindView(R.id.progress_posted_at)
        TextView progressPostedAt;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_progress, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Progress progress = progressList.get(position);
        Picasso
                .with(mContext)
                .load(progress.getImgUrl())
                .into(holder.imgProgress);
        holder.tvProgDesc.setText(Utility.getSafeSubstring(progress.getStripedProgressText(), 60));
        holder.progressPostedAt.setText(progress.getPostedAt());

    }

    @Override
    public int getItemCount() {
        return progressList.size();
    }
}
