package eu.sanjin.sightslocator.intro.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Optional;

import eu.sanjin.handlers.PreferenceHandler;
import eu.sanjin.handlers.PreferenceKey;
import eu.sanjin.sightslocator.intro.model.LocationModel;
import lombok.Getter;

public class LocationViewModel extends ViewModel {

  @Getter
  private MutableLiveData<LocationModel> location;

  public void init(Context context) {
    if (location != null) {
      return;
    }
    location = new MutableLiveData<>();
    Optional<String> lastUserLocation = PreferenceHandler.getStringPreference(context, PreferenceKey.LAST_USER_LOCATION);
    location.setValue(new LocationModel(lastUserLocation.orElse(null)));
  }

  public void fetchNewLocation() {
  }
}
