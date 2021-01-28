package eu.sanjin.handlers;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Optional;

public class FileHandler {

  private static final String PREFIX = String.valueOf("Sights Locator".hashCode());
  private static final String IMAGE_EXTENSION = "jpg";
  private static final int IMAGE_QUALITY = 100;

  public static Optional<String> saveBitmap(Context context, String fileName, Bitmap bitmap) {
    File directory = context.getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
    File file = new File(directory, String.format("%s%s%s.%s", File.separator, PREFIX, fileName, IMAGE_EXTENSION));

    if (file.exists() && !file.delete()) {
      Log.e(FileHandler.class.getName(), String.format("Could not delete file: %s", file.getAbsolutePath()));
      return Optional.empty();
    }

    try (FileOutputStream fos = new FileOutputStream(file); ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
      bitmap.compress(Bitmap.CompressFormat.JPEG, IMAGE_QUALITY, bos);
      fos.write(bos.toByteArray());
    } catch (Exception e) {
      Log.e(FileHandler.class.getName(), e.getMessage());
      return Optional.empty();
    }

    return Optional.of(file.getAbsolutePath());
  }
}
