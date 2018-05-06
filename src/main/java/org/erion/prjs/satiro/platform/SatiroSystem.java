package org.erion.prjs.satiro.platform;

import org.erion.prjs.satiro.core.Frame;

import javax.swing.*;
import java.awt.*;

public class SatiroSystem extends Thread {
  private static Graphics graphics;
  private static Graphics gOrg;
  private Image bgimage = new ImageIcon("/home/alexian/dev/java/source/ch02/ch02src/images/background2.jpg").getImage();

  public SatiroSystem() {
    // do nothing... perhaps... �_�
  }

  public SatiroSystem(Graphics g) {
    graphics = g;
    gOrg = g;
    graphics.setColor(Color.PINK);
  }

  public Image loadImage(String fileName) {
    return new ImageIcon(fileName).getImage();
  }

  public synchronized void drawSprite(Frame frame, Point position) {
    graphics = gOrg;
    graphics.drawImage(this.bgimage, 0, 0, null);
    graphics.drawImage(frame.getImage(), (int) position.getX(), (int) position.getY(), null);
    //draw(this.graphics, this.bgimage, 0, 0);
    //draw(this.graphics, frame.getImageFrame(), 0, 0);
    //this.graphics.dispose();
  }

  private synchronized void draw(Graphics g, Image i, int x, int y) {
    g.drawImage(i, x, y, null);
  }

  public void run() {
    System.out.println("[satiro.platform - " + System.currentTimeMillis() + "] Info: Satiro Platform initializated");
    while (true) {
      //this.graphics.dispose();
    }
  }
}
