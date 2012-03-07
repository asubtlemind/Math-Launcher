package com.asubtlemind.learning.math.thirdgrade;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MathDropper extends JPanel {
	
	private static final long serialVersionUID = -6708389277123145617L;
	
	private static Random rn = new Random();
	private static int STARTING_BOARD = 0;
	private static int BOARD_WIDTH = 50;
	private static int BOARD_HEIGHT = 10;
	private static int BOARD_BOTTOM = 500;
	private static int SPACER_WIDTH = 3;
	private static int ENDING_BOARD = 700;
	private static Font FONT_SCORE_BOARD = new Font("Helvetica", Font.PLAIN,  22);
	
	private BufferedImage bgImage = null;
	private boolean boardsHit[];
	private Question questionArr[];
	private int delay = 50;
	private int score = 0;
	private boolean gameOver;
	private String lastMessage;
	private Timer timer; 
	private StringBuilder input = new StringBuilder();
	private Shooter shooter;
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private ArrayList<Question> questions = new ArrayList<Question>();
	private boolean questionAttempted = false;
	private boolean questionAttemptSuccessful = false;
	
	
	private void detectAndProcessAllCollisions() {
		
		for(Bullet bullet : bullets) {
			int index = getBoardLocation(bullet.getX());
			if(bullet.getBounds().intersects(questionArr[index].getBounds())) {
				increaseScore();
				remove(bullet);
				//bullets.remove(bullet);	//this will throw a ConcurrentModificationException - no lie
				remove(questionArr[index]);
				continue;
			}
		}
	}
	
	private void increaseScore() {
		score += 10;
	}
	
	private void decreaseScore() {
		score -= 5;
	}
	
	private void processAnswer(int shooterXLocation) {
		questionAttempted = true;
		Question question = questionArr[ getBoardLocation(shooterXLocation) ];
		if(question != null && question.isActive()) {
			if(input.toString().equals(question.getSelectedAnswer())) {
				question.setSolved(true);	
				question.setActive(false);
				questionAttemptSuccessful = true;
			}
		}
		resetUserInputText();
	}
	
	public MathDropper() {
		setLayout(null);
		//configure background details
		try {
			bgImage = ImageIO.read(new File("src/com/asubtlemind/learning/math/thirdgrade/resources/mdpBG1-X.jpg"));
		} catch (IOException e) {
			setBackground(Color.WHITE);
		}
		
		//configure shooter details
		shooter = new Shooter();
		shooter.setLocation((ENDING_BOARD - STARTING_BOARD)/2, BOARD_BOTTOM + 2*BOARD_HEIGHT); 
		add(shooter);
		
		//configure board details
		int arr_size = (ENDING_BOARD - STARTING_BOARD)/BOARD_WIDTH;
		boardsHit = new boolean[arr_size];		//defaults to false
		questionArr = new Question[arr_size]; 	//defaults to null values
		
		//configure first question details
		questionArr[0] = new Question();
		add(questionArr[0]);
		questions.add(questionArr[0]);
		
		//configure mouse and keyboard events
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(delay > 0)
					delay--;
				
				timer.setDelay(delay);
			}
		});
		
		addKeyListener( new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
					case KeyEvent.VK_LEFT: shooter.moveShooterLeft(); break;
					case KeyEvent.VK_RIGHT: shooter.moveShooterRight(); break;
					case KeyEvent.VK_ENTER: processAnswer(shooter.getX()); break;
					case KeyEvent.VK_BACK_SPACE: if(input.length() > 0) input.deleteCharAt(input.length()-1);  break;
					case KeyEvent.VK_ESCAPE: resetUserInputText(); break;
					default: input.append(e.getKeyChar());
				}
			}
		});
		
		//start the game
		timer = new Timer(delay, new TimerListener());
		timer.start();	
	
	}
	
	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			gameLoop();
			repaint();
		}
	}
	
	
	private int getBoardLocation(int xLocation) {
		return( (int) Math.ceil((xLocation - STARTING_BOARD)/BOARD_WIDTH) );
	}
	
	private void eliminateBoard(int xLocation) {	
		boardsHit[ getBoardLocation(xLocation) ] = true;
	}
	
	private boolean isQuestionMissed(Question question) {
		if(question.isActive())
			return( question.getY() + question.getHeight() >= BOARD_BOTTOM );
		else
			return( false );
	}
	
	private boolean isLastBoard() {
		short liveBoards = 0; 
		for(boolean x : boardsHit) { 	if(!x) ++liveBoards; if(liveBoards > 1) return false; }
		return true;
	}
	
	private void paintScoreBoard(Graphics g) {
		if(score > 0)
			g.setColor(Color.GREEN);
		else
			g.setColor(Color.RED);
		
		g.setFont(FONT_SCORE_BOARD);
		g.drawString( String.valueOf(score), 500, 100 );
		g.drawRect(450, 75, 200, 260);
	}
	
	private void paintBase(Graphics g) {
		for(int i=0; i < boardsHit.length; ++i)
		{
			if(!boardsHit[i])
				g.fillRect(STARTING_BOARD + (i*BOARD_WIDTH), BOARD_BOTTOM, BOARD_WIDTH - SPACER_WIDTH,BOARD_HEIGHT);
		}
	}
	
	private void generateNewQuestion() {
		int randomBoardChoice = rn.nextInt(boardsHit.length);
		int tries = 0;
		while(boardsHit[randomBoardChoice] == true && (questionArr[randomBoardChoice] == null || !questionArr[randomBoardChoice].isActive()))
		{
			randomBoardChoice = rn.nextInt(boardsHit.length);
					
			if(tries > 10) return;
			tries++;
		}
		
				
		if(questionArr[randomBoardChoice] == null) {
			questionArr[randomBoardChoice] = new Question();
		} else {
			questionArr[randomBoardChoice].setRandomQuestion();
			questionArr[randomBoardChoice].setActive(true);
		}
		
		questionArr[randomBoardChoice].setLocation((randomBoardChoice * BOARD_WIDTH) - STARTING_BOARD + BOARD_WIDTH/2, 0 );
		add(questionArr[randomBoardChoice]);
	}
	
	
	private void resetQuestionAttemptState()
	{
		questionAttempted = false;
		questionAttemptSuccessful = false;
	}
	

	private void gameLoop() {
		
		if(questionAttempted && questionAttemptSuccessful) {				//fire bullet
			Bullet bullet = new Bullet(shooter.getX(),shooter.getY());
			bullets.add(bullet);
			add(bullet);
			
			generateNewQuestion();
			resetQuestionAttemptState();
		}    

		detectAndProcessAllCollisions();
		
		for(Question question : questionArr)
			if(question != null)
				if(isQuestionMissed(question))
				{
					question.setActive(false);
					eliminateBoard(question.getX());
					generateNewQuestion();
					remove(question);
					decreaseScore();
					
					if(isLastBoard()) {
						gameOver = true;
					}
				} else {
					question.moveDownward();
				}
				
		
		for(Bullet bullet : bullets)
			bullet.moveUpward();
		
	}
	
	private void resetUserInputText() {
		input.delete(0, input.length());
	}
	
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		if(bgImage != null)
			g.drawImage(bgImage, 0, 0, null);
		
		if(gameOver)
		{
			timer.stop();
			g.drawString("GAME OVER", (int) getBounds().getMaxX()/2, (int) getBounds().getMaxY()/2);
		} else {
		
			g.setColor(Color.BLACK);
			
			if (lastMessage != null) { g.drawString( lastMessage, 500, 300 ); }
		
			for(Question question : questionArr)
				if(question != null && question.isActive())
					g.drawString(question.getSelectedQuestion(), question.getX(), question.getY());
					
			g.drawString( input.toString(), 500, 200 );
	
			paintBase(g);
			paintScoreBoard(g);
		}
	}
	
}
