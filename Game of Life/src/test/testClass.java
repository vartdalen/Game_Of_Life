package test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.GameFunctions;
import model.PatternFormatException;

public class testClass {
	private byte[][] board = {
			
            { 0, 0, 0, 0 },
            { 0, 1, 1, 0 },
            { 0, 1, 1, 0 },
            { 0, 1, 0, 0 },
            { 0, 0, 0, 0 },
            { 0, 0, 0, 0 },
            { 0, 0, 0, 0 },
		};
	
	
	
	@Test
	public void testNextGeneration() { 

	GameFunctions gol = new GameFunctions();
	gol.setBoard(board);
	gol.nextGen(); 
	org.junit.Assert.assertEquals(gol.toString(),"0000011001100100000000000000");

	}
	

	@Test
	public void testReadGameBoard() {
		GameFunctions gol = new GameFunctions();
		File file = new File("/Users/DagBrede/Desktop/test.lif");
		try {
			gol.readGameBoardFromDisk(file);
		} 
		catch (IOException | PatternFormatException ioe) {
			Alert alertbox = new Alert(AlertType.ERROR);
			alertbox.setTitle("Error");
			alertbox.setHeaderText("File error!");
			alertbox.setContentText(ioe.getMessage());
			alertbox.showAndWait();
		}
		
		org.junit.Assert.assertEquals(gol.getBoundingBoxPattern(),"001101011");
		
	}
	
	
	
	
}
	
	


