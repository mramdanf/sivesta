package com.sivestafunder.android.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sivestafunder.android.Models.Simulation;
import com.sivestafunder.android.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sivesta on 4/1/2018.
 */

public class ListSimulationAdapter extends RecyclerView.Adapter<ListSimulationAdapter.RecyclerViewHolder> {

    private List<Simulation> simulationList;
    private Context mContext;

    public ListSimulationAdapter(List<Simulation> simulationList, Context mContext) {
        this.simulationList = simulationList;
        this.mContext = mContext;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ls_net_profit)
        TextView lsNetProfit;
        @BindView(R.id.ls_year_minkontrak)
        TextView lsYearMinkontrak;
        @BindView(R.id.ls_profit)
        TextView lsProfit;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_simulation_item, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Simulation simulation = simulationList.get(position);
        holder.lsNetProfit.setText(simulation.getNetProfit());
        holder.lsProfit.setText(String.valueOf(simulation.getProfit()) + "%");
        holder.lsYearMinkontrak.setText(String.valueOf(simulation.getYear()));
    }

    @Override
    public int getItemCount() {
        return simulationList.size();
    }
}
