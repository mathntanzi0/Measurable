package com.netanzi.measurable;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.netanzi.measurable.databinding.FragmentFirstBinding;
import com.netanzi.measurable.databinding.FragmentResultsBinding;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ResultsFragment extends Fragment {

    FragmentResultsBinding binding;
    InputMetricRecyclerViewAdapter metricRecyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (SkillModel.skill == null)
            NavHostFragment.findNavController(ResultsFragment.this).navigate(R.id.action_ResultsFragment_to_FirstFragment);

        binding = FragmentResultsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (SkillModel.skill == null)
            return;
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder values = new StringBuilder();
                ExerciseModel exercise = new ExerciseModel(SkillModel.skill.getExercisesSize()+1, LocalDateTime.now());

                List<String> enteredValues = metricRecyclerViewAdapter.getEnteredValues();
                List<String> metrics = SkillModel.skill.getMetrics();

                if (metrics.size() != enteredValues.size()) {
                    Toast.makeText(getContext(),"Enter in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                double sum = 0;
                for (int i = 0; i < metrics.size(); i++) {
                    String metric = metrics.get(i);
                    String enteredValue = enteredValues.get(i);

                    try {
                        double value = Double.parseDouble(enteredValue);
                        sum += value;
                        exercise.addResult(metric, value);
                    } catch (NumberFormatException e) {
                        Toast.makeText(getContext(),"Invalid input", Toast.LENGTH_SHORT).show();
                    }
                }
                SkillModel.skill.addExercise(exercise);
                SkillModel.skill.setScore(sum/SkillModel.skill.getMetricsSize());
                SkillModel.skills.set(SkillModel.skill.getSkillID()-1, SkillModel.skill);
                NavHostFragment.findNavController(ResultsFragment.this).popBackStack();
                //binding.tvOutput.setText(values);
            }
        });


        metricRecyclerViewAdapter = new InputMetricRecyclerViewAdapter(requireContext(), SkillModel.skill.getMetrics());
        binding.recyclerviewInputMetrics.setLayoutManager(new GridLayoutManager(requireContext(),1));
        binding.recyclerviewInputMetrics.setAdapter(metricRecyclerViewAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}