package eu.sanjin.sightslocator.item;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import eu.sanjin.handlers.PreferenceHandler;
import eu.sanjin.handlers.PreferenceKey;
import eu.sanjin.model.Sight;
import eu.sanjin.sightslocator.databinding.FragmentItemBinding;
import eu.sanjin.sightslocator.item.view.ItemAdapter;
import eu.sanjin.viewmodel.SightViewModel;

public class ItemFragment extends Fragment {

  private ItemAdapter adapter;
  private FragmentItemBinding binding;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = FragmentItemBinding.inflate(getLayoutInflater());

    // Set view model
    SightViewModel viewModel = new ViewModelProvider(this).get(SightViewModel.class);
    viewModel.init(null);
    viewModel.getList().observe(this.getViewLifecycleOwner(), this::refreshAdapter);

    // Set adapter
    adapter = new ItemAdapter(viewModel);
    binding.sight.setAdapter(adapter);

    // Load sight data
    viewModel.loadSightListData(this.getContext());

    return binding.getRoot();
  }

  private void refreshAdapter(ArrayList<Sight> sights) {
    adapter.notifyDataSetChanged();
    binding.sight.post(() -> binding.sight.setCurrentItem(PreferenceHandler.getIntPreference(getContext(), PreferenceKey.SIGHT_ITEM_POSITION)));
  }
}
