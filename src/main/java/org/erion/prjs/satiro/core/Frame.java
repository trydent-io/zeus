package org.erion.prjs.satiro.core;

import org.erion.prjs.satiro.utils.SatiroTools;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;

public class Frame {
  private BufferedImage image;
  private long duration;
  private Point keySpot;
  private ArrayList<Point> actionSpots;
  private boolean isReady = false;

  public Frame() {
    // do nothing
  }

  public Frame(String path) {
    try {
      this.setImage(
        SatiroTools.toBufferedImage(new ImageIcon(new URL(path)).getImage())
      );
    } catch (java.net.MalformedURLException mue) {
      System.err.println("[satiro.core.Frame] Exception: " + mue);
    }
    this.isReady = true;
  }

  public Frame(BufferedImage image) {
    this.image = image;
    this.keySpot = new Point(0, 0);
  }

  public Frame(BufferedImage image, long duration) {
    this.image = image;
    this.duration = duration;
    this.keySpot = new Point(0, 0);
  }

  public Frame(BufferedImage image, int width, int height, long duration) {
    this.image = image;
    this.duration = duration;
    this.keySpot = new Point(0, 0);
  }

  public void setImage(BufferedImage image) {
    this.image = image;
  }

  public BufferedImage getImage() {
    return this.image;
  }

  public void setDuration(long duration) {
    this.duration = duration;
  }

  public long getDuration() {
    return this.duration;
  }

  public int getWidth() {
    return this.image.getWidth(null);
  }

  public int getHeight() {
    return this.image.getHeight(null);
  }

  public boolean isReady() {
    return this.isReady;
  }
}