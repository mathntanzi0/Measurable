package com.netanzi.measurable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.netanzi.measurable.databinding.FragmentFirstBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();


    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });*/

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_FirstFragment_to_AddSkillFragment);
            }
        });

        ArrayList<String> metrics = new ArrayList<>();
        metrics.add("Hello");
        metrics.add("Math");

        ArrayList<String> metrics1 = new ArrayList<>();
        metrics1.add("Hello");
        metrics1.add("Math");
        metrics1.add("Sleep");
        metrics1.add("Math");
        metrics1.add("Zine");

        SkillModel.skills.add(new SkillModel(1, "Learning", "Just Learning", 60.6, metrics1));
        SkillModel.skills.add(new SkillModel(2, "Kissing", "Just Learning", 70.6, metrics));
        SkillModel.skills.add(new SkillModel(2, "Kissing", "Just Learning", 70.6, metrics1));


        SkillRecyclerViewAdapter skillRecyclerViewAdapter = new SkillRecyclerViewAdapter(requireContext(), SkillModel.skills);
        binding.recyclerviewMetrics.setLayoutManager(new GridLayoutManager(requireContext(),1));
        binding.recyclerviewMetrics.setAdapter(skillRecyclerViewAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}