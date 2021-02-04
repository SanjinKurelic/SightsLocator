package eu.sanjin.sightslocator.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Objects;

import eu.sanjin.handlers.PreferenceHandler;
import eu.sanjin.handlers.PreferenceKey;
import eu.sanjin.model.Sight;
import eu.sanjin.sightslocator.MainViewActivity;
import eu.sanjin.sightslocator.R;
import eu.sanjin.sightslocator.databinding.ItemMapInfoWindowBinding;
import eu.sanjin.viewmodel.SightViewModel;
import eu.sanjin.sightslocator.map.view.MapAdapter;

public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

  private static final float MAP_ZOOM_LEVEL = 11f;
  private GoogleMap map;
  private SightViewModel viewModel;
  private ItemMapInfoWindowBinding binding;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_map, container, false);

    // Set info window binding
    View infoWindow = LayoutInflater.from(requireContext()).inflate(R.layout.item_map_info_window, (ViewGroup) view.getParent(), false);
    binding = ItemMapInfoWindowBinding.bind(infoWindow);

    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.maps);
    if (mapFragment != null) {
      mapFragment.getMapAsync(this);

      viewModel = new ViewModelProvider(this).get(SightViewModel.class);
      viewModel.init(null);
      viewModel.getList().observe(this.getViewLifecycleOwner(), this::refreshAdapter);
    }
  }

  private void refreshAdapter(ArrayList<Sight> sights) {
    // First load
    if (sights.isEmpty()) {
      return;
    }
    LatLngBounds.Builder bounds = LatLngBounds.builder();
    sights.forEach(sight -> {
      LatLng latLng = new LatLng(sight.getLocation().getLatitude(), sight.getLocation().getLongitude());
      Marker marker = map.addMarker(
        new MarkerOptions()
          .position(latLng)
          .title(sight.getTitle()));
      marker.setTag(sight);

      // Set bounds
      bounds.include(latLng);
    });
    map.animateCamera(CameraUpdateFactory.newLatLngZoom(bounds.build().getCenter(), MAP_ZOOM_LEVEL));
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {
    map = googleMap;

    // Common settings
    map.getUiSettings().setZoomControlsEnabled(true);
    map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    map.setOnInfoWindowClickListener(this);

    // Set adapter
    MapAdapter mapAdapter = new MapAdapter();
    mapAdapter.setBinding(binding);
    map.setInfoWindowAdapter(mapAdapter);

    viewModel.loadSightListData(this.getContext());
  }

  @Override
  public void onInfoWindowClick(Marker marker) {
    if (!(marker.getTag() instanceof Sight)) {
      return;
    }
    // Get item position and open more info fragment
    @SuppressWarnings("SuspiciousMethodCalls")
    int position = Objects.requireNonNull(viewModel.getList().getValue()).indexOf(marker.getTag());
    PreferenceHandler.setIntPreference(this.requireContext(), PreferenceKey.SIGHT_ITEM_POSITION, position);
    ((MainViewActivity) requireContext()).showInfo();
  }
}
