package org.erion.prjs.satiro.platform;

import org.erion.prjs.satiro.utils.ScreenManager;

import java.awt.*;
import java.util.ArrayList;

public abstract class SatiroGame implements Runnable {
  private SatiroGameProperties prop;
  private SatiroFrame frame;
  private Thread satiroThread;

  private ScreenManager screenManager;
  private ArrayList<SatiroLevel> levels;

  public SatiroGame(String title, String version, String author, int width, int height, int colorDepth) {
    this.initGame(title, version, author, width, height, colorDepth, 75);
  }

  public SatiroGame(String title, String version, String author, int width, int height, int colorDepth, int refresh) {
    this.initGame(title, version, author, width, height, colorDepth, refresh);
  }

  private void initGame(String title, String version, String author, int width, int height, int colorDepth, int refresh) {
    this.prop = new SatiroGameProperties();
    this.prop.setTitle(title);
    this.prop.setVersion(version);
    this.prop.setResolution(width, height);
    this.frame = new SatiroFrame(1024, 768, Color.BLUE);
    this.screenManager = new ScreenManager(this.frame);
    this.levels = new ArrayList<SatiroLevel>();
    this.satiroThread = new Thread(this);
  }

  public void fullScreen() {
    this.screenManager.setFullScreen(
      (int) this.prop.getResolution().getWidth(),
      (int) this.prop.getResolution().getHeight(),
      this.prop.getColorDepth(),
      this.prop.getRefreshRate());
  }

  public void addLevel(SatiroLevel level) {
    this.levels.add(level);
  }

  public SatiroLevel getLevel(String title) {
    for (SatiroLevel level : this.levels) {
      if (level.getProperties().getTitle().equals(title)) {
        return level;
      }
    }

    System.err.println("[satiro.platform.SatiroGame] Error: level `" + title + "` not found!");
    return null;
  }

  public abstract void startGame();

  public abstract void pauseGame();

  public abstract void stopGame();

  public abstract void behaviour();

  public void run() {
    while (true) {
      this.showGame();

      try {
        Thread.sleep(20);
      } catch (InterruptedException ie) {
        // do nothing
      }
    }
  }

  public void starter() {
    if (this.satiroThread != null) this.satiroThread.start();
  }

  private void showGame() {
    for (SatiroLevel level : this.levels) {
      if (level.getProperties().isActive()) {
        level.updateLevel();
      }
    }

    for (SatiroLevel level : this.levels) {
      if (level.getProperties().isActive()) {
        this.frame.draw(level.getLevelArea(), 0, 0);
      }
    }

    this.frame.repaint();
  }
}