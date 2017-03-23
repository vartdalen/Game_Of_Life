package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import model.GameFunctions;

public class testClass {
	@Test
	public void testNextGeneration() { 
		byte[][] board = {
	
	             { 0, 0, 0, 0 },
	             { 0, 1, 1, 0 },
	             { 0, 1, 1, 0 },
	             { 0, 1, 0, 0 },
	             { 0, 0, 0, 0 },
	             { 0, 0, 0, 0 },
	             { 0, 0, 0, 0 },
	};
	GameFunctions gol = new GameFunctions();
	gol.setBoard(board);
	gol.nextGen(); 
	org.junit.Assert.assertEquals(gol.toString(),"0000011001100100000000000000");

	}
}
	
	


