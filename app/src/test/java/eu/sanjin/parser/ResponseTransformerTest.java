package eu.sanjin.parser;

import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Optional;

import eu.sanjin.handlers.FileHandler;
import eu.sanjin.model.Sight;

public class ResponseTransformerTest {

  @Test
  public void testTransformImagePath() {
    Sight sight = new Sight();
    sight.setImageUrl("test");
    Sight[] sights = new Sight[0];
    try (MockedStatic<FileHandler> ignored = Mockito.mockStatic(FileHandler.class)) {
      //noinspection ConstantConditions
      Mockito.when(FileHandler.saveBitmap(null, "name", null)).thenReturn(Optional.of("OK"));
      ResponseTransformer.transformImagePath(null, sights);
    }
  }
}