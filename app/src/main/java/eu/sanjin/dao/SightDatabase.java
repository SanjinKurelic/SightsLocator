package eu.sanjin.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import eu.sanjin.model.Sight;

@Database(entities = {Sight.class}, version = 2)
public abstract class SightDatabase extends RoomDatabase {

  public abstract SightDao sightDao();

}
