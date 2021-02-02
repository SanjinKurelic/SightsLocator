package eu.sanjin.sightslocator.intro.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Optional;

import eu.sanjin.handlers.DaoHandler;
import eu.sanjin.handlers.PreferenceHandler;
import eu.sanjin.handlers.PreferenceKey;
import eu.sanjin.handlers.RetrofitHandler;
import eu.sanjin.model.Sight;
import eu.sanjin.parser.ResponseTransformer;
import eu.sanjin.service.LocationDataRestApi;
import eu.sanjin.sightslocator.intro.model.LocationModel;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.Getter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class LocationViewModel extends ViewModel {

  @Getter
  private MutableLiveData<LocationModel> location;

  private static final String REST_CALL_ERROR_MESSAGE = "Error while fetching data from server. Please check internet connection and try opening application again.";

  public void init(Context context) {
    if (location != null) {
      return;
    }
    location = new MutableLiveData<>();
    Optional<String> lastUserLocation = PreferenceHandler.getStringPreference(context, PreferenceKey.LAST_USER_LOCATION);
    location.setValue(new LocationModel(lastUserLocation.orElse("")));
  }

  public void loadCurrentLocationData(Context context) {
    // TODO Fetch location from GPS
    String currentLocation = "Krk";

    // Fetch data for current location from REST API
    RetrofitHandler.getInstance().create(LocationDataRestApi.class).getLocationData().enqueue(new Callback<Sight[]>() {
      @Override
      @EverythingIsNonNull
      public void onResponse(Call<Sight[]> call, Response<Sight[]> response) {
        // Store fetched data to database
        //noinspection ResultOfMethodCallIgnored
        Observable.just(DaoHandler.getInstance(context).sightDao())
          .subscribeOn(Schedulers.io())
          .subscribe(db -> {
            // Store images to local disk
            ResponseTransformer.transformImagePath(context, response.body());
            // Store fetched data to database
            db.insertAll(response.body());
            // Store new location in shared pref
            PreferenceHandler.setStringPreference(context, PreferenceKey.LAST_USER_LOCATION, currentLocation);
            // Refresh VM
            location.postValue(new LocationModel(currentLocation));
          }, e -> Log.e(LocationViewModel.class.getName(), e.getMessage()));
      }

      @Override
      @EverythingIsNonNull
      public void onFailure(Call<Sight[]> call, Throwable t) {
        Log.e(LocationViewModel.class.getName(), t.getMessage());
        Toast.makeText(context, REST_CALL_ERROR_MESSAGE, Toast.LENGTH_LONG).show();
      }
    });
  }
}
