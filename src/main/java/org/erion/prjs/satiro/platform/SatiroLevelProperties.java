/*
 * SatiroLevelProperties.java
 *
 * Created on 16 novembre 2005, 15.44
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.erion.prjs.satiro.platform;

import java.io.Serializable;

/**
 * @author alexian
 */
public class SatiroLevelProperties implements Serializable {
  private static final long serialVersionUID = 02l;

  private String title;
  private String password;
  private String description;

  private boolean isActive;

  private int areaWidth;
  private int areaHeight;
  private int x;
  private int y;

  /**
   * Creates a new instance of SatiroLevelProperties
   */
  public SatiroLevelProperties(String title, String password, String description, int areaWidth, int areaHeight, int x, int y) {
    this.title = title;
    this.password = password;
    this.description = description;
    this.areaWidth = areaWidth;
    this.areaHeight = areaHeight;
    this.x = x;
    this.y = y;
  }

  public void active() {
    this.isActive = true;
  }

  public void deactive() {
    this.isActive = false;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTitle() {
    return this.title;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPassword() {
    return this.password;
  }

  public void putADesription(String description) {
    this.description = description;
  }

  public String giveTheDescription() {
    return this.description;
  }

  public void setAreaWidth(int width) {
    this.areaWidth = width;
  }

  public int getAreaWidth() {
    return this.areaWidth;
  }

  public void setAreaHeight(int height) {
    this.areaHeight = height;
  }

  public int getAreaHeight() {
    return this.areaHeight;
  }

  public void setX() {
    this.x = x;
  }

  public int getX() {
    return this.x;
  }

  public void setY() {
    this.y = y;
  }

  public int getY() {
    return this.y;
  }

  public boolean isActive() {
    return this.isActive;
  }
}
