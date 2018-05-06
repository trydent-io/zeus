/*
 * CollisionArea.java
 *
 * Created on 29 novembre 2005, 16.24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.erion.prjs.satiro.core;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.Vector;

/**
 * @author alexian
 */

class SingleLine extends Line2D {
  public SingleLine(Point p1, Point p2) {
    this.setLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
  }

  public void setLine(double x1, double y1, double x2, double y2) {
    this.setLine(
      new Point((int) x1, (int) y1),
      new Point((int) x2, (int) y2));
  }

  public Point getP1() {
    return new Point(
      (int) this.getBounds().getX(),
      (int) this.getBounds().getY());
  }

  public Point getP2() {
    return new Point(
      (int) this.getBounds().getWidth(),
      (int) this.getBounds().getHeight());
  }

  public double getX1() {
    return this.getBounds().getX();
  }

  public double getY1() {
    return this.getBounds().getY();
  }

  public double getX2() {
    return this.getBounds().getWidth();
  }

  public double getY2() {
    return this.getBounds().getHeight();
  }

  public Rectangle2D getBounds2D() {
    return new Rectangle2D.Double(
      this.getBounds().getX(),
      this.getBounds().getY(),
      this.getBounds().getWidth(),
      this.getBounds().getHeight());
  }
}

public class CollisionArea {

  public static final int AREA = 0;
  public static final int LINE = 1;

  private Collection<SingleLine> borderLines;
  private Collection<Rectangle> zones;

  /**
   * Creates a new instance of CollisionArea
   */
  public CollisionArea(Point p1, Point p2, int collisionAreaType) {
    if (collisionAreaType == LINE && this.borderLines == null)
      this.borderLines = new Vector<SingleLine>();

    if (collisionAreaType == AREA && this.zones == null)
      this.zones = new Vector<Rectangle>();

    this.addCollisionArea(p1, p2, collisionAreaType);
  }

  public void addCollisionArea(Point p1, Point p2, int collisionAreaType) {
    if (collisionAreaType == LINE && this.borderLines == null)
      this.borderLines = new Vector<SingleLine>();

    if (collisionAreaType == AREA && this.zones == null)
      this.zones = new Vector<Rectangle>();

    switch (collisionAreaType) {
      case AREA:
        this.zones.add(new Rectangle(p1, new Dimension((int) p2.getX(), (int) p2.getY())));
        break;
      case LINE:
        this.borderLines.add(new SingleLine(p1, p2));
        break;
      default:
        System.err.println("[satiro.core.CollisionArea] Error: type of collision area " + collisionAreaType + " not exists!");
        break;
    }
  }

  public Collection<SingleLine> getSingleLines() {
    return this.borderLines;
  }

  public Collection<Rectangle> getRectangles() {
    return this.zones;
  }

  public boolean touch(CollisionArea ca) {
    Collection<SingleLine> cs = ca.getSingleLines();
    Collection<Rectangle> cr = ca.getRectangles();

    if (cs != null && cr != null) {
      for (SingleLine sl1 : this.borderLines) {
        for (SingleLine sl2 : cs) {
          if (sl2.intersectsLine(sl1)) return true;
        }

        //for
      }
    }

    return true;
  }
}
