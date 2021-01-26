package eu.sanjin.sightslocator.intro.service;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LocationDataApi {

  @GET("locationData")
  Call<Object> getLocationData();

}
