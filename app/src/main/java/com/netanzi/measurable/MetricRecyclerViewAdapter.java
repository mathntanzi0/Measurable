package com.netanzi.measurable;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

public class MetricRecyclerViewAdapter extends RecyclerView.Adapter<MetricRecyclerViewAdapter.MetricViewHolder> {

    private final Context context;
    private final List<String> metrics;
    private final Map<String, Double> metricValues;

    public MetricRecyclerViewAdapter(Context context, List<String> metrics, Map<String, Double> metricValues) {
        this.context = context;
        this.metrics = metrics;
        this.metricValues = metricValues;
    }

    @NonNull
    @Override
    public MetricViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.metric_item, parent, false);
        return new MetricViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MetricViewHolder holder, int position) {
        String metric = metrics.get(position);
        holder.textViewMetric.setText(metric);
        holder.textViewValue.setText(metricValues.get(metric).toString());
        holder.textViewValue.setTextColor(getColorForValue(metricValues.get(metric)));
    }

    @Override
    public int getItemCount() {
        return metrics.size();
    }

    public int getColorForValue(double value) {
        int redColor = Color.RED;
        int orangeColor = Color.parseColor("#FFA500");
        int greenColor = Color.GREEN;

        int color;

        if (value <= 30) {
            // Red to Orange
            color = interpolateColors(redColor, orangeColor, value / 30.0);
        } else {
            // Orange to Green
            color = interpolateColors(orangeColor, greenColor, (value - 30) / (100 - 30));
        }

        return color;
    }

    private int interpolateColors(int color1, int color2, double ratio) {
        float[] hsvColor1 = new float[3];
        float[] hsvColor2 = new float[3];
        float[] interpolatedColor = new float[3];

        Color.colorToHSV(color1, hsvColor1);
        Color.colorToHSV(color2, hsvColor2);

        for (int i = 0; i < 3; i++) {
            interpolatedColor[i] = (float) ((1 - ratio) * hsvColor1[i] + ratio * hsvColor2[i]);
        }

        return Color.HSVToColor(interpolatedColor);
    }

    static class MetricViewHolder extends RecyclerView.ViewHolder {
        final TextView textViewMetric;
        final TextView textViewValue;

        MetricViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMetric = itemView.findViewById(R.id.textViewMetric);
            textViewValue = itemView.findViewById(R.id.textViewValue);
        }
    }
}
