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

public class ExerciseRecyclerViewAdapter extends RecyclerView.Adapter<ExerciseRecyclerViewAdapter.ExerciseViewHolder> {

    private final List<ExerciseModel> exercises;
    private final Context context;

    public ExerciseRecyclerViewAdapter(Context context, List<ExerciseModel> exerciseList) {
        this.context = context;
        this.exercises = exerciseList;
    }

    @NonNull
    @Override
    public ExerciseRecyclerViewAdapter.ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_exercise, parent, false);
        return new ExerciseRecyclerViewAdapter.ExerciseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseRecyclerViewAdapter.ExerciseViewHolder holder, int position) {
        ExerciseModel currentItem = exercises.get(position);
        /*holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SkillModel.skill = currentItem;
                NavHostFragment.findNavController(fragment)
                        .navigate(R.id.action_FirstFragment_to_SkillFragment);
            }
        });*/


        LocalDateTime recentDate = currentItem.getDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MMM dd HH:mm");
        String formattedDate = recentDate.format(formatter);
        holder.dateTextView.setText(formattedDate);
        String score = Utilities.roundToTwoDecimalPlaces(currentItem.getScore()) + "%";
        holder.valueTextView.setText(score);



        List<String> keysList = new ArrayList<>(currentItem.getResults().keySet());
        MetricRecyclerViewAdapter adapter = new MetricRecyclerViewAdapter(context, keysList, currentItem.getResults());
        holder.recyclerView.setLayoutManager(new GridLayoutManager(context,1));

        holder.recyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        final TextView valueTextView;
        final TextView dateTextView;
        final RecyclerView recyclerView;
        final CardView cardView;
        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.text_view_date);
            valueTextView = itemView.findViewById(R.id.text_view_value);
            recyclerView = itemView.findViewById(R.id.recyclerViewMetrics);
            cardView = itemView.findViewById(R.id.card_view_item);
        }
    }
}
