package eu.sanjin.handlers;

public enum PreferenceKey {

  LAST_USER_LOCATION("last_user_location");

  private final String key;

  PreferenceKey(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }
}
