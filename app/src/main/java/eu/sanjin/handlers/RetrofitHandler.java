package eu.sanjin.handlers;

import java.util.Objects;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHandler {

  private static final String SERVER_URL = "";
  private static Retrofit instance;

  private RetrofitHandler() {
  }

  public static Retrofit getInstance() {
    if (Objects.isNull(instance)) {
      init();
    }
    return instance;
  }

  private static void init() {
    instance = new Retrofit.Builder()
      .baseUrl(SERVER_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build();
  }
}
