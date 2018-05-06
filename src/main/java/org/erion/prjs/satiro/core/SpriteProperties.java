package org.erion.prjs.satiro.core;

import java.awt.*;
import java.io.Serializable;

public class SpriteProperties implements Serializable {
  private static final long serialVersionUID = 03l;

  private String idName;
  private String currentAnimation;

  private Point framePosition;
  private Point spritePosition;
  private Frame currentFrame;

  private Point collisionSpot;
  private Rectangle collisionArea;

  private boolean isActive;

  private int currentFrameIndex;
  private int visualizationPriority;

  public SpriteProperties() {
    this.spritePosition = new Point();
    this.framePosition = new Point();
    this.isActive = false;
    this.collisionSpot = new Point(0, 0);
    this.collisionArea = new Rectangle(this.collisionSpot, new Dimension(0, 0));
  }

  public void active() {
    this.isActive = true;
  }

  public void deactive() {
    this.isActive = false;
  }

  public void setIdName(String idName) {
    this.idName = idName;
  }

  public String getIdName() {
    return this.idName;
  }

  public void setCurrentFrameIndex(int index) {
    this.currentFrameIndex = index;
  }

  public int getCurrentFrameIndex() {
    return this.currentFrameIndex;
  }

  public void setCurrentFrame(Frame frame) {
    this.currentFrame = null;
    this.currentFrame = frame;
    this.collisionArea.setSize(frame.getWidth(), frame.getHeight());
  }

  public Frame getCurrentFrame() {
    return this.currentFrame;
  }

  public void setPosition(int x, int y) {
    this.spritePosition.setLocation(x, y);
    this.collisionSpot.setLocation(new Point(x, y));
    this.collisionArea.setLocation(this.collisionSpot);
  }

  public Point getPosition() {
    return this.spritePosition;
  }

  public Point getFramePosition() {
    return this.framePosition;
  }

  public void setVisualizationPriority(int priority) {
    this.visualizationPriority = priority;
  }

  public int getVisualizationPriority() {
    return this.visualizationPriority;
  }

  public boolean isActive() {
    return this.isActive;
  }

  public void setCollisionSpot(Point spot) {
    this.collisionSpot = null;
    this.collisionSpot = spot;
    this.collisionArea.setLocation(spot);
  }

  public Point getCollisionSpot() {
    return this.collisionSpot;
  }

  public void setCollisionArea(Dimension area) {
    this.collisionArea = null;
    this.collisionArea = new Rectangle(
      (int) this.collisionSpot.getX(),
      (int) this.collisionSpot.getY(),
      (int) area.getHeight(),
      (int) area.getWidth());
  }

  public Rectangle getCollisionArea() {
    return this.collisionArea;
  }

  public void setCurrentAnimation(String animation) {
    this.currentAnimation = animation;
  }

  public String getCurrentAnimation() {
    return this.currentAnimation;
  }
}
