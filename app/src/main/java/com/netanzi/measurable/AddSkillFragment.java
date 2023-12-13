package com.netanzi.measurable;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.netanzi.measurable.databinding.FragmentAddSkillBinding;
import com.netanzi.measurable.databinding.FragmentFirstBinding;

import java.util.ArrayList;

public class AddSkillFragment extends Fragment {

    private FragmentAddSkillBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createSkill();
                NavHostFragment.findNavController(AddSkillFragment.this)
                        .navigate(R.id.action_AddSkillFragment_to_FirstFragment);
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddSkillBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void createSkill(){
        String skillTitle = binding.etSkillName.getText().toString();
        String skillDesc = binding.etDescription.getText().toString();
        String metrics = binding.etMetrics.getText().toString();
        ArrayList<String> skillMetrics = new ArrayList<>();

        String[] metricsArray = metrics.split(",");
        for (String metric : metricsArray) {
            String trimmedMetric = metric.trim();
            skillMetrics.add(trimmedMetric);
        }

        SkillModel skill = new SkillModel(SkillModel.skills.size()+1,skillTitle, skillDesc, 0, skillMetrics);
        SkillModel.skills.add(skill);
    }
}