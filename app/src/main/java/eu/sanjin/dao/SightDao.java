package eu.sanjin.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import eu.sanjin.model.Sight;

@Dao
public interface SightDao {

  @Query("SELECT * FROM sight")
  List<Sight> getAll();

  @Insert
  void insertAll(Sight... sights);

  @Delete
  void delete(Sight sight);

}
