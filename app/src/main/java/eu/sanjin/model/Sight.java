package eu.sanjin.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Sight {

  @PrimaryKey
  private Long id;
  private String title;
  private String description;
  private String imageUrl;
  @Embedded
  SightLocation location;

}
