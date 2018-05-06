/*
 * Level.java
 *
 * Created on 16 novembre 2005, 15.34
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.erion.prjs.satiro.platform;

import org.erion.prjs.satiro.core.AnimatedSprite;
import org.erion.prjs.satiro.core.Background;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author alexian
 */
public abstract class SatiroLevel {
  private SatiroLevelProperties prop;
  private BufferedImage gameArea;
  private Robot tool;

  protected ArrayList<AnimatedSprite> sprites;
  protected ArrayList<Background> backgrounds;

  // private Hashtable<String, SpriteProperties> spriteProps;
  // private Hashtable<String, Background> backgrounds;

  // private CollisionSide[] grid;

  /**
   * Creates a new instance of SatiroLevel
   */
  public SatiroLevel(String title, String password, String description, int width, int height) {
    this.prop = new SatiroLevelProperties(title, password, description, width, height, 0, 0);
    this.gameArea = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    this.sprites = new ArrayList<AnimatedSprite>();
    this.backgrounds = new ArrayList<Background>();

    try {
      this.tool = new Robot(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice());
    } catch (AWTException awte) {
      // do nothing
    }
  }

  public SatiroLevelProperties getProperties() {
    return this.prop;
  }

  public BufferedImage getLevelArea() {
    return this.gameArea;
  }

  public void updateLevel() {
    // Level manager
    Graphics2D g2d = (Graphics2D) this.gameArea.getGraphics();

    for (AnimatedSprite sprite : this.sprites) {
      if (sprite.getProperties().isActive()) {
        sprite.updateAnimation();
      }
    }

    for (Background back : this.backgrounds) {
      g2d.drawImage(back.getImage(), null, 0, 0);
    }

    for (AnimatedSprite sprite : this.sprites) {
      if (sprite.getProperties().isActive())
        g2d.drawImage(sprite.getProperties().getCurrentFrame().getImage(), null, 0, 0);
    }

    this.behaviour();
    this.eventsManager();
  }

  public void animateScene() {
    Graphics2D g2d = (Graphics2D) this.gameArea.getGraphics();
		
		/*
		for (Background background : this.backgrounds) {
			g2d.drawImage(
				background.getProperties(),getImage(), null, 
				background.getProperties().getX(),
				background.getProperties().getY());
		}
		 */

    for (AnimatedSprite sprite : this.sprites) {
      if (sprite.getProperties().isActive()) {
        if (sprite.getProperties().getCurrentFrame() != null) {
          g2d.drawImage(
            sprite.getProperties().getCurrentFrame().getImage(), null,
            (int) sprite.getProperties().getPosition().getX(),
            (int) sprite.getProperties().getPosition().getY());
        }
      }
    }
  }

  public boolean existsSprite(String idName) {
    for (AnimatedSprite sprite : this.sprites) {
      if (sprite.getProperties().getIdName().equals(idName)) {
        return true;
      }
    }

    return false;
  }

  public boolean existsBackground(String idName) {
    for (Background back : this.backgrounds) {
      if (back.getIdName().equals(idName))
        return true;
    }

    return false;
  }

  private ArrayList<AnimatedSprite> addSpriteInOrder(AnimatedSprite sprite) {
    boolean spriteNotInserted = true;

    ArrayList<AnimatedSprite> al = new ArrayList<AnimatedSprite>();
    if (this.sprites.size() > 0) {
      for (AnimatedSprite s : this.sprites) {
        int p1 = s.getProperties().getVisualizationPriority();
        int p2 = sprite.getProperties().getVisualizationPriority();

        if (p1 > p2) {
          al.add(sprite);
          al.add(s);
          spriteNotInserted = false;
        } else {
          al.add(s);
        }
      }
    } else {
      al.add(sprite);
      spriteNotInserted = false;
    }

    if (spriteNotInserted) al.add(sprite);
    return al;
  }

  public void addSprite(AnimatedSprite sprite) {
    if (this.existsSprite(sprite.getProperties().getIdName())) {
      System.err.println("[satiro.platform.SatiroLevel - " + System.currentTimeMillis() + "] Error: a sprite with name " + sprite.getProperties().getIdName() + " already exists. AnimatedSprite not added.");
    } else {
      this.sprites = this.addSpriteInOrder(sprite);
      System.out.println("[satiro.platform.SatiroLevel - " + System.currentTimeMillis() + "] Info: sprite " + sprite.getProperties().getIdName() + " added to level " + this.getProperties().getTitle());
    }
  }

  public AnimatedSprite getSprite(String idName) {
    for (AnimatedSprite sprite : this.sprites) {
      if (sprite.getProperties().getIdName().equals(idName)) {
        return sprite;
      }
    }

    System.err.println("[satiro.platform.SatiroLevel - " + System.currentTimeMillis() + "] Error: sprite `" + idName + "` not found.");
    return null;
  }

  private ArrayList<Background> addBackgroundInOrder(Background background) {
    boolean backgroundNotInserted = true;

    ArrayList<Background> al = new ArrayList<Background>();
    if (this.backgrounds.size() > 0) {
      for (Background b : this.backgrounds) {
        int p1 = b.getVisualizationPriority();
        int p2 = background.getVisualizationPriority();

        if (p1 > p2) {
          al.add(background);
          al.add(b);
          backgroundNotInserted = false;
        } else {
          al.add(b);
        }
      }
    } else {
      al.add(background);
      backgroundNotInserted = false;
    }

    if (backgroundNotInserted) al.add(background);
    return al;
  }

  public void addBackground(Background background) {
    if (this.existsBackground(background.getIdName())) {
      System.err.println("[satiro.platform.SatiroLevel - " + System.currentTimeMillis() + "] Error: background " + background.getIdName() + " already exists. Background not added.");
    } else {
      this.backgrounds = this.addBackgroundInOrder(background);
      System.out.println("[satiro.platform.SatiroLevel - " + System.currentTimeMillis() + "] Info: background " + background.getIdName() + " added to level " + this.getProperties().getTitle());
    }
  }

  public Background getBackground(String idName) {
    for (Background background : this.backgrounds) {
      if (background.getIdName().equals(idName)) {
        return background;
      }
    }

    System.err.println("[satiro.platform.SatiroLevel - " + System.currentTimeMillis() + "] Error: background `" + idName + "` not found.");
    return null;
  }

  private void eventsManager() {

  }

  public void listSprites() {
    for (AnimatedSprite sprite : this.sprites) {
      System.out.println("Lo sprite: " + sprite.getProperties().getIdName());
    }
  }

  public abstract void behaviour();

  public abstract void startLevel();

  public abstract void stopLevel();
}
