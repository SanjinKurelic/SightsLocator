package eu.sanjin.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sight {

  @PrimaryKey(autoGenerate = true)
  private Long id;
  private String title;
  private String description;
  private String imageUrl;

}
