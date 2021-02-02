package eu.sanjin.sightslocator.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import eu.sanjin.sightslocator.databinding.FragmentListBinding;
import eu.sanjin.sightslocator.list.adapter.ListAdapter;
import eu.sanjin.sightslocator.list.viewmodel.ListViewModel;

public class ListFragment extends Fragment {

  private ListAdapter adapter;
  private ListViewModel viewModel;
  private FragmentListBinding binding;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = FragmentListBinding.inflate(getLayoutInflater());

    viewModel = new ViewModelProvider(this).get(ListViewModel.class);
    viewModel.init(this.getContext());

    adapter = new ListAdapter(viewModel);
    binding.sightList.setLayoutManager(new LinearLayoutManager(getContext()));
    binding.sightList.setAdapter(adapter);

    // Touch helper

    return binding.getRoot();
  }
}
