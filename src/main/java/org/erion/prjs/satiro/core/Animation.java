package org.erion.prjs.satiro.core;

import java.util.ArrayList;

public class Animation {
  public static final long FRAME_VELOCITY = Long.MIN_VALUE;
  public static final long DEFAULT_VELOCITY = Long.MAX_VALUE;

  private String name;

  private long velocity;
  private ArrayList<Frame> frames;

  public Animation() {
    this.velocity = 0;
  }

  public Animation(Frame[] frames, long velocity) {
    this.setFrames(frames);
    this.setVelocity(velocity);
  }

  public Animation(ArrayList<Frame> frames, long velocity) {
    this.setFrames(frames);
    this.setVelocity(velocity);
  }

  public boolean isReady() {
    for (Frame frame : this.frames) {
      while (!frame.isReady()) ;
    }

    return true;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setFrames(Frame[] frames) {
    this.frames = new ArrayList<Frame>();

    for (Frame frame : frames) {
      this.frames.add(frame);
    }
  }

  public void setFrames(ArrayList<Frame> frames) {
    this.frames = frames;
  }

  public ArrayList<Frame> getFrames() {
    return this.frames;
  }

  public Frame[] getFramesArray() {
    return (Frame[]) this.frames.toArray();
  }

  public boolean addFrame(Frame frame) {
    if (frame == null) {
      System.err.println("[satiro.core - " + System.currentTimeMillis() + "] Error: the frame is empity!");
      return false;
    }

    this.frames.add(frame);
    return true;
  }

  public boolean addFrame(Frame frame, int position) {
    if (frame == null) {
      System.err.println("[satiro.core - " + System.currentTimeMillis() + "] Error: the frame is empity!");
      return false;
    }

    if (position > -1 && position < this.frames.size() - 1) {
      if (position == this.frames.size() - 1) {
        this.frames.add(frame);
        return true;
      }

      for (int index = this.frames.size() - 1; index >= position; index--) {
        if (index == this.frames.size() - 1) this.frames.add(((Frame[]) this.frames.toArray())[index]);
      }

      this.frames.set(position, frame);
      return true;
    }

    System.err.println("[satiro.core - " + System.currentTimeMillis() + "] Error: position is out of range!");
    return false;
  }

  public Frame getFrame(int number) {
    if (number > -1 && number < this.frames.size()) {
      return this.frames.get(number);
    }

    System.err.println("[satiro.core - " + System.currentTimeMillis() + "] Error: frame number " + number + " does not exist!");
    return null;
  }

  public void removeAllFrames() {
    this.frames = null;
  }

  public void removeFrame(int number) {
    if (number > -1 && number < this.frames.size()) {
      this.frames.remove(number);
    }

    System.err.println("[satiro.core - " + System.currentTimeMillis() + "] Error: frame number " + number + " does not exist!");
  }

  public void setVelocity(long velocity) {
    if (velocity == FRAME_VELOCITY) {
      // animation velocity = frameLock1 + frameLock2 + ... + frameLockN
      for (Frame frame : this.frames) {
        this.velocity += frame.getDuration();
      }

    } else if (velocity == DEFAULT_VELOCITY) {
      // animation velocity = numberOfFrames * 100ms
      this.velocity = this.frames.size() * 100;

    } else if (velocity >= 0) {
      // frameLock1 = frameLock2 = ... = frameLockN = velocity / numberOfFrames
      this.velocity = velocity;
      for (Frame frame : this.frames) {
        frame.setDuration(velocity / this.frames.size());
      }
    }
  }

  public long getVelocity() {
    return this.velocity;
  }

  public synchronized void start() {

  }

  public synchronized void update() {

  }
}
