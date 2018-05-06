package org.erion.prjs.satiro.core;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

public class DrawnObjectProperties implements Serializable {
  private static final long serialVersionUID = 9L;

  private String idName;

  private Point position;
  private Dimension dimension;
  private BufferedImage image;

  private Point keySpot;
  private Point collisionSpot;
  private ArrayList<Point> activeSpots;

  public void setIdName(String idName) {
    this.idName = idName;
  }

  public String getIdName() {
    return this.idName;
  }


  public void setPosition(Point position) {
    if (this.position == null) this.position = new Point();

    this.position.setLocation(position);
  }

  public void setPosition(int x, int y) {
    if (this.position == null) this.position = new Point();

    this.position.setLocation(x, y);
  }

  public Point getPosition() {
    return this.position;
  }


  private void setDimension(Dimension dimension) {
    if (this.dimension == null) this.dimension = new Dimension();

    this.dimension.setSize(dimension);
  }

  public Dimension getDimension() {
    return this.dimension;
  }


  public void setImage(BufferedImage image) {
    this.image = image;

    this.setDimension(new Dimension(image.getWidth(null), image.getHeight(null)));
  }

  public BufferedImage getImage() {
    return this.image;
  }


  public void setKeySpot(Point keySpot) {
    if (this.keySpot == null) this.keySpot = new Point();

    this.keySpot.setLocation(keySpot);
  }

  public void setKeySpot(int x, int y) {
    if (this.keySpot == null) this.keySpot = new Point();

    this.keySpot.setLocation(x, y);
  }

  public Point getKeySpot() {
    return this.keySpot;
  }


  public void setCollisionSpot(Point collisionSpot) {
    if (this.collisionSpot == null) this.collisionSpot = new Point();

    this.collisionSpot.setLocation(collisionSpot);
  }

  public void setCollisionSpot(int x, int y) {
    if (this.collisionSpot == null) this.collisionSpot = new Point();

    this.collisionSpot.setLocation(x, y);
  }

  public Point getCollisionSpot() {
    return this.collisionSpot;
  }


  public void addActiveSpot(Point activeSpot) {
    if (this.activeSpots == null) this.activeSpots = new ArrayList<Point>();

    this.activeSpots.add(activeSpot);
  }

  public void addActiveSpot(int x, int y) {
    if (this.activeSpots == null) this.activeSpots = new ArrayList<Point>();

    this.activeSpots.add(new Point(x, y));
  }

  public boolean setActiveSpot(int id, Point activeSpot) {
    if (this.activeSpots == null) this.addActiveSpot(activeSpot);
    if (this.activeSpots.size() < id) {
      return false;
    } else {
      this.activeSpots.set(id, activeSpot);
    }

    return true;
  }

  public boolean setActiveSpot(int id, int x, int y) {
    if (this.activeSpots == null) this.addActiveSpot(x, y);
    if (this.activeSpots.size() < id) {
      return false;
    } else {
      this.activeSpots.set(id, new Point(x, y));
    }

    return true;
  }
}
