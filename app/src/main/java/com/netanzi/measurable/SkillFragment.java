package com.netanzi.measurable;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.netanzi.measurable.databinding.FragmentResultsBinding;
import com.netanzi.measurable.databinding.FragmentSkillBinding;
import com.netanzi.measurable.databinding.FragmentSkillsBinding;

public class SkillFragment extends Fragment {

    FragmentSkillBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (SkillModel.skill == null)
            NavHostFragment.findNavController(SkillFragment.this)
                    .navigate(R.id.action_SkillFragment_to_FirstFragment);
        binding = FragmentSkillBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (SkillModel.skill == null)
            return;

        binding.tvSkillTitle.setText(SkillModel.skill.getTitle());
        binding.tvSkillDesc.setText(SkillModel.skill.getDescription());
        String score = Utilities.roundToTwoDecimalPlaces(SkillModel.skill.getScore()) + "%";
        binding.tvSkillScore.setText(score);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SkillFragment.this).navigate(R.id.action_SkillFragment_to_ResultsFragment);
            }
        });

        ExerciseRecyclerViewAdapter exerciseRecyclerViewAdapter = new ExerciseRecyclerViewAdapter(requireContext(), SkillModel.skill.getExercises());

        GridLayoutManager layoutManager = new GridLayoutManager(requireContext(), 1);
        layoutManager.setReverseLayout(true);

        binding.recyclerviewExercises.setLayoutManager(layoutManager);
        binding.recyclerviewExercises.setAdapter(exerciseRecyclerViewAdapter);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}