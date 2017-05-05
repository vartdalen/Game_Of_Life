package test;

import java.io.IOException;
import org.junit.Test;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.GameFunctions;
import model.fileReader;
import model.staticBoard;

public class staticBoardTest {
	
	GameFunctions gol = new GameFunctions();
	staticBoard board = new staticBoard();
	fileReader reader = new fileReader();
	
	private byte[][] testBoard = {
			
            { 0, 0, 0, 0 },
            { 0, 1, 1, 0 },
            { 0, 1, 1, 0 },
            { 0, 0, 0, 0 },
            { 0, 0, 0, 0 },
		};
	
	
	
	
	@Test
	public void testNextGeneration() { 

	board.setBoard(testBoard);
	board.nextGen(); 
	org.junit.Assert.assertEquals(board.toString(),"00000110011000000000");

	}
	

	@Test
	public void testReadGameBoard() {

//		File file = new File("/Users/DagBrede/Desktop/test.lif");
		try {
			reader.readGameBoardFromURL("http://student.cs.hioa.no/~s305456/test.lif", board);
		} 
		catch (IOException ioe) {
			Alert alertbox = new Alert(AlertType.ERROR);
			alertbox.setTitle("Error");
			alertbox.setHeaderText("File error!");
			alertbox.setContentText(ioe.getMessage());
			alertbox.showAndWait();
		}
		
		org.junit.Assert.assertEquals(board.getBoundingBoxPattern(),"001101011");
		
	}
	
	
	
	
}
	
	


