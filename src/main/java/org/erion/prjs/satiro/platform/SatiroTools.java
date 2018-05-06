package org.erion.prjs.satiro.platform;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SatiroTools {
  public static final int X_FAILED = Integer.MIN_VALUE;

  public static BufferedImage toBufferedImage(Image image) {
    BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
    bi.getGraphics().drawImage(image, 0, 0, null);
    bi.getGraphics().dispose();

    return bi;
  }

  public static Image loadImage(String path) {
    return new ImageIcon(path).getImage();
  }

  public static BufferedImage loadBufferedImage(String path) {
    return toBufferedImage(new ImageIcon(path).getImage());
  }

  public static int toSatiroXCordinate(int x) {
    return X_FAILED;
  }

  public static int toSatiroYCordinate(int y) {
    return y;
  }
}