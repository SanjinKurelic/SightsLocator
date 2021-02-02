package eu.sanjin.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Ignore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor(onConstructor = @__(@Ignore))
@NoArgsConstructor
public class Sight {

  @PrimaryKey(autoGenerate = true)
  private Long id;
  private String title;
  private String description;
  private String imageUrl;

}
