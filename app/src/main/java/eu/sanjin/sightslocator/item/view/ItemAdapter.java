package eu.sanjin.sightslocator.item.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import eu.sanjin.sightslocator.R;
import eu.sanjin.viewmodel.SightViewModel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

  private final SightViewModel viewModel;

  @NonNull
  @Override
  public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new ItemViewHolder(
      LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sight_item, parent, false)
    );
  }

  @Override
  public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
    viewModel.bindSight(holder, position);
  }

  @Override
  public int getItemCount() {
    return viewModel.getNumberOfItems();
  }
}
