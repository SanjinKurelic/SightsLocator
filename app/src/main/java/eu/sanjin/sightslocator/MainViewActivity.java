package eu.sanjin.sightslocator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import eu.sanjin.sightslocator.databinding.ActivityMainViewBinding;

public class MainViewActivity extends AppCompatActivity {

  private ActivityMainViewBinding binding;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainViewBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    binding.bottomNavigation.setOnNavigationItemSelectedListener(this::navigationItemSelected);
  }

  @SuppressLint("NonConstantResourceId")
  private boolean navigationItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.exit:
        exitApplication();
        break;
      case R.id.sight:
        break;
      case R.id.list:
        showList(item.getActionView());
        break;
      case R.id.maps:
        break;
      case R.id.about:
        break;
    }
    Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
    return true;
  }

  public void exitApplication() {
    new AlertDialog.Builder(this)
      .setIcon(R.drawable.ic_exit)
      .setTitle("Exit application")
      .setPositiveButton("Ok", (d, i) -> finishAndRemoveTask())
      .setNegativeButton("Cancel", null)
      .create()
      .show();
  }

  public void showList(View v) {
    Toast.makeText(this, "List", Toast.LENGTH_SHORT).show();
  }
}
