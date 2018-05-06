package org.erion.prjs.satiro.core;

import org.erion.prjs.satiro.utils.SatiroTools;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.net.URL;
import java.util.Collection;
import java.util.Vector;

public class Background implements Serializable {
  private static final long serialVersionUID = 04L;

  private Collection<Line2D> borders;
  private BufferedImage image;
  private String idName;
  private boolean visible;
  private int visualizationPriority;
  private int x;
  private int y;

  private boolean isReady = false;

  public Background(String idName, BufferedImage image, int x, int y, boolean visible) {
    this.idName = idName;
    this.image = image;
    this.x = x;
    this.y = y;
    this.visible = visible;
  }

  public Background(String idName, Image image, int x, int y, boolean visible) {
    this.idName = idName;
    this.image = SatiroTools.toBufferedImage(image);
    this.x = x;
    this.y = y;
    this.visible = visible;
  }

  public Background(String idName, String path, int x, int y, boolean visible) {
    this.idName = idName;
    try {
      this.image = SatiroTools.toBufferedImage(new ImageIcon(new URL(path)).getImage());
    } catch (java.net.MalformedURLException mue) {

    }
    this.isReady = true;
    this.x = x;
    this.y = y;
    this.visible = visible;
  }

  public boolean isReady() {
    return this.isReady;
  }

  public void setIdName(String idName) {
    this.idName = idName;
  }

  public String getIdName() {
    return this.idName;
  }

  public void setImage(BufferedImage image) {
    this.image = image;
  }

  public void setImage(Image image) {
    this.image = SatiroTools.toBufferedImage(image);
  }

  public BufferedImage getImage() {
    return this.image;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getX() {
    return this.x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getY() {
    return this.y;
  }

  public boolean isVisible() {
    return this.visible;
  }

  public void setVisualizationPriority(int visualizationPriority) {
    this.visualizationPriority = visualizationPriority;
  }

  public int getVisualizationPriority() {
    return this.visualizationPriority;
  }

  public Collection<Line2D> getBorders() {
    return this.borders;
  }

  public void addBorder(int x1, int y1, int x2, int y2) {
    if (this.borders == null) this.borders = new Vector<Line2D>();

    this.borders.add(new Line2D.Float(x1, y1, x2, y2));
  }

  /*
   * il metodo � temporaneo questo perch� tutti gli oggetti che dovranno rappresentare
   * la scena di gioco dovranno estendere la classe SatiroDrawnObject
   * e collidesWith sar� uno dei metodi che sar� uguale per tutte le classi
   * e su cui non sar� possibile effettuare l'overriding
   *
   * la nuova firma sar�:
   * public boolean collidesWith(SatiroDrawnObject swo) {
   *     if (swo instanceof AnimatedSprite) ...
   *     if (swo instanceof StaticSprite) ...
   *     if (swo instanceof IsometricBackground) ...
   *     if (swo instanceof ...) ...
   * }
   */

  public boolean collidesWith(Object object) {
    if (object instanceof AnimatedSprite) {
      AnimatedSprite s = (AnimatedSprite) object;

      for (Line2D line : this.borders) {
        if (line.intersects(s.getCollisionArea())) return true;
        if (s.getCollisionArea().intersectsLine(line)) return true;
      }
    }

    return false;
  }
}
