package org.erion.prjs.satiro.platform;

import org.erion.prjs.satiro.utils.SatiroTools;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SatiroFrame extends JFrame implements Runnable {
  protected static final long serialVersionUID = 1212121223l;

  private static BufferedImage bufferedArea;
  private static Graphics2D gameArea;

  private static boolean objects;

  public SatiroFrame(int width, int height, Color background) {
    bufferedArea = new BufferedImage(1024, 768, BufferedImage.TYPE_INT_ARGB);
    gameArea = (Graphics2D) bufferedArea.getGraphics();
    this.setBackground(background);
  }

  public void paint(Graphics g) {
    updater(g);
    g.dispose();
  }

  private void updater(Graphics g) {
    ((Graphics2D) g).drawImage(bufferedArea, null, 0, 0);
  }

  public synchronized void draw(Image image, int x, int y) {
    // this.gameArea = this.bufferedArea.createGraphics();
    gameArea.drawImage(SatiroTools.toBufferedImage(image), null, x, y);
  }

  public synchronized void draw(BufferedImage image, int x, int y) {
    gameArea.drawImage(image, null, x, y);
  }

  public synchronized void objects(boolean present) {
    objects = present;
  }

  public boolean objectsPresent() {
    return objects;
  }

  public void run() {
    while (true) {
      if (objects) repaint();
    }
  }
}
