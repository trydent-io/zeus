/*
 * ALevel.java
 *
 * Created on 18 novembre 2005, 17.16
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.erion.prjs.satiro.tests;

import org.erion.prjs.satiro.core.AnimatedSprite;
import org.erion.prjs.satiro.platform.SatiroLevel;

/**
 * @author alexian
 */
public class ALevel extends SatiroLevel {

  /**
   * Creates a new instance of ALevel
   */
  public ALevel() {
    super("ALevel", "password", "", 1024, 768);
    Zeus as = new Zeus();
    //Background b = new Background("Back", new javax.swing.ImageIcon("/home/alexian/dev/images/back01.jpg").getImage(), 0, 0);
    as.getProperties().active();
    this.addSprite(as);
    this.getSprite(as.getProperties().getIdName()).setAnimation(AnimatedSprite.WALKING);
  }

  public void behaviour() {

  }

  public void startLevel() {

  }

  public void stopLevel() {

  }
}
