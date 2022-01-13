package com.example.proapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proapp.R;
import com.example.proapp.adapters.JobsAdapter;
import com.example.proapp.databinding.FragmentHomeBinding;
import com.example.proapp.models.Job;
import com.example.proapp.utils.ObjectBox;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    Box<Job> jobBox = ObjectBox.get().boxFor(Job.class);
    JobsAdapter jobsAdapter;
    RecyclerView jobsRecyclerView;
    List<Job> jobs = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        jobsRecyclerView = root.findViewById(R.id.jobs_recyclerview);
        jobsRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        jobsRecyclerView.setNestedScrollingEnabled(true);

        jobsAdapter = new JobsAdapter(getActivity(), jobs, homeViewModel);
        jobsRecyclerView.setAdapter(jobsAdapter);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        homeViewModel.getJobs().observe(requireActivity(), job_list -> {
            jobs.clear();
            jobs.addAll(job_list);
            jobsAdapter.notifyDataSetChanged();
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}