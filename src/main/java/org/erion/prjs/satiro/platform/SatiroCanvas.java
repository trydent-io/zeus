package org.erion.prjs.satiro.platform;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SatiroCanvas extends Canvas implements Runnable {
  private static final long serialVersionUID = 1212121212l;

  private static BufferedImage bufferedArea;
  private static Graphics2D gameArea;

  public SatiroCanvas() {
    bufferedArea = new BufferedImage(1024, 768, BufferedImage.TYPE_INT_ARGB);
    gameArea = bufferedArea.createGraphics();
    this.setBackground(Color.WHITE);
  }

  public void run() {
    // avr� effettivamente senso?
  }

  private BufferedImage toBufferedImage(Image image) {
    BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
    bi.getGraphics().drawImage(image, 0, 0, null);
    bi.getGraphics().dispose();

    return bi;
  }

  public synchronized void drawObject(Image image, int x, int y) {
    gameArea = bufferedArea.createGraphics();
    gameArea.drawRect(0, 0, 1023, 767);
    gameArea.drawImage(this.toBufferedImage(image), null, x, y);
  }

  public void paint(Graphics g) {
    //this.gameArea = (Graphics2D) g;
  }
}