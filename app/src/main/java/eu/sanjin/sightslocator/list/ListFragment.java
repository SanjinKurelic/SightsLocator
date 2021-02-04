package eu.sanjin.sightslocator.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import eu.sanjin.model.Sight;
import eu.sanjin.sightslocator.MainViewActivity;
import eu.sanjin.sightslocator.databinding.FragmentListBinding;
import eu.sanjin.sightslocator.list.helper.ListAdapter;
import eu.sanjin.sightslocator.list.helper.ListTouchHelper;
import eu.sanjin.sightslocator.list.helper.ListTouchListener;
import eu.sanjin.viewmodel.SightViewModel;

public class ListFragment extends Fragment implements ListTouchListener {

  private ListAdapter adapter;
  private SightViewModel viewModel;
  private FragmentListBinding binding;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = FragmentListBinding.inflate(getLayoutInflater());
    binding.progressBar.setVisibility(View.VISIBLE);

    viewModel = new ViewModelProvider(this).get(SightViewModel.class);
    viewModel.init(this);
    viewModel.getList().observe(this.getViewLifecycleOwner(), this::refreshAdapter);
    viewModel.loadSightListData(this.getContext());

    adapter = new ListAdapter(viewModel);
    binding.sightList.setLayoutManager(new LinearLayoutManager(getContext()));
    binding.sightList.setAdapter(adapter);

    // Touch helper
    new ItemTouchHelper(new ListTouchHelper(0, ItemTouchHelper.LEFT, this))
      .attachToRecyclerView(binding.sightList);

    return binding.getRoot();
  }

  public void refreshAdapter(ArrayList<Sight> list) {
    binding.progressBar.setVisibility(View.GONE);
    adapter.notifyDataSetChanged();
  }

  @Override
  public void onSwiped(int position) {
    binding.progressBar.setVisibility(View.VISIBLE);
    viewModel.deleteListData(this.getContext(), position);
  }

  @Override
  public void onItemClick() {
    ((MainViewActivity) requireContext()).showInfo();
  }
}
