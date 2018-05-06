package org.erion.prjs.satiro.system;

import org.erion.prjs.satiro.core.AnimatedSprite;
import org.erion.prjs.satiro.core.Background;
import org.erion.prjs.satiro.utils.ScreenManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.io.Serializable;
import java.util.ArrayList;

class SilenoProperties implements Serializable {
  private static final long serialVersionUID = 05L;

  private String title;

  public SilenoProperties(String title) {
    this.title = title;
  }
}

public class Sileno extends JFrame implements Runnable, KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
  private static final long serialVersionUID = 06L;

  private SilenoProperties prop;
  private Thread sileno;

  private volatile boolean running = false;
  private volatile boolean gameOver = false;
  private int inv = 1;

  private KeyEvent keyPressed = null;
  private long diffTime = -1;
  private long actTime = 0;
  private boolean noKeyPressed = true;
  //private boolean isKeyReleased = true;

  private Graphics g;
  private Image image = null;

  private long fps = 1; // frames per second
  private ArrayList<AnimatedSprite> sprites;
  private ArrayList<Background> backgrounds;

  private ScreenManager screen;
  private Rectangle tempCollisionArea = new Rectangle(591, 336, 10, 300);

  public Sileno(String title, int width, int height) {
    super();
    this.setPreferredSize(new Dimension(width, height));
    this.screen = new ScreenManager(this);
    this.prop = new SilenoProperties(title);
    this.sprites = new ArrayList<AnimatedSprite>();
    this.backgrounds = new ArrayList<Background>();

    this.addKeyListener(this);
    this.addMouseListener(this);
    this.addMouseWheelListener(this);
    this.addMouseMotionListener(this);
  }

  public final boolean allObjectsAreReady() {
    for (Background background : this.backgrounds) {
      while (!background.isReady()) ;
    }

    for (AnimatedSprite sprite : this.sprites) {
      while (!sprite.isReady()) ;
    }

    return true;
  }

  public void addNotify() {
    super.addNotify();
    this.startGame();
  }

  public void addSprite(AnimatedSprite sprite) {
    this.sprites.add(sprite);
  }

  public void addBackground(Background background) {
    this.backgrounds.add(background);
  }

  public void activeFullscreen() {
    this.screen.setFullScreen(this.getWidth(), this.getHeight(), 32);
  }

  public void deactiveFullscreen() {
    this.screen.restoreScreen();
  }

  public void runner() {

  }

  public void run() {
    long beforeTime, timeDiff, sleepTime;

    beforeTime = System.currentTimeMillis();

    this.running = true;
    while (this.running) {
      this.gameUpdate();
      this.gameRender();
      this.gamePaint();

      timeDiff = System.currentTimeMillis() - beforeTime;
      sleepTime = this.fps - timeDiff;

      if (sleepTime <= 0) sleepTime = 5;
      try {
        Thread.sleep(sleepTime);
      } catch (InterruptedException ie) {
        // do nothing
      }

      beforeTime = System.currentTimeMillis();
			/*
			if (this.keyPressed != null) {
				this.noKeyPressed = false;
				this.keyPressed = null;
			} else {
				this.noKeyPressed = true;
			}
			 **/
    }

    System.exit(0);
  }

  private void startGame() {
    if (this.sileno == null || !this.running) {
      this.sileno = new Thread(this);
      this.sileno.start();
      //this.runner();
    }
  }

  public void stopGame() {
    this.running = false;
  }

  private void gameRender() {
    if (this.image == null) {
      this.image = this.createImage(this.getWidth(), this.getHeight());
      if (this.image == null) {
        System.err.println("[satiro - ERROR] Sileno.gameRender: image is null!");
        return;
      } else {
        g = this.image.getGraphics();
        this.enableAntiAlias(g);
      }
    }

    g.setColor(Color.GRAY);
    g.fillRect(0, 0, this.getWidth(), this.getHeight());

    g.setColor(Color.BLUE);
    g.fillRoundRect(0, 0, 768, 553, 20, 20);

    g.setColor(Color.WHITE);
    ((Graphics2D) g).draw(new Line2D.Float(50, 366, 344, 712));

    if (this.backgrounds.size() > 0) {
      for (Background back : this.backgrounds) {
        if (back.isVisible()) {
          g.drawImage(back.getImage(), back.getX(), back.getY(), null);
          for (Line2D line : back.getBorders()) {
            ((Graphics2D) g).draw(line);
          }
        }
      }
    }

    // questa parte di codice indentata serve per visualizzare gli eventi di tastiera
    g.setColor(Color.WHITE);
			/*
			g.drawString("Right: " + keys[0], 812, 15);
			g.drawString("Up: " +    keys[1], 812, 30);
			g.drawString("Left: " +  keys[2], 812, 45);
			g.drawString("Down: " +  keys[3], 812, 60);
			g.drawString("Released: " +  keys[4], 812, 75);
			g.drawString("Typed: " +  keys[5], 812, 90);
			g.drawString("Pressed: " +  keys[6], 812, 105);
			g.drawString("isKeyPressed: " + isKeyPressed, 812, 120);
			*/

    if (this.sprites.size() > 0) {
      for (AnimatedSprite sprite : this.sprites) {
        if (sprite.isVisible()) {
          if (System.currentTimeMillis() - this.actTime > 140) {
            this.diffTime = -1;
            this.actTime = 0;
            if (sprite.getProperties().getIdName().equals("Zeus") &&
              !sprite.getCurrentAnimation().getName().equals(AnimatedSprite.STANDING)) {

              sprite.setAnimation(AnimatedSprite.STANDING);
            }
          }

          int x = (int) sprite.getProperties().getPosition().getX();
          int y = (int) sprite.getProperties().getPosition().getY();
          int xs = (int) sprite.getProperties().getCollisionSpot().getX();
          int ys = (int) sprite.getProperties().getCollisionSpot().getY();

          g.drawImage(
            sprite.getProperties().getCurrentFrame().getImage(),
            x, y, null);
          if (sprite.getCollisionArea() != null) ((Graphics2D) g).draw(sprite.getCollisionArea());
        }


        // le due righe di codice qui sotto mi servono solo per provare gli spostamenti
        //sprite.getProperties().getPosition().setLocation(x + (5 * this.inv), y);
        //sprite.setCollisionSpot(xs + (3 * this.inv), ys);

        // il codice qua sotto � solo per provare le collisioni
				/*
				if (this.tempCollisionArea.intersects(sprite.getCollisionArea()) ||
					sprite.getProperties().getPosition().getX() > 1024) {
					this.inv = -1;
					sprite.setAnimation("de" + AnimatedSprite.WALKING);
				}
				*/
        if (sprite.getProperties().getPosition().getX() < 0) {
          this.inv = 1;
          sprite.setAnimation(AnimatedSprite.WALKING);
        }
      }
    }

    if (this.gameOver) {
      System.out.println("[satiro - INFO] The game is over!");
    }
  }

  public void paintComponents(Graphics g) {
    super.paintComponents(g);
    if (this.image != null) {
      g.drawImage(this.image, 0, 0, null);
    }
  }

  private void enableTransparency(Graphics g, float value) {
    Graphics2D g2d = (Graphics2D) g;

    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, value));
  }

  private void disableTransparency(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;

    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.0f));
  }

  private boolean isAntiAliasOn(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;

    return g2d.getRenderingHints().containsValue(RenderingHints.VALUE_ANTIALIAS_ON);
  }

  private void enableAntiAlias(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;

    // Per abilitare l'antialising sui disegni
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
      RenderingHints.VALUE_ANTIALIAS_ON);

    // Per abilitare l'antialising sul testo
    g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
      RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
  }

  private void disableAntiAlias(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;

    // Per disabilitare l'antialising sui disegni
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
      RenderingHints.VALUE_ANTIALIAS_OFF);

    // Per disabilitare l'antialising sul testo
    g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
      RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
  }

  private void gamePaint() {
    Graphics gg;

    try {
      gg = this.getGraphics();

      if (gg != null && this.image != null) {
        gg.drawImage(this.image, 0, 0, null);
        Toolkit.getDefaultToolkit().sync();
        gg.dispose();
      }
    } catch (Exception e) {
      System.err.println("[satiro - EXCEPTION] Sileno.gamePaint: " + e);
    }
  }

  private void gameUpdate() {
    if (!this.gameOver) {
      for (AnimatedSprite sprite : this.sprites) {
        if (sprite.isVisible()) sprite.updateAnimation();
      }
    }
  }

  public AnimatedSprite getSprite(String idName) {
    for (AnimatedSprite sprite : this.sprites) {
      if (sprite.getProperties().getIdName().equals(idName))
        return sprite;
    }

    return null;
  }

  public final void keyReleased(KeyEvent event) {
    long time = this.actTime - System.currentTimeMillis();

    if (this.diffTime == -1)
      this.diffTime = time;
    else if (this.diffTime < -1)
      this.diffTime = System.currentTimeMillis() - this.actTime;
    else
      this.diffTime = System.currentTimeMillis() - this.actTime;

    this.actTime = System.currentTimeMillis();
  }

  public final void keyPressed(KeyEvent event) {
    boolean collision = this.getSprite("Zeus").collidesWith(this.backgrounds.toArray()[0]);

    if (event.getKeyCode() == KeyEvent.VK_UP && !collision) {
      if (!this.getSprite("Zeus").getCurrentAnimation().getName().equals("up" + AnimatedSprite.WALKING)) {
        this.getSprite("Zeus").setAnimation("up" + AnimatedSprite.WALKING);
      }

      this.getSprite("Zeus").setPosition(
        (int) this.getSprite("Zeus").getProperties().getPosition().getX(),
        (int) this.getSprite("Zeus").getProperties().getPosition().getY() - 6);
    }

    if (event.getKeyCode() == KeyEvent.VK_DOWN && !collision) {
      if (!this.getSprite("Zeus").getCurrentAnimation().getName().equals("dw" + AnimatedSprite.WALKING)) {
        this.getSprite("Zeus").setAnimation("dw" + AnimatedSprite.WALKING);
      }

      this.getSprite("Zeus").setPosition(
        (int) this.getSprite("Zeus").getProperties().getPosition().getX(),
        (int) this.getSprite("Zeus").getProperties().getPosition().getY() + 6);
    }

    if (event.getKeyCode() == KeyEvent.VK_RIGHT && !collision) {
      if (!this.getSprite("Zeus").getCurrentAnimation().getName().equals(AnimatedSprite.WALKING)) {
        this.getSprite("Zeus").setAnimation(AnimatedSprite.WALKING);
      }

      this.getSprite("Zeus").setPosition(
        (int) this.getSprite("Zeus").getProperties().getPosition().getX() + 6,
        (int) this.getSprite("Zeus").getProperties().getPosition().getY());
    }

    if (event.getKeyCode() == KeyEvent.VK_LEFT && !collision) {
      if (!this.getSprite("Zeus").getCurrentAnimation().getName().equals("de" + AnimatedSprite.WALKING)) {
        this.getSprite("Zeus").setAnimation("de" + AnimatedSprite.WALKING);
      }

      this.getSprite("Zeus").setPosition(
        (int) this.getSprite("Zeus").getProperties().getPosition().getX() - 6,
        (int) this.getSprite("Zeus").getProperties().getPosition().getY());
    }
  }

  public final void keyTyped(KeyEvent event) {
  }

  public final void mouseExited(MouseEvent event) {

  }

  public final void mouseEntered(MouseEvent event) {

  }

  public final void mouseReleased(MouseEvent event) {

  }

  public final void mousePressed(MouseEvent event) {

  }

  public final void mouseClicked(MouseEvent event) {

  }

  public final void mouseMoved(MouseEvent event) {

  }

  public final void mouseDragged(MouseEvent event) {

  }

  public final void mouseWheelMoved(MouseWheelEvent event) {

  }
}
