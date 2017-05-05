package test;

import java.io.IOException;

import org.junit.Test;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.GameFunctions;
import model.dynamicBoard;
import model.fileReader;

public class dynamicBoardTest {
	
	dynamicBoard board = new dynamicBoard(4);
	GameFunctions functions = new GameFunctions();
	fileReader reader = new fileReader();
	
	
	@Test
	public void testDynamicNextGeneration() { 

		try {
			reader.readGameBoardFromURL("http://student.cs.hioa.no/~s305456/test.lif", board);
		}
		catch(IOException ioe) {
			Alert alertbox = new Alert(AlertType.ERROR);
			alertbox.setTitle("Error");
			alertbox.setHeaderText("URL error!");
			alertbox.setContentText(ioe.getMessage());
			alertbox.showAndWait();
		}
		
	
	board.nextGen();
	org.junit.Assert.assertEquals(board.toString(),"0100001101100000");
	}

}
