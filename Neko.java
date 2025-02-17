import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;

public class Neko extends java.applet.Applet implements Runnable {

    Image nekopics[] = new Image[9];
    Image currentimg;
    Thread runner;
    int xpos = 10;
    int ypos = 10;

     public void init() {
	 String nekosrc[] = { "screenshot_0630130942.png", "screenshot_0630130858.png",
			      "screenshot_0630130839.png", "screenshot_0630130817.png", 
			      "screenshot_0630130801.png","screenshot_0630130738.png",
			      "screenshot_0630130711.png", "screenshot_0630130655.png",
			      "screenshot_0630130638.png" };

	 for (int i=0; i < nekopics.length; i++) {
	     nekopics[i] = getImage(getCodeBase(),
				    "images/" + nekosrc[i]);
	 }
     }

    public void start() {
	if (runner == null) {
	    runner = new Thread(this);
	    runner.start();
	}
    }

    public void stop() {
	if (runner != null) {
	    runner.stop();
	    runner = null;
	}
    }

    public void run() {

	setBackground(Color.lightGray);

 // run from one side of the screen to the middle
	nekorun(0, this.size().width / 2);

 // stop and pause
	currentimg = nekopics[2];
	repaint();
	pause(2500);

 // yawn
	currentimg = nekopics[3];
	repaint();
	pause(2500);

 // scratch four times
	nekoscratch(4);

 // sleep for 5 "turns"
	nekosleep(5);

 // wake up and run off
	currentimg = nekopics[8];
	repaint();
	pause(1250);
	nekorun(xpos, this.size().width + 10);
    }

    void nekorun(int start, int end) {
	for (int i = start; i < end; i+=10) {
	    this.xpos = i;
 // swap images
	    if (currentimg == nekopics[0])
		currentimg = nekopics[1];
	    else if (currentimg == nekopics[1])
		currentimg = nekopics[0];
	    else currentimg = nekopics[0];

	    repaint();
	    pause(1150);
	}
    }

    void nekoscratch(int numtimes) {
	for (int i = numtimes; i > 0; i--) {
	    currentimg = nekopics[4];
	    repaint();
	    pause(1500);
	    currentimg = nekopics[5];
	    repaint();
	    pause(1500);
	}
    }

    void nekosleep(int numtimes) {
	for (int i = numtimes; i > 0; i--) {
	    currentimg = nekopics[6];
	    repaint();
	    pause(1250);
	    currentimg = nekopics[7];
	    repaint();
	    pause(1250);
	}
    }

    void pause(int time) {
	try { Thread.sleep(time); }
	catch (InterruptedException e) { }
    }

    public void paint(Graphics g) {
	int iwidth = currentimg.getWidth(this);
        int iheight = currentimg.getHeight(this);
	g.drawImage(currentimg, xpos, ypos, iwidth / 4, iheight / 4, this);
    }
}