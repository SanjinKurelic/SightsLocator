package eu.sanjin.sightslocator.intro;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.Objects;

import eu.sanjin.sightslocator.MainViewActivity;
import eu.sanjin.sightslocator.R;
import eu.sanjin.sightslocator.databinding.ActivitySplashScreenBinding;
import eu.sanjin.sightslocator.intro.model.LocationModel;
import eu.sanjin.sightslocator.intro.viewmodel.LocationViewModel;

public class SplashScreenActivity extends AppCompatActivity {

  private LocationViewModel locationViewModel;
  private ActivitySplashScreenBinding binding;

  // Delay app start if everything is already loaded
  private static final int DELAY = 3000;
  private final Handler handler = new Handler(Looper.getMainLooper());
  private Runnable redirect;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    locationViewModel = new ViewModelProvider(this).get(LocationViewModel.class);
    locationViewModel.init(this);
    locationViewModel.getLocation().observe(this, this::refreshUi);
  }

  @Override
  protected void onResume() {
    super.onResume();
    startAnimations();
    refreshUi(Objects.requireNonNull(locationViewModel.getLocation().getValue()));
  }

  @Override
  public void finish() {
    super.finish();
    handler.removeCallbacks(redirect);
  }

  private void refreshUi(LocationModel model) {
    if (!model.getCurrentLocation().isEmpty()) {
      redirect = () -> {
        startActivity(new Intent(this, MainViewActivity.class));
        finish();
      };

      handler.postDelayed(redirect, DELAY);
    } else {
      locationViewModel.loadCurrentLocationData(this);
    }
  }

  private void startAnimations() {
    binding.animSplashScreenRipple.startRippleAnimation();

    Animation logoAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.logo_animation);
    binding.ivSplashScreenLogo.startAnimation(logoAnimation);
  }
}