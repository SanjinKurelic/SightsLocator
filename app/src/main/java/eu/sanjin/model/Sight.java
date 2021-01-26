package eu.sanjin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sight {

  private Long id;
  private String title;
  private String description;
  private String imageUrl;

}
