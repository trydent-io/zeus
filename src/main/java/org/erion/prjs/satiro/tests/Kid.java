/*
 * ASprite.java
 *
 * Created on 18 novembre 2005, 16.47
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.erion.prjs.satiro.tests;

import org.erion.prjs.satiro.core.AnimatedSprite;
import org.erion.prjs.satiro.core.Frame;
import org.erion.prjs.satiro.utils.SatiroTools;

/**
 * @author alexian
 */
public class Kid extends AnimatedSprite {

  /**
   * Creates a new instance of ASprite
   */
  public Kid() {
    super("Kid", false);

    this.addAnimation(AnimatedSprite.STANDING,
      new Frame[]{
        new Frame(SatiroTools.getJarAddress() + "sprites/kid/kid_stand01.gif"),
        new Frame(SatiroTools.getJarAddress() + "sprites/kid/kid_stand02.gif"),
        new Frame(SatiroTools.getJarAddress() + "sprites/kid/kid_stand03.gif"),
        new Frame(SatiroTools.getJarAddress() + "sprites/kid/kid_stand04.gif"),
        new Frame(SatiroTools.getJarAddress() + "sprites/kid/kid_stand05.gif"),
        new Frame(SatiroTools.getJarAddress() + "sprites/kid/kid_stand06.gif"),
        new Frame(SatiroTools.getJarAddress() + "sprites/kid/kid_stand07.gif"),
        new Frame(SatiroTools.getJarAddress() + "sprites/kid/kid_stand08.gif")
      },
      400
    );

    this.getProperties().setVisualizationPriority(3);
  }

  public void behaviour() {
    // nothing
  }

  public void startSprite() {
  }

  public void stopSprite() {

  }

  public void pauseSprite() {
    // nothing
  }
}