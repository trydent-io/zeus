package org.erion.prjs.satiro.platform;

import java.awt.*;
import java.io.Serializable;

public class SatiroGameProperties implements Serializable {
  private static final long serialVersionUID = 01l;

  private String title;
  private String version;
  private String author;

  private Image icon;
  private Image logo;
  private Image splash;

  private Dimension resolution;
  private int refresh;
  private int colorDepth;

  public SatiroGameProperties() {
    System.out.println("Creiamo tutti insieme un nuovo giochino!");
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTitle() {
    return this.title;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getVersion() {
    return this.version;
  }

  public void setColorDepth(int colorDepth) {
    this.colorDepth = colorDepth;
  }

  public int getColorDepth() {
    return this.colorDepth;
  }

  public void setRefreshRate(int refresh) {
    this.refresh = refresh;
  }

  public int getRefreshRate() {
    return this.refresh;
  }

  public void setResolution(int width, int height) {
    this.resolution = new Dimension(width, height);
  }

  public Dimension getResolution() {
    return this.resolution;
  }

  public Object getProperties() {
    return null;
  }
}