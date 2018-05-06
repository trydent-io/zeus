package org.erion.prjs.satiro.core;

import org.erion.prjs.satiro.utils.SatiroTools;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.net.URL;
import java.util.Collection;

public class StaticSprite implements Serializable {
  private static final long serialVersionUID = 8L;

  private Rectangle collisionArea;
  private BufferedImage image;
  private String idName;
  private boolean visible;
  private int visualizationPriority;
  private int x;
  private int y;
  private Point keySpot;
  private Point collisionSpot;
  private Collection<Point> activeSpots;

  private boolean isReady = false;

  public StaticSprite(String idName, BufferedImage image, int x, int y, boolean visible) {
    this.idName = idName;
    this.image = image;
    this.x = x;
    this.y = y;
    this.visible = visible;
  }

  public StaticSprite(String idName, Image image, int x, int y, boolean visible) {
    this.idName = idName;
    this.image = SatiroTools.toBufferedImage(image);
    this.x = x;
    this.y = y;
    this.visible = visible;
  }

  public StaticSprite(String idName, String path, int x, int y, boolean visible) {
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

  public void setCollisionSpot(int x, int y) {
    if (this.collisionSpot == null)
      this.collisionSpot = new Point(x, y);
    else
      this.collisionSpot.setLocation(x, y);
  }

  public void setCollisionArea(int x, int y, int width, int height) {
    this.setCollisionSpot(x, y);

    if ((int) this.collisionArea.getWidth() != width ||
      (int) this.collisionArea.getHeight() != height) {

      this.collisionArea = null;
      this.collisionArea = new Rectangle(x, y, width, height);
    } else {
      this.collisionArea.setLocation(x, y);
    }
  }

  public Rectangle getCollisionArea() {
    return this.collisionArea;
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

			return this.collisionArea.intersects(s.getCollisionArea());
    }

    return false;
  }
}
