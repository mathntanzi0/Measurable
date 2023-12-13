package com.netanzi.measurable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkillRecyclerViewAdapter extends RecyclerView.Adapter<SkillRecyclerViewAdapter.SkillViewHolder> {

    private final List<SkillModel> metricList;
    private final Context context;
    private final Fragment fragment;

    public SkillRecyclerViewAdapter(Context context, List<SkillModel> metricList, Fragment fragment) {
        this.context = context;
        this.metricList = metricList;
        this.fragment = fragment;
    }

    public static class SkillViewHolder extends RecyclerView.ViewHolder {
        public final TextView titleTextView;
        public final TextView valueTextView;
        public final TextView dateTextView;
        public final RecyclerView recyclerView;
        public final CardView cardView;

        public SkillViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.text_view_title);
            valueTextView = itemView.findViewById(R.id.text_view_value);
            dateTextView = itemView.findViewById(R.id.text_view_date);
            recyclerView = itemView.findViewById(R.id.recyclerViewMetrics);
            cardView = itemView.findViewById(R.id.card_view_item);
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

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SkillModel.skill = currentItem;
                NavHostFragment.findNavController(fragment)
                        .navigate(R.id.action_FirstFragment_to_SkillFragment);
            }
        });

        Map<String, Double> map = new HashMap<>();

        if(currentItem.getExercises() == null || currentItem.getExercisesSize() < 1){
            for (String metric:
                 currentItem.getMetrics()) {
                map.put(metric, 0.0);
            }
        } else {
            LocalDateTime recentDate = currentItem.getRecentDate();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MMM dd HH:mm:ss");

            String formattedDate = recentDate.format(formatter);

            holder.dateTextView.setText("Last updated ~ " + formattedDate);
            for (String metric:
                    currentItem.getMetrics()) {
                map.put(metric, currentItem.getExercises().get(currentItem.getExercisesSize()-1).getValue(metric));
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