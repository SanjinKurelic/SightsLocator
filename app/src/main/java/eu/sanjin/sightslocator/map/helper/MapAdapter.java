package eu.sanjin.sightslocator.map.helper;

import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;

import eu.sanjin.model.Sight;
import eu.sanjin.sightslocator.databinding.ItemMapInfoWindowBinding;
import lombok.Setter;

public class MapAdapter implements GoogleMap.InfoWindowAdapter {

  @Setter
  ItemMapInfoWindowBinding binding;

  @Override
  public View getInfoWindow(Marker marker) {
    if (!(marker.getTag() instanceof Sight)) {
      return null;
    }

    Sight sight = (Sight) marker.getTag();
    binding.mapInfoTitle.setText(sight.getTitle());
    Picasso.get()
      .load(new File(sight.getImageUrl()))
      .into(binding.mapInfoImage, new Callback() {
        @Override
        public void onSuccess() {
          if (marker.isInfoWindowShown()) {
            marker.hideInfoWindow();
            marker.showInfoWindow();
          }
        }

        @Override
        public void onError(Exception e) {
        }
      });

    return binding.getRoot();
  }

  @Override
  public View getInfoContents(Marker marker) {
    return null;
  }
}
