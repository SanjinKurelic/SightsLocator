package eu.sanjin.sightslocator.intro.service;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import eu.sanjin.handlers.PreferenceHandler;
import eu.sanjin.handlers.PreferenceKey;

public class LocationDataService extends JobIntentService {


  @Override
  protected void onHandleWork(@NonNull Intent intent) {
    // fetch near city name
    String cityName = "Krk";
    // fetch data

    // store in db

    // save in preferences
    PreferenceHandler.setStringPreference(this, PreferenceKey.LAST_USER_LOCATION, cityName);
  }
}
