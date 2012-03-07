/**
 * 
 */
package com.asubtlemind.learning.math.thirdgrade.tests;


import junit.framework.JUnit4TestAdapter;

import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.asubtlemind.learning.math.thirdgrade.Question;

/**
 * @author mc40ag
 *
 */
public class JUQuestion {
	
	

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
	
	@Test 
	public void confirmQACountsMatch() {
		assert(Question.answers.length == Question.questions.length);
	}
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(JUQuestion.class);
	}

}
