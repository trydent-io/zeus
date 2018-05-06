/*
 * Zeus.java
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
public class Zeus extends AnimatedSprite {

  /**
   * Creates a new instance of Zeus
   */
  public Zeus() {
    super("Zeus", true);

    this.addAnimation(AnimatedSprite.STANDING,
      new Frame[]{
        new Frame(SatiroTools.getJarAddress() + "sprites/zeus/zeus_sleep01.gif"),
        new Frame(SatiroTools.getJarAddress() + "sprites/zeus/zeus_sleep02.gif")
      },
      800
    );

    this.addAnimation(AnimatedSprite.WALKING,
      new Frame[]{
        new Frame(SatiroTools.getJarAddress() + "sprites/zeus/zeus_walk01.gif"),
        new Frame(SatiroTools.getJarAddress() + "sprites/zeus/zeus_walk02.gif")
      },
      400
    );

    this.addAnimation("de" + AnimatedSprite.WALKING,
      new Frame[]{
        new Frame(SatiroTools.getJarAddress() + "sprites/zeus/zeus_dewalk01.gif"),
        new Frame(SatiroTools.getJarAddress() + "sprites/zeus/zeus_dewalk02.gif")
      },
      400
    );

    this.addAnimation("up" + AnimatedSprite.WALKING,
      new Frame[]{
        new Frame(SatiroTools.getJarAddress() + "sprites/zeus/zeus_upwalk01.gif"),
        new Frame(SatiroTools.getJarAddress() + "sprites/zeus/zeus_upwalk02.gif")
      },
      400
    );

    this.addAnimation("dw" + AnimatedSprite.WALKING,
      new Frame[]{
        new Frame(SatiroTools.getJarAddress() + "sprites/zeus/zeus_dwwalk01.gif"),
        new Frame(SatiroTools.getJarAddress() + "sprites/zeus/zeus_dwwalk02.gif")
      },
      400
    );

    this.getProperties().setVisualizationPriority(2);
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