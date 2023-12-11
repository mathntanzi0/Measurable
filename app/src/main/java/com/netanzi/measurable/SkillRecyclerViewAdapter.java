package com.netanzi.measurable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkillRecyclerViewAdapter extends RecyclerView.Adapter<SkillRecyclerViewAdapter.SkillViewHolder> {

    private final List<SkillModel> metricList;
    private final Context context;

    public SkillRecyclerViewAdapter(Context context, List<SkillModel> metricList) {
        this.context = context;
        this.metricList = metricList;
    }

    public static class SkillViewHolder extends RecyclerView.ViewHolder {
        public final TextView titleTextView;
        public final TextView valueTextView;
        public final RecyclerView recyclerView;

        public SkillViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.text_view_title);
            valueTextView = itemView.findViewById(R.id.text_view_value);
            recyclerView = itemView.findViewById(R.id.recyclerViewMetrics);
        }
    }

    @NonNull
    @Override
    public SkillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_skill, parent, false);
        return new SkillViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillViewHolder holder, int position) {
        SkillModel currentItem = metricList.get(position);

        holder.titleTextView.setText(currentItem.getTitle());
        holder.valueTextView.setText(String.valueOf(currentItem.getScore()));

        Map<String, Double> map = new HashMap<>();

        if(currentItem.getExercises() == null || currentItem.getExercises().size() < 1){
            for (String metric:
                 currentItem.getMetrics()) {
                map.put(metric, 0.0);
            }
        } else {
            for (String metric:
                    currentItem.getMetrics()) {
                map.put(metric, currentItem.getExercises().get(0).getValue(metric));
            }
        }



        List<String> keysList = new ArrayList<>(map.keySet());
        MetricRecyclerViewAdapter adapter = new MetricRecyclerViewAdapter(context, keysList, map);
        holder.recyclerView.setLayoutManager(new GridLayoutManager(context,1));

        holder.recyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return metricList.size();
    }
}