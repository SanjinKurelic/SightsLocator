package eu.sanjin.handlers;

import lombok.Getter;

public enum PreferenceKey {

  LAST_USER_LOCATION("last_user_location");

  @Getter
  private final String key;

  PreferenceKey(String key) {
    this.key = key;
  }
}
