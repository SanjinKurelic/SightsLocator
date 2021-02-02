package eu.sanjin.handlers;

import android.content.Context;

import androidx.preference.PreferenceManager;

import java.util.Objects;
import java.util.Optional;

public class PreferenceHandler {

  private static final String PREFERENCE_ROOT = Objects.requireNonNull(PreferenceManager.class.getPackage()).getName();

  public static Optional<String> getStringPreference(Context context, PreferenceKey preferenceKey) {
    return Optional.ofNullable(
      PreferenceManager.getDefaultSharedPreferences(context).getString(getStringKey(preferenceKey), null)
    );
  }

  public static void setStringPreference(Context context, PreferenceKey preferenceKey, String value) {
    PreferenceManager.getDefaultSharedPreferences(context).edit().putString(getStringKey(preferenceKey), value).apply();
  }

  public static int getIntPreference(Context context, PreferenceKey preferenceKey) {
    return PreferenceManager.getDefaultSharedPreferences(context).getInt(getStringKey(preferenceKey), 0);
  }

  public static void setIntPreference(Context context, PreferenceKey preferenceKey, int value) {
    PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(getStringKey(preferenceKey), value).apply();
  }

  private static String getStringKey(PreferenceKey preferenceKey) {
    return PREFERENCE_ROOT + preferenceKey.getKey();
  }
}
