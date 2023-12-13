package com.netanzi.measurable;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

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
        binding.tvSkillScore.setText(String.valueOf(SkillModel.skill.getScore()));

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SkillFragment.this).navigate(R.id.action_SkillFragment_to_ResultsFragment);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}