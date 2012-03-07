package com.asubtlemind.learning.math.thirdgrade.tests;

import junit.framework.JUnit4TestAdapter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.asubtlemind.learning.math.thirdgrade.Question;
import com.asubtlemind.learning.math.thirdgrade.Shooter;


public class JUShooter {
	
	private static Shooter shooter = new Shooter();
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Test 
	public void confirmMoveRightAndLeftLeavePositionZero() {
		int initialX = shooter.getX();
		shooter.moveShooterRight();
		assert(shooter.getX() != initialX);
		shooter.moveShooterLeft();
		int XafterLeftAndRightMoves = shooter.getX();
		assert(initialX == XafterLeftAndRightMoves);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(JUShooter.class);
	}
}
