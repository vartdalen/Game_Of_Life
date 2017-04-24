package model;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class fileReader extends java.io.Reader {
	
	
	public void readGameBoardFromDisk(File file, Board board) throws IOException, PatternFormatException {
		try {
			readGameBoard(new FileReader(file), board);
		}
		catch (IOException ex) {
			Alert alertbox = new Alert(AlertType.ERROR);
			alertbox.setTitle("Error");
			alertbox.setHeaderText("File error!");
			alertbox.setContentText(ex.getMessage());
			alertbox.showAndWait();
		}
	}

	
	public void readGameBoardFromURL(String url, Board board) throws IOException, PatternFormatException {
		try {
			URL destination = new URL(url);
			URLConnection conn = destination.openConnection();
			readGameBoard(new InputStreamReader(conn.getInputStream()), board);
		}
		catch (IOException ex) {
			Alert alertbox = new Alert(AlertType.ERROR);
			alertbox.setTitle("Error");
			alertbox.setHeaderText("URL error!");
			alertbox.setContentText(ex.getMessage());
			alertbox.showAndWait();
		}
	}
	
	public void readGameBoard(Reader r, Board board) throws IOException {
		Scanner inFile = new Scanner(r);
//		String testString = "";
//		String pattern = "(\\d+.*?\\d+)";
		inFile.nextLine();
		while (inFile.hasNext()) {
			String testString = inFile.nextLine();
			int x = testString.charAt(0);
			int y = testString.charAt(2);
			x = Character.getNumericValue(x);
			y = Character.getNumericValue(y);
			board.setCellState(x, y, true);
		}
		inFile.close();
	}
	
	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		return 0;
	}

	@Override
	public void close() throws IOException {
	}
}
