package com.asubtlemind.learning.math.thirdgrade;

import java.awt.Color;

import javax.swing.JPanel;

public class Shooter extends JPanel {
	private static final long serialVersionUID = -2279704402399937416L;
	
	private static int WIDTH = 50;
	private static int HEIGHT = 50;

	public Shooter() {
		setBackground(Color.BLUE);
		setBounds(0,0,WIDTH,HEIGHT);
		setVisible(true);
	}
	
	public void moveShooterLeft() {
		this.setLocation(this.getX() - 10, this.getY());
	}
	
	public void moveShooterRight() {
		this.setLocation(this.getX() + 10, this.getY());
	}	
	
}
