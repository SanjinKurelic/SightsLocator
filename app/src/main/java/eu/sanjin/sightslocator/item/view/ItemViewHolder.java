package eu.sanjin.sightslocator.item.view;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.File;

import eu.sanjin.contracts.ViewHolderContract;
import eu.sanjin.parser.ResponseTransformer;
import eu.sanjin.sightslocator.databinding.ItemSightItemBinding;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
import lombok.Getter;

public class ItemViewHolder extends RecyclerView.ViewHolder implements ViewHolderContract {

  @Getter
  private final ItemSightItemBinding binding;

  public ItemViewHolder(@NonNull View itemView) {
    super(itemView);
    binding = ItemSightItemBinding.bind(itemView);
  }

  @Override
  public void setTitle(String title) {
    binding.tvSightItemTitle.setText(title);
  }

  @Override
  public void setImage(String imagePath) {
    Picasso.get()
      .load(new File(imagePath))
      .transform(new RoundedCornersTransformation(ResponseTransformer.IMAGE_RADIUS, ResponseTransformer.IMAGE_MARGIN))
      .into(binding.ivSightItemImage);
  }

  @Override
  public void setDescription(String description) {
    binding.tvSightItemDescription.setText(description);
  }
}
