package com.rubydev.basketcourt.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rubydev.basketcourt.R;
import com.rubydev.basketcourt.model.Score;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by irfanandarafifsatrio on 11/1/17.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private Context context;
    private List<Score> list = new ArrayList<>();

    public HistoryAdapter(Context context, List<Score> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoryAdapter.ViewHolder holder, final int position) {
        holder.tvScoreA.setText(String.valueOf(list.get(position).getScore_a()));
        holder.tvScoreB.setText(String.valueOf(list.get(position).getScore_b()));
        holder.tvTeamA.setText(list.get(position).getTeam_a());
        holder.tvTeamB.setText(list.get(position).getTeam_b());
        holder.tvTanggal.setText(list.get(position).getDate());
        String pertandingan = list.get(position).getTeam_a() + " VS " + list.get(position).getTeam_b();
        holder.tvPertandingan.setText(pertandingan);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvScoreA, tvScoreB, tvTeamA, tvTeamB, tvTanggal, tvPertandingan;

        public ViewHolder(View itemView) {
            super(itemView);
            tvScoreA =  itemView.findViewById(R.id.tvScoreA);
            tvScoreB =  itemView.findViewById(R.id.tvScoreB);
            tvTeamA =  itemView.findViewById(R.id.tvTeamA);
            tvTeamB = itemView.findViewById(R.id.tvTeamB);
            tvTanggal = itemView.findViewById(R.id.tvTanggal);
            tvPertandingan = itemView.findViewById(R.id.tvPertandingan);
        }
    }
}
