package org.erion.prjs.satiro.tests;

import org.erion.prjs.satiro.core.AnimatedSprite;
import org.erion.prjs.satiro.core.Background;
import org.erion.prjs.satiro.system.Sileno;
import org.erion.prjs.satiro.utils.SatiroTools;

import java.awt.*;

public class AGame extends Sileno {
  private static final long serialVersionUID = 07L;

  public AGame() {
    super("A Game", 640, 480);
    //this.activeFullscreen();
  }

  public static void main(String[] args) {
    AGame ag = new AGame();
    Rectangle rect1 = new Rectangle(0, 0, 200, 200);
    Rectangle rect2 = new Rectangle(201, 0, 100, 100);
		
		/*
		ag.addBackground(new Background("ABack", "/home/alexian/dev/java/source/sprites/back.jpg", 0, 0));
		ag.addBackground(new Background("AGraf", "/home/alexian/dev/java/source/sprites/back2.png", 350, 0));
		ag.addBackground(new Background("AFore", "/home/alexian/dev/java/source/sprites/fore.png", 0, 0));
		 */

    Background b1 = new Background("Cucina", SatiroTools.getJarAddress() + "backgrounds/house2.gif", 0, 0, true);
    b1.addBorder(50, 366, 383, 537);
    b1.addBorder(383, 537, 444, 506);
    ag.addBackground(b1);

    ag.addSprite(new Kid());
    ag.getSprite("Kid").setAnimation(AnimatedSprite.STANDING);
    ag.getSprite("Kid").getProperties().setPosition(445, 195);

    ag.addSprite(new Zeus());
    ag.getSprite("Zeus").setAnimation(AnimatedSprite.STANDING);
    ag.getSprite("Zeus").setPosition(128, 344);

    while (!ag.allObjectsAreReady()) ;
    //ag.runner();
    ag.addNotify();
  }
}