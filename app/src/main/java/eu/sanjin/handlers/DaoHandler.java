package eu.sanjin.handlers;

import android.content.Context;

import androidx.room.Room;

import java.util.Objects;

import eu.sanjin.dao.SightDatabase;

public class DaoHandler {

  private static SightDatabase sightDatabase;
  private static final String DB_NAME = "sights-locator-db";

  private DaoHandler() {
  }

  public static SightDatabase getInstance(Context context) {
    if (Objects.isNull(sightDatabase)) {
      init(context);
    }

    return sightDatabase;
  }

  private static void init(Context context) {
    sightDatabase = Room.databaseBuilder(context, SightDatabase.class, DB_NAME).build();
  }
}
