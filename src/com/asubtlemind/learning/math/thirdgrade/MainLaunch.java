package com.asubtlemind.learning.math.thirdgrade;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.ReverbType;
import javax.swing.*;

public class MainLaunch extends JFrame {
	
	private JPanel mathDropper = new MathDropper();
	
	private static final long serialVersionUID = 4574496524420290042L;

	void setGame(String game) {
		if(game == "Math Dropper"){
			mathDropper.setVisible(true);
		}
	}

	MainLaunch() {		
		add(mathDropper);
		mathDropper.setFocusable(true);
		mathDropper.setVisible(true);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu gameMenu = new JMenu("Game");
		menuBar.add(gameMenu);
		
		JMenuItem newMathDropper = new JMenuItem("Math Dropper");
		gameMenu.add(newMathDropper);
		
		newMathDropper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
				System.out.println("here");
				setGame("Math Dropper");
			}
		});
		
		
		setTitle("Welcome to Math Launcher");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	
	public static void main(String[] args) {
		MainLaunch main = new MainLaunch();
	}
}
