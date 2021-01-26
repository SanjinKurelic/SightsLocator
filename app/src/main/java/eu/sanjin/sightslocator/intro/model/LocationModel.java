package eu.sanjin.sightslocator.intro.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class LocationModel {

  @NonNull
  private String currentLocation;

}
