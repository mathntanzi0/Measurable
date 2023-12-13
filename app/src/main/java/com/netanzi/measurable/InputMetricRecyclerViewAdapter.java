package com.netanzi.measurable;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class InputMetricRecyclerViewAdapter extends RecyclerView.Adapter<InputMetricRecyclerViewAdapter.InputMetricViewHolder>{

    private final Context context;
    private final List<String> metrics;
    private final SparseArray<String> editTextValues = new SparseArray<>(); // Store entered values

    public InputMetricRecyclerViewAdapter(Context context, List<String> metrics) {
        this.context = context;
        this.metrics = metrics;
    }

    @NonNull
    @Override
    public InputMetricViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_input_metric, parent, false);
        return new InputMetricViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InputMetricViewHolder holder, int position) {
        String metric = metrics.get(position);
        holder.setIsRecyclable(false);
        holder.textViewMetric.setText(metric);

        // Set a TextChangedListener to capture changes
        holder.editTextMetric.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable editable) {
                // Update the corresponding value in your data structure
                editTextValues.put(position, editable.toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return metrics.size();
    }

    public List<String> getEnteredValues() {
        List<String> enteredValues = new ArrayList<>();
        for (int i = 0; i < metrics.size(); i++) {
            enteredValues.add(editTextValues.get(i, ""));
        }
        return enteredValues;
    }

    static class InputMetricViewHolder extends RecyclerView.ViewHolder {
        final TextView textViewMetric;
        final EditText editTextMetric;

        InputMetricViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMetric = itemView.findViewById(R.id.tv_metric_name);
            editTextMetric = itemView.findViewById(R.id.et_metric_value);
        }
    }
}
