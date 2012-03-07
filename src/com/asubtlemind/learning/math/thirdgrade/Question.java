package com.asubtlemind.learning.math.thirdgrade;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Question extends JPanel {
	
	private static final long serialVersionUID = -7152917516860030895L;
	public static final String[] questions = { "4+3", "5-3", "7+9", "5*5", "1+7", "9/3", "14+4" };
	public static final String[] answers = { "7", "2", "16", "25", "8", "3", "18" };
	private static int WIDTH = 25;
	private static int HEIGHT = 25;
	
	private static Random rn = new Random();
	private int index;
	private boolean isActive;
	private boolean isSolved;
		
	public Question() {
		setRandomQuestion();
		setVisible(true);
		setBackground(Color.GREEN);
		setBounds(0, 0, WIDTH, HEIGHT);
		
		isActive = true;
	}
	
	public String getSelectedAnswer() {
		return answers[index];
	}
	
	public String getSelectedQuestion() {
		return questions[index];
	}

	public void setSolved(boolean isSolved_) {
		isSolved = isSolved_;
		
	}
	
	public void setRandomQuestion()
	{
		index = rn.nextInt(questions.length);
		isSolved = false;
	}
	
	public boolean isSolved() {
		return isSolved;
	}
	
	public void moveDownward() {
		setLocation(this.getX(), this.getY() + 1);
	}
	
	public void hideQuestion() {
		setVisible(false);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		//g.drawString(getSelectedQuestion(), 0, 0);
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isActive() {
		return isActive;
	}
}
