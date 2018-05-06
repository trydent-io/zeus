/*
 * AnimatedSprite.java
 *
 * Created on November 10, 2005, 4:14 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package org.erion.prjs.satiro.core;

import java.awt.*;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * @author luca.guadagnini.1
 */
public abstract class AnimatedSprite implements DefaultAnimations, Runnable {
  private SpriteProperties prop;
  private Rectangle collisionArea;

  private Hashtable<String, Animation> animations;
  private Animation currentAnimation = null;

  private long initialTime;
  private boolean visible;

  private float transparency;

  public AnimatedSprite(String idName, boolean visible) {
    this.prop = new SpriteProperties();
    this.prop.setIdName(idName);
    System.out.println("[satiro.core.AnimatedSprite - " + System.currentTimeMillis() + "] Info: sprite " + idName + " created!");
    this.transparency = 0;
    this.visible = visible;
  }

  public synchronized final boolean isReady() {
    Enumeration e = this.animations.elements();
    while (e.hasMoreElements()) {
      while (!((Animation) e.nextElement()).isReady()) ;
    }

    return true;
  }

  protected synchronized final void addAnimation(String name, Frame[] frames, long velocity) {
    if (this.animations == null) this.animations = new Hashtable<String, Animation>();
    String safeName = "animation" + (this.animations.size() + 1);

    Animation anim = new Animation(frames, velocity);
    System.out.println("[satiro.core.AnimatedSprite - " + System.currentTimeMillis() + "] Info: animation " + name + " created!");

    if (name != null) {
      if (this.animations.containsKey(name)) {
        System.err.println("[satiro.core.AnimatedSprite - " + System.currentTimeMillis() + "] Warning: " + name + ", this name already exists for the animations!");
        anim.setName(safeName);
        this.animations.put(safeName, anim);
      } else {
        anim.setName(name);
        this.animations.put(name, anim);
        System.err.println("[satiro.core.AnimatedSprite - " + System.currentTimeMillis() + "] Info: animation " + name + " is added! Size: " + this.animations.size());
      }
    } else {
      System.err.println("[satiro.core.AnimatedSprite - " + System.currentTimeMillis() + "] Warning: the name of animation is null!");
      anim.setName(safeName);
      this.animations.put(safeName, anim);
    }
  }

  protected synchronized final void addAnimation(String name, Collection<Frame> frames, long velocity) {
    this.addAnimation(name, (Frame[]) frames.toArray(), velocity);
  }

  protected synchronized final void addAnimation(String name, Frame[] frames) {
    this.addAnimation(name, frames, Animation.DEFAULT_VELOCITY);
  }

  protected synchronized final void addAnimation(String name, Collection<Frame> frames) {
    this.addAnimation(name, (Frame[]) frames.toArray(), Animation.DEFAULT_VELOCITY);
  }

  protected synchronized final Animation getAnimation(String name) {
    Animation anim = this.animations.get(name);
    if (anim == null)
      System.err.println("[satiro.core.AnimatedSprite - " + System.currentTimeMillis() + "] Error: animation " + name + " do not exists!");
    return anim;
  }

  public synchronized void updateAnimation() {
    if (this.currentAnimation != null) {
      if (System.currentTimeMillis() - this.initialTime > this.prop.getCurrentFrame().getDuration()) {
        int index = this.prop.getCurrentFrameIndex() + 1;

        if (index == this.currentAnimation.getFrames().size()) index = 0;

        this.prop.setCurrentFrameIndex(index);
        this.prop.setCurrentFrame(this.currentAnimation.getFrame(index));
        this.initialTime = System.currentTimeMillis();
      }
    } else {
      System.err.println("[Satiro GameCore - 00.00.00] SpriteError: animation not selected!");
    }
  }

  public boolean isVisible() {
    return this.visible;
  }

  public synchronized final void setAnimation(String animation) {
    this.initialTime = System.currentTimeMillis();
    if (this.animations != null && this.animations.size() > 0) {
      Animation anim = this.animations.get(animation);
      if (this.currentAnimation == null ||
        (this.currentAnimation != null && !this.currentAnimation.equals(anim))) {

        this.currentAnimation = this.animations.get(animation);
        this.prop.setCurrentFrameIndex(0);
        this.prop.setCurrentFrame(this.currentAnimation.getFrame(0));
        if (this.currentAnimation == null) {
          this.currentAnimation = ((Animation[]) this.animations.values().toArray())[0];
          this.prop.setCurrentAnimation(this.currentAnimation.getName());
          System.err.println("[satiro.core.AnimatedSprite - " + System.currentTimeMillis() + "] Error: animation " + animation + " does not exists! Size: " + this.animations.size());
        } else {
          this.prop.setCurrentAnimation(animation);
          System.out.println("[satiro.core.AnimatedSprite - " + System.currentTimeMillis() + "] Info: animation " + animation + " selected.");
        }
      }
    }
  }

  public synchronized final Animation getCurrentAnimation() {
    return this.currentAnimation;
  }

  public synchronized final SpriteProperties getProperties() {
    return this.prop;
  }

  public void setCollisionSpot(int x, int y) {
    this.prop.setCollisionSpot(new Point(x, y));
  }

  public Point getCollisionSpot() {
    return this.prop.getCollisionSpot();
  }

  public void setPosition(int x, int y) {
    this.setCollisionArea(
      x, y,
      this.prop.getCurrentFrame().getImage().getWidth(),
      this.prop.getCurrentFrame().getImage().getHeight());
    this.prop.setPosition(x, y);
  }

  public void setTransparency(float value) {
    this.transparency = value;
  }

  public float getTransparency() {
    return this.transparency;
  }

  public Rectangle getCollisionArea() {
    return this.collisionArea;
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

  public boolean collidesWith(Object object) {
    if (object instanceof AnimatedSprite) {
      AnimatedSprite s = (AnimatedSprite) object;

      if (this.collisionArea.intersects(s.getCollisionArea())) return true;
    }

    if (object instanceof Background) {
      Background b = (Background) object;
      return b.collidesWith(this);
    }

    return false;
  }

  public void run() {
    // by now do nothing
  }

  public abstract void startSprite(); // start the sprite

  public abstract void stopSprite(); // stop the sprite

  public abstract void pauseSprite(); // pause the actual animation

  protected abstract void behaviour();
}