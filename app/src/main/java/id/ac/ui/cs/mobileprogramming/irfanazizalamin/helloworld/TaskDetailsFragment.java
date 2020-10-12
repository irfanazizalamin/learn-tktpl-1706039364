package id.ac.ui.cs.mobileprogramming.irfanazizalamin.helloworld;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import id.ac.ui.cs.mobileprogramming.irfanazizalamin.helloworld.databinding.FragmentTaskDetailsBinding;

public class TaskDetailsFragment extends Fragment {
    private FragmentTaskDetailsBinding binding;

    public TaskDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_details, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedViewModel viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        viewModel.getSelected().observe(getViewLifecycleOwner(), item -> {
            binding.day.setText(item.getDay());
            binding.task.setText(item.getTask());
            binding.details.setText(item.getDetails());
        });
    }
}