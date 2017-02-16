package com.example.assignment2;

import static org.junit.Assert.*;

import org.junit.Test;

public class InputTest {

	@Test
	public void inputTest() {
		DatabaseHelper db = new DatabaseHelper(null);
		boolean result = db.insertData("first", "last", "12345", "passw");
		assertEquals(true, result);
	}
	
	@Test
	public void messageTest() {
		SignUpActivity test = new SignUpActivity();
		test.showMessage("title", "message");
	}

	
}
