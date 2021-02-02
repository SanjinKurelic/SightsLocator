package eu.sanjin.sightslocator.list.helper;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import eu.sanjin.sightslocator.R;
import eu.sanjin.sightslocator.list.viewmodel.ListViewModel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ListAdapter extends RecyclerView.Adapter<ListViewHolder> {

  private final ListViewModel viewModel;

  @NonNull
  @Override
  public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new ListViewHolder(
      LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sight_list_item, parent, false)
    );
  }

  @Override
  public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
    // Open info fragment on click
    holder.getBinding().viewForeground.setOnClickListener(v -> viewModel.view(holder.itemView.getContext(), position));
    viewModel.bindSight(holder, position);
  }

  @Override
  public int getItemCount() {
    return viewModel.getNumberOfItems();
  }
}
