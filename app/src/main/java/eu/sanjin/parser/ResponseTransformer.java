package eu.sanjin.parser;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.Arrays;

import eu.sanjin.handlers.FileHandler;
import eu.sanjin.model.Sight;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class ResponseTransformer {

  public static final int IMAGE_MARGIN = 5;
  public static final int IMAGE_RADIUS = 2;
  private static final int IMAGE_WIDTH = 750;
  private static final int IMAGE_HEIGHT = 422;

  public static void transformImagePath(Context context, Sight[] sights) {
    Arrays.stream(sights).forEach(sight -> {
      String url = sight.getImageUrl();
      if (!url.isEmpty()) {
        try {
          Bitmap bitmap = Picasso.get()
            .load(Uri.parse(url))
            .transform(new RoundedCornersTransformation(IMAGE_RADIUS, IMAGE_MARGIN))
            .resize(IMAGE_WIDTH, IMAGE_HEIGHT)
            .onlyScaleDown()
            .get();
          String fileName = url.substring(url.lastIndexOf('/') + 1, url.lastIndexOf('.'));
          sight.setImageUrl(FileHandler.saveBitmap(context, fileName, bitmap).orElse(""));
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
  }
}
