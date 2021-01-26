package eu.sanjin.handlers;

import android.content.Context;

import androidx.preference.PreferenceManager;

import java.util.Objects;
import java.util.Optional;

public class PreferenceHandler {

  private static final String PREFERENCE_ROOT = Objects.requireNonNull(PreferenceManager.class.getPackage()).getName();

  public static Optional<String> getStringPreference(Context context, PreferenceKey preferenceKey) {
    String key = PREFERENCE_ROOT + preferenceKey.getKey();
    return Optional.ofNullable(PreferenceManager.getDefaultSharedPreferences(context).getString(key, null));
  }

  public static void setStringPreference(Context context, PreferenceKey preferenceKey, String value) {
    String key = PREFERENCE_ROOT + preferenceKey.getKey();
    PreferenceManager.getDefaultSharedPreferences(context).edit().putString(key, value).apply();
  }
}
