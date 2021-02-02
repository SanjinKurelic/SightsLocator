package eu.sanjin.handlers;

import lombok.Getter;

public enum PreferenceKey {

  LAST_USER_LOCATION("last_user_location"),
  SIGHT_ITEM_POSITION("sight_item_position");

  @Getter
  private final String key;

  PreferenceKey(String key) {
    this.key = key;
  }
}
