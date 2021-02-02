package eu.sanjin.sightslocator;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import java.util.Objects;

import eu.sanjin.sightslocator.databinding.ActivityMainViewBinding;

public class MainViewActivity extends AppCompatActivity {

  private ActivityMainViewBinding binding;
  private NavHostFragment fragmentContainer;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainViewBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    Fragment fragment = getSupportFragmentManager().findFragmentById(binding.fragmentContainer.getId());
    if (!Objects.isNull(fragment) && fragment instanceof NavHostFragment) {
      this.fragmentContainer = (NavHostFragment) fragment;
      NavigationUI.setupWithNavController(binding.bottomNavigation, fragmentContainer.getNavController());
    }
    // Custom action for exiting application
    binding.bottomNavigation.getMenu().findItem(R.id.exit).setOnMenuItemClickListener(item -> exitApplication());
    // Show list
    showList(this.getCurrentFocus());
  }

  public void showList(View v) {
    binding.bottomNavigation.setSelectedItemId(R.id.list);
  }

  public void showInfo() {
    Navigation.findNavController(fragmentContainer.requireView()).navigate(R.id.sight);
  }

  public boolean exitApplication() {
    new AlertDialog.Builder(this)
      .setIcon(R.drawable.ic_exit)
      .setTitle("Exit application")
      .setPositiveButton("Ok", (d, i) -> finishAndRemoveTask())
      .setNegativeButton("Cancel", null)
      .create()
      .show();

    return true;
  }
}
