/*
 * Behaviour.java
 *
 * Created on 23 novembre 2005, 19.16
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.erion.prjs.satiro.system;

import java.awt.event.KeyEvent;

/**
 * @author alexian
 */
public class Behaviour {
  private KeyEvent keyEvent;
  private boolean keyStatusChanged = false;


  public Behaviour() {
    this.keyEvent = null;
  }

  public boolean keyPressed() {
    return (keyStatusChanged);
  }

  public boolean key;
}
