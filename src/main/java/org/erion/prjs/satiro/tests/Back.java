/*
 * Back.java
 *
 * Created on 19 novembre 2005, 15.25
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.erion.prjs.satiro.tests;

import org.erion.prjs.satiro.core.AnimatedSprite;
import org.erion.prjs.satiro.core.Frame;

/**
 * @author alexian
 */
public class Back extends AnimatedSprite {

  /**
   * Creates a new instance of Back
   */
  public Back() {
    super("back", false);
    this.addAnimation(AnimatedSprite.WALKING,
      new Frame[]{
        new Frame("/home/alexian/dev/java/source/sprites/back.jpg")
      },
      1000
    );

    this.getProperties().setVisualizationPriority(1);
  }

  public void behaviour() {

  }

  public void startSprite() {

  }

  public void stopSprite() {

  }

  public void pauseSprite() {

  }
}
