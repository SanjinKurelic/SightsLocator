package eu.sanjin.sightslocator.list.view;

import android.graphics.Canvas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import eu.sanjin.contracts.ListTouchContract;

public class ListTouchHelper extends ItemTouchHelper.SimpleCallback {

  private final ListTouchContract listener;

  public ListTouchHelper(int dragDirs, int swipeDirs, ListTouchContract listener) {
    super(dragDirs, swipeDirs);
    this.listener = listener;
  }

  @Override
  public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
    return false;
  }

  @Override
  public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
    listener.onSwiped(viewHolder.getAdapterPosition());
  }

  @Override
  public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
    super.onSelectedChanged(viewHolder, actionState);
    if (!Objects.isNull(viewHolder) && viewHolder instanceof ListViewHolder) {
      getDefaultUIUtil().onSelected(((ListViewHolder) viewHolder).getBinding().viewForeground);
    }
  }

  @Override
  public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    if (viewHolder instanceof ListViewHolder) {
      getDefaultUIUtil().onDraw(c, recyclerView, ((ListViewHolder) viewHolder).getBinding().viewForeground, dX, dY, actionState, isCurrentlyActive);
    }
  }

  @Override
  public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
    super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    if (!Objects.isNull(viewHolder) && viewHolder instanceof ListViewHolder) {
      getDefaultUIUtil().onDrawOver(c, recyclerView, ((ListViewHolder) viewHolder).getBinding().viewForeground, dX, dY, actionState, isCurrentlyActive);
    }
  }

  @Override
  public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
    super.clearView(recyclerView, viewHolder);
    if (viewHolder instanceof ListViewHolder) {
      getDefaultUIUtil().clearView(((ListViewHolder) viewHolder).getBinding().viewForeground);
    }
  }
}
