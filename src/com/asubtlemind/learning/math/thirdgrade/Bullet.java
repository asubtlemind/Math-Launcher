package com.asubtlemind.learning.math.thirdgrade;

import javax.swing.JPanel;

public class Bullet extends JPanel {
	private static final long serialVersionUID = -8692746587814920104L;
	private static int WIDTH = 25;
	private static int HEIGHT = 25;
	
	public Bullet(int x, int y)
	{
		setBounds(x,y,WIDTH,HEIGHT);
	}

	public void moveUpward() {
		setLocation(getX(),getY() - 1);
	}
}
