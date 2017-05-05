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
/**
 * Denne klassen inneholder programmene som står for lesing av inisiellverdier til boardet.
 */
public class fileReader extends java.io.Reader {
	
	/**
	 * Funksjonen tillater innlasting av GameOfLife-monstre av .lif format, i fra disk.
	 * @param file - Brettfil på harddisk i .lif format.
	 * @param board - Brettet hvor filen blir lastet inn.
	 * @throws IOException - Kaster denne dersom det oppstår feil rundt fillesing eller filinnlasting.
	 * @throws PatternFormatException - Kaster denne dersom formatet er feil. 
	 */
	public void readGameBoardFromDisk(File file, Board board) throws IOException, IndexOutOfBoundsException {
		try {
			
			readGameBoard(new FileReader(file), new FileReader(file), board);
		}
		catch (IOException | PatternFormatException ex) {
			Alert alertbox = new Alert(AlertType.ERROR);
			alertbox.setTitle("Error");
			alertbox.setHeaderText("File error!");
			alertbox.setContentText(ex.getMessage());
			alertbox.showAndWait();
		}
	}

	/**
	 * Funksjonen tillater innlasting av GameOfLife-monstre av .lif format, i fra URL/internett.
	 * @param url - URL til hvor brett-filen ligger på internett.
	 * @param board - Brettet hvor filen blir lastet inn.
	 * @throws IOException - Kaster denne dersom det oppstår feil rundt fillesing eller filinnlasting.
	 * @throws PatternFormatException - Kaster denne dersom formatet er feil.
	 */
	public void readGameBoardFromURL(String url, Board board) throws IOException, IndexOutOfBoundsException {
		try {
			URL destination = new URL(url);
			URLConnection conn = destination.openConnection();
			URLConnection conn2 = destination.openConnection();
			readGameBoard(new InputStreamReader(conn.getInputStream()), new InputStreamReader(conn2.getInputStream()), board);
		}
		catch (IOException | PatternFormatException ex) {
			Alert alertbox = new Alert(AlertType.ERROR);
			alertbox.setTitle("Error");
			alertbox.setHeaderText("URL error!");
			alertbox.setContentText(ex.getMessage());
			alertbox.showAndWait();
		}
	}
	/**
	 * Brukes i de to andre read funksjonene til å lese av gridden.
	 * @param r 
	 * @param r2
	 * @param board
	 * @throws IOException
	 * @throws PatternFormatException
	 */
	public void readGameBoard(Reader r, Reader r2, Board board) throws IOException, PatternFormatException {
		Scanner testFile = new Scanner(r);
		if (!testFile.nextLine().equals("#Life 1.06")) {
			throw new PatternFormatException("Du lastet inn noe annet enn Life 1.06 format");
		}
		int lowest = 0;
		String[] numbers;
		String[] inputNumbers;
		while (testFile.hasNext()) {
			String testString = testFile.nextLine();
			testString = testString.replaceAll("[^-?0-9]+", " ");
			String pattern = "[0-9-]{1,4}[ ][0-9-]{1,4}";
			String pattern2 = "-?[0-9]\\d+";
			if (!testString.matches(pattern)){
				throw new PatternFormatException("Feil format på filen!");
			}
			numbers = testString.split(" ");
			int x = Integer.parseInt(numbers[0]);
			int y = Integer.parseInt(numbers[1]);
			if ((x < 0 || y < 0) && x < lowest || y < lowest ) {
				if (x < y) {
					lowest = x;
				}
				if (y < x) {
					lowest = y;
				}
			}
		}
		lowest = lowest*-1;
		Scanner inputFile = new Scanner(r2);
		inputFile.nextLine();
		while (inputFile.hasNext()) {
			String inputString = inputFile.nextLine();
			inputString = inputString.replaceAll("[^-?0-9]+", " "); 
			inputNumbers = inputString.split(" ");
			int x = Integer.parseInt(inputNumbers[0]);
			int y = Integer.parseInt(inputNumbers[1]);
			x = x + lowest;
			y = y + lowest;
			board.setCellState(x, y, true);
		}
	}
	
	
	
	@Override
	
	public int read(char[] cbuf, int off, int len) throws IOException {
		return 0;
	}

	@Override
	public void close() throws IOException {
	}
	
}
