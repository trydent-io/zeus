package org.erion.prjs.satiro.utils;

import imgs.Imgs;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public interface SatiroTools {
  int X_FAILED = Integer.MIN_VALUE;

  static BufferedImage toBufferedImage(Image image) {
    BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
    bi.getGraphics().drawImage(image, 0, 0, null);
    bi.getGraphics().dispose();

    return bi;
  }

  static Image loadImage(String path) {
    return new ImageIcon(path).getImage();
  }

  static BufferedImage loadBufferedImage(String path) {
    return toBufferedImage(new ImageIcon(path).getImage());
  }

  static String getJarAddress() {
    return Imgs.class.getResource("").toString();
  }

  static int toSatiroXCordinate(int x) {
    return X_FAILED;
  }

  static int toSatiroYCordinate(int y) {
    return y;
  }
}