package eu.sanjin.sightslocator.list.view;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.File;

import eu.sanjin.contracts.ViewHolderContract;
import eu.sanjin.parser.ResponseTransformer;
import eu.sanjin.sightslocator.databinding.ItemSightListItemBinding;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
import lombok.Getter;

public class ListViewHolder extends RecyclerView.ViewHolder implements ViewHolderContract {

  @Getter
  private final ItemSightListItemBinding binding;

  public ListViewHolder(@NonNull View itemView) {
    super(itemView);
    binding = ItemSightListItemBinding.bind(itemView);
  }

  @Override
  public void setTitle(String title) {
    binding.tvSightListTitle.setText(title);
  }

  @Override
  public void setImage(String imagePath) {
    Picasso.get()
      .load(new File(imagePath))
      .transform(new RoundedCornersTransformation(ResponseTransformer.IMAGE_RADIUS, ResponseTransformer.IMAGE_MARGIN))
      .into(binding.ivSightListImage);
  }
}
