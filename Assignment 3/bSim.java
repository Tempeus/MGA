import java.awt.Color;
import java.awt.Font;

import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

@SuppressWarnings("serial")
public class bSim extends GraphicsProgram {
	//required parameters for the program
	private static final int WIDTH = 1200;
	public static final int HEIGHT = 600;
	public static final int OFFSET = 200;
	public static final int NUMBALLS = 15;
	private static final double MINSIZE = 1;
	private static final double MAXSIZE = 8;
	private static final double XMIN = 10;
	private static final double XMAX = 50;
	private static final double YMIN = 50;
	private static final double YMAX = 100;
	private static final double EMIN = 0.4;
	private static final double EMAX = 0.9;
	private static final double VMIN = 0.5;
	private static final double VMAX = 3.0;
	
	RandomGenerator rgen = new RandomGenerator();
	bTree myTree = new bTree();	
	
	public void init() {
		addMouseListeners();
	}
	
	public void run() {
		
		//set up display, create and start multiple instances of gball
		this.resize(WIDTH, HEIGHT + OFFSET);
		GLine ground = new GLine(0,HEIGHT,WIDTH,HEIGHT);
		add(ground);
		
		
		for(int i = 0; i < NUMBALLS; i++) {
			double iX = rgen.nextDouble(XMIN,XMAX);
			double iY = rgen.nextDouble(YMIN,YMAX);
			double iSize = rgen.nextDouble(MINSIZE,MAXSIZE);
			Color iColor = rgen.nextColor();
			double iE = rgen.nextDouble(EMIN,EMAX);
			double iV = rgen.nextDouble(VMIN,VMAX);
			
			gBall iball = new gBall(iX *5,iY * 5,iSize * 5 ,iColor ,iE ,iV);
			add(iball.myBall);
			myTree.addNode(iball);
			iball.start();
		}
		while(myTree.isRunning());
		
		GLabel Notification = new GLabel ("Left click to sort the balls",400, 500);
		Notification.setFont(new Font("Serif",Font.BOLD, 46));
		add(Notification);
		waitForClick();
		myTree.moveSort();
	}
}
