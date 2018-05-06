/*
 * ScreenManager.java
 *
 * Created on 15 novembre 2005, 18.54
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.erion.prjs.satiro.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;

public class ScreenManager {
  private GraphicsDevice device;
  private final JFrame frame;

  /**
   * Creates a new ScreenManager object.
   */
  public ScreenManager(JFrame frame) {
    if (frame == null) {
      System.err.println("[satiro.utils.ScreenManager] Error: frame object is null!");
      System.exit(-1);
    }
    this.frame = frame;

    GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
    device = environment.getDefaultScreenDevice();
  }


  /**
   * Returns a list of compatible display modes for the
   * default device on the System.
   */
  public DisplayMode[] getCompatibleDisplayModes() {
    return device.getDisplayModes();
  }


  /**
   * Returns the first compatible mode in a list of modes.
   * Returns null if no modes are compatible.
   */
  public DisplayMode findFirstCompatibleMode(DisplayMode modes[]) {
    DisplayMode goodModes[] = device.getDisplayModes();
    DisplayMode bestModes[] = new DisplayMode[goodModes.length];

    int index = 0;
    for (int i = 0; i < modes.length; i++) {
      for (int j = 0; j < goodModes.length; j++) {
        System.out.println("[satiro.utils.ScreenManager] Info: possible display resolutions: " +
          goodModes[i].getWidth() + " by " + goodModes[i].getHeight() + " with " +
          goodModes[i].getBitDepth() + "bit color depth.");
        if (displayModesMatch(modes[i], goodModes[j])) {
          bestModes[index++] = modes[i];
        }
      }
    }
    if (bestModes.length > 0) {
      System.out.println("[satiro.utils.ScreenManager] Info: display will be " +
        bestModes[bestModes.length - 1].getWidth() + " by " +
        bestModes[bestModes.length - 1].getHeight() + " with " +
        bestModes[bestModes.length - 1].getBitDepth() + "bit color depth");
      return bestModes[bestModes.length - 1];
    }

    System.out.println("[satiro.utils.ScreenManager] Error: no avaiable display resolutions found!");
    return null;
  }

  public DisplayMode getCurrentDisplayMode() {
    return device.getDisplayMode();
  }


  /**
   * Determines if two display modes "match". Two display
   * modes match if they have the same resolution, bit depth,
   * and refresh rate. The bit depth is ignored if one of the
   * modes has a bit depth of DisplayMode.BIT_DEPTH_MULTI.
   * Likewise, the refresh rate is ignored if one of the
   * modes has a refresh rate of
   * DisplayMode.REFRESH_RATE_UNKNOWN.
   */
  public boolean displayModesMatch(DisplayMode mode1, DisplayMode mode2) {
    return mode1.getWidth() == mode2.getWidth() && mode1.getHeight() == mode2.getHeight();
  }

  public void setFullScreen(int width, int height, int colorDepth, int refresh) {
    this.setFullScreen(
      new DisplayMode(width, height, colorDepth, refresh)
    );
  }

  public void setFullScreen(int width, int height, int colorDepth) {
    this.setFullScreen(
      new DisplayMode(width, height, colorDepth, DisplayMode.REFRESH_RATE_UNKNOWN)
    );
  }

  /**
   * Enters full screen mode and changes the display mode.
   * If the specified display mode is null or not compatible
   * with this device, or if the display mode cannot be
   * changed on this System, the current display mode is used.
   * <p>
   * The display uses a BufferStrategy with 2 buffers.
   */
  public void setFullScreen(DisplayMode displayMode) {
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.frame.setUndecorated(true);
    this.frame.setIgnoreRepaint(true);
    this.frame.setResizable(false);

    device.setFullScreenWindow(frame);

    if (displayMode != null && device.isDisplayChangeSupported()) {
      try {
        device.setDisplayMode(displayMode);
      } catch (IllegalArgumentException iae) {
        System.err.println("[satiro.utils.ScreenManager] Exception: " + iae);
      }
      // fix for mac os x
      frame.setSize(displayMode.getWidth(), displayMode.getHeight());
    }

    // avoid potential deadlock in 1.4.1_02
    try {
      EventQueue.invokeAndWait(new Runnable() {
        public void run() {
          frame.createBufferStrategy(2);
        }
      });
    } catch (InterruptedException ie) {
      // ignore
      System.err.println("[satiro.utils.ScreenManager] Exception: " + ie);
    } catch (InvocationTargetException ite) {
      // ignore
      System.err.println("[satiro.utils.ScreenManager] Exception: " + ite);
    }
  }


  /**
   * Gets the graphics context for the display. The
   * ScreenManager uses double buffering, so applications must
   * call update() to show any graphics drawn.
   * <p>
   * The application must dispose of the graphics object.
   */
  public Graphics2D getGraphics() {
    Window window = device.getFullScreenWindow();
    if (window != null) {
      BufferStrategy strategy = window.getBufferStrategy();
      return (Graphics2D) strategy.getDrawGraphics();
    } else {
      return null;
    }
  }


  /**
   * Updates the display.
   */
  public void update() {
    Window window = device.getFullScreenWindow();
    if (window != null) {
      BufferStrategy strategy = window.getBufferStrategy();
      if (!strategy.contentsLost()) {
        strategy.show();
      }
    }
    // Sync the display on some Systems.
    // (on Linux, this fixes event queue problems)
    Toolkit.getDefaultToolkit().sync();
  }


  /**
   * Returns the window currently used in full screen mode.
   * Returns null if the device is not in full screen mode.
   */
  public JFrame getFullScreenWindow() {
    return (JFrame) device.getFullScreenWindow();
  }


  /**
   * Returns the width of the window currently used in full
   * screen mode. Returns 0 if the device is not in full
   * screen mode.
   */
  public int getWidth() {
    Window window = device.getFullScreenWindow();
    if (window != null) {
      return window.getWidth();
    } else {
      return 0;
    }
  }


  /**
   * Returns the height of the window currently used in full
   * screen mode. Returns 0 if the device is not in full
   * screen mode.
   */
  public int getHeight() {
    Window window = device.getFullScreenWindow();
    if (window != null) {
      return window.getHeight();
    } else {
      return 0;
    }
  }


  /**
   * Restores the screen's display mode.
   */
  public void restoreScreen() {
    Window window = device.getFullScreenWindow();

    if (window != null) {
      window.dispose();
    }

    device.setFullScreenWindow(null);
  }


  /**
   * Creates an image compatible with the current display.
   */
  public BufferedImage createCompatibleImage(int w, int h, int transparancy) {
    Window window = device.getFullScreenWindow();

    if (window != null) {
      GraphicsConfiguration gc = window.getGraphicsConfiguration();
      return gc.createCompatibleImage(w, h, transparancy);
    }

    return null;
  }
}
