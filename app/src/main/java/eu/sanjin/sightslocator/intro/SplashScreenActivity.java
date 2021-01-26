package eu.sanjin.sightslocator.intro;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import eu.sanjin.sightslocator.MainViewActivity;
import eu.sanjin.sightslocator.databinding.ActivitySplashScreenBinding;
import eu.sanjin.sightslocator.intro.model.LocationModel;
import eu.sanjin.sightslocator.intro.viewmodel.LocationViewModel;

public class SplashScreenActivity extends AppCompatActivity {

  private LocationViewModel locationViewModel;
  private ActivitySplashScreenBinding binding;

  // Delay app start if everything is already loaded
  private static final int DELAY = 3000;
  private final Handler handler = new Handler(Looper.getMainLooper());
  private Runnable fetch;

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
    refreshUi(locationViewModel.getLocation().getValue());
  }

  @Override
  public void finish() {
    super.finish();
    handler.removeCallbacks(fetch);
  }

  private void refreshUi(LocationModel model) {
    if (!model.getCurrentLocation().isEmpty()) {
      fetch = () -> startActivity(new Intent(this, MainViewActivity.class));

      handler.postDelayed(fetch, DELAY);
    } else {
      locationViewModel.loadCurrentLocationData(this);
    }
  }

  private void startAnimations() {
    //AnimationUtils.loadAnimation(this, RotateBin);
    //binding.splashScreenLoadingIcon.startAnimation();
    ObjectAnimator rotate = ObjectAnimator.ofFloat(binding.splashScreenLoadingIcon, View.ROTATION, 0f, 360f);
    rotate.setDuration(300);
    rotate.setRepeatCount(Animation.INFINITE);
    rotate.start();
  }
}