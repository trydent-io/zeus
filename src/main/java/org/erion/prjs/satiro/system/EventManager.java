/*
 * EventManager.java
 *
 * Created on 3 dicembre 2005, 18.24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.erion.prjs.satiro.system;

import org.erion.prjs.satiro.core.AnimatedSprite;
import org.erion.prjs.satiro.core.Background;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * @author alexian
 */
public class EventManager {
  private static KeyEvent keyPressed;
  private static KeyEvent keyReleased;
  private static KeyEvent keyTyped;
  private static MouseEvent mouseClicked;
  private static MouseEvent mouseReleased;

  private static boolean noKeyTouched;

  public boolean isKeyPressed(int key) {
		return keyPressed.getKeyCode() == key;

	}

  public boolean isKeyReleased(int key) {
		return keyReleased.getKeyCode() == key;

	}

  public boolean isKeyTyped(int key) {
		return keyTyped.getKeyCode() == key;

	}

  public boolean noKeyTouched() {
    return noKeyTouched;
  }

  public void setKeyPressed(KeyEvent key) {
    keyPressed = key;
  }

  public void setKeyReleased(KeyEvent key) {
    keyReleased = key;
  }

  public void setKeyTyped(KeyEvent key) {
    keyTyped = key;
  }

  public boolean collisionBetween(Object o1, Object o2) {
    if ((o1 instanceof AnimatedSprite || o1 instanceof Background) &&
      (o2 instanceof AnimatedSprite || o2 instanceof Background)) {

      AnimatedSprite s1, s2;
      s1 = s2 = null;
      Background b1, b2;
      b1 = b2 = null;

      if (o1 instanceof AnimatedSprite) s1 = (AnimatedSprite) o1;
      if (o1 instanceof Background) b1 = (Background) o1;

      if (o2 instanceof AnimatedSprite) s2 = (AnimatedSprite) o2;
      if (o2 instanceof Background) b2 = (Background) o2;

      if (s1 != null && s1.collidesWith(o2)) return true;
      if (s2 != null && s2.collidesWith(o1)) return true;
      if (b1 != null && b1.collidesWith(o2)) return true;
			return b2 != null && b2.collidesWith(o1);
    } else {
      System.err.println("[satiro.system.EventManager.collisionBetween - " + System.currentTimeMillis() + "] Error: Object1 or Object2 is not valid!");
    }

    return false;
  }
}
