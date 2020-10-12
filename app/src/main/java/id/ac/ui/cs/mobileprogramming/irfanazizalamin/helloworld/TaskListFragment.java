package id.ac.ui.cs.mobileprogramming.irfanazizalamin.helloworld;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.irfanazizalamin.helloworld.databinding.FragmentTaskListBinding;
import id.ac.ui.cs.mobileprogramming.irfanazizalamin.helloworld.databinding.TaskListItemBinding;

public class TaskListFragment extends Fragment {
    FragmentTaskListBinding binding;
    TaskDetailsFragment detailsFragment = new TaskDetailsFragment();

    public TaskListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_list, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedViewModel viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        List<Item> list = new ArrayList<>();
        list.add(new Item("Monday", "PMPL", "Learn how to struggling in this course"));
        list.add(new Item("Tuesday", "TKTPL", "Learn how to Fragment works"));
        list.add(new Item("Wednesday", "DAA", "Learn how to big oh notation works"));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(list);
        binding.recyclerView.setAdapter(adapter);
        adapter.setListener((v, position) -> {
            viewModel.setSelected(adapter.getItemAt(position));
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.container, detailsFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }
}