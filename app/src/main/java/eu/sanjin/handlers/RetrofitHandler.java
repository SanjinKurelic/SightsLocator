package eu.sanjin.handlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Objects;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHandler {

  private static final String SERVER_URL = "https://sanjin.eu/github-projects/android/";
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
    Gson gson = new GsonBuilder()
      .setLenient()
      .create();

    instance = new Retrofit.Builder()
      .baseUrl(SERVER_URL)
      .addConverterFactory(GsonConverterFactory.create(gson))
      .build();
  }
}
