package eu.sanjin.service;

import eu.sanjin.model.Sight;
import retrofit2.Call;
import retrofit2.http.GET;

public interface LocationDataRestApi {

  @GET("locationData")
  Call<Sight[]> getLocationData();

}
