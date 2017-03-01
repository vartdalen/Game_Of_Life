//import java.net.URL;
import java.util.ArrayList;
import java.util.List;
//import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.control.Button;
//import javafx.scene.control.ColorPicker;
//import javafx.scene.control.MenuBar;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class gui_controller implements Initializable {

	private byte [][] initialBoard = { //MÃ¸nster
			{1,0,0,1}, 
			{0,1,1,0}, 
			{0,1,1,0}, 
			{1,0,0,1}
		   };
	
	private byte [][] board = cloneByteArray(initialBoard);
	//private float size = 5000; //Cell-size
	/*
	 * Vi bruker verdiene i Board til Ã¥ avgjÃ¸re hvilken farge som blir brukt. Verdi 0 i Board = White fordi White 
	 * er plassert pÃ¥ index [0] i "colors" tabellen under; Verdi 1 i board = black fordi den er plassert pÃ¥ index[1].
	 * */
	private Color[] colors = new Color[] { Color.WHITE, Color.BLACK }; 
	private Timeline timeline;
	
//	private static class Point {
//		public double x,y;
//		public void draw(GraphicsContext gc, double size) {
//			gc.setFill(Color.BLACK);
//			gc.fillRect(x, y, size, size);
//		}
//	}
	
	private void applyRule() {
		flipValues();
//		addBlack();
	}
	
	/*
	 * UtfÃ¸rer spill-regelen: Hvis celle er "dÃ¸d"- vekk den til live (ved Ã¥ gi den "1" verdi), og drep celle dersom
	 * den er i live (ved Ã¥ sette verdien dens til 0).
	 */
	
	//prøvde å lage gol-regler ift naboer, fikk noen exception errors. regner med at det har noe å gjøre med at celler som befinner seg
	// i endene av gridden ikke finner naboer. eller at jeg må forandre loopen fra å søke gjennom samtlige celler i gridden og heller kun søke de
	// 8 nærmeste.
	
	private void flipValues() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 1) {
					
					board[i][j] = 0;
					
				} else if (board[i][j] == 0) {
					
					board[i][j] = 1;
					
				}
			}
		}
		
//		for (int i = 0; i < board.length; i++) {
//			for (int j = 0; j < board[i].length; j++) {
//				if (board[i-1][j-1] + board[i][j-1] + 
//					board[i+1][j-1] + board[i+1][j] +
//					board[i+1][j+1] + board[i][j+1] + 
//					board[i-1][j+1] + board[i-1][j] == 3) {
//					
//					board[i][j] = 1; //cellen blir født
//					
//				} else if 	(board[i-1][j-1] + board[i][j-1] + 
//							board[i+1][j-1] + board[i+1][j] +
//							board[i+1][j+1] + board[i][j+1] + 
//							board[i-1][j+1] + board[i-1][j] > 3) {
//					
//					board[i][j] = 0; //cellen dør av overpopulasjon
//					
//				} else if	(board[i-1][j-1] + board[i][j-1] + 
//							board[i+1][j-1] + board[i+1][j] +
//							board[i+1][j+1] + board[i][j+1] + 
//							board[i-1][j+1] + board[i-1][j] < 3) {
//					
//					board[i][j] = 0; // cellen dør av ensomhet
//					
//				}
//			}
//		}
		
		
		
	}
	
//	private void blackify() {
//		for (int i = 0; i < board.length; i++) {
//			for (int j = 0; j < board[i].length; j++) {
//				if (board[i][j] == 0) {
//					board[i][j] = 1;
//					return;
//				}
//			}
//		}
//	}
	
//	@FXML private Button btnStart;
//	@FXML private Button btnStop;
//	@FXML private Button btnClear;
//	@FXML private Button btnImport;
	@FXML private Slider slider_size;
	@FXML private Slider slider_speed;
	@FXML private Canvas gol_canvas;
//	private List<Point> plist;
	
	
	
	public void initialize(java.net.URL location,
            java.util.ResourceBundle resources) {
		slider_size.setValue(slider_size.getValue());
		slider_speed.setValue((slider_speed.getValue()));
		slider_speed.valueProperty().addListener(new ChangeListener<Number>(){

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (timeline == null) {
					return;
				}
				timeline.stop();
				timeline = createTimeline(newValue.floatValue());
				timeline.play();
			}});
//		plist = new ArrayList<Point>();
		drawBoard();
		clearCanvas(); //prøver å bli kvitt søppel som blir igjen i bakgrunnen.
		//dette gjør bare at funksjonen starter blankt.
	}
	
//	private void draw() { //tegner opp mÃ¸nster
//		GraphicsContext gc = gol_canvas.getGraphicsContext2D();
//		Point p = new Point();
//		
//		for(int i = 0; i<board.length; i++) {
//			for(int j = 0; j<board[i].length; j++){
//				if(board[i][j] == 1) {
//				p.y = i*size;
//				p.x = j*size;
//				p.draw(gc, size);
//				}
//			}
//		}
//		gc.strokeText("Hello canvas", 150, 450);
//		gc.clearRect(0, 0, gol_canvas.widthProperty().doubleValue(), gol_canvas.heightProperty().doubleValue());
//		for( Point p : plist) {
//			p.draw(gc, Color.RED,slider_size.getValue());
//		}
//	}
	
	private void drawBoard() {
		GraphicsContext gc = gol_canvas.getGraphicsContext2D();
		
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				double x = j*getCellSize();
				double y = i*getCellSize();
				byte boardValue = board[i][j];
				gc.setFill(colors[boardValue]); 
				gc.fillRect(x, y, getCellSize(), getCellSize());
			}
		}
	}
	
	
//	public void nextGeneration() {
//		for(int counter = 0; counter <= 3; counter++) {
//			if(counter == 1){
//				draw();
//			} else if (counter == 2) {
//				drawChange();
////				counter = 0;
////			} else if (counter == 3) {
////				System.exit(0);
//			}
//		}
//	}
	
	public void startBtnClicked(ActionEvent e) {
		if (timeline != null) {
			timeline.stop();
		}
		timeline = createTimeline(getAnimationSpeed());
		timeline.play();
		
		drawBoard(); //tegner en gang fÃ¸rst for Ã¥ fÃ¥ riktig start.
	}
	
	public Timeline createTimeline(float animationTime) {
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame frame = new KeyFrame(Duration.millis(animationTime), new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event) {
				applyRule();
				drawBoard();
			}});
		
		timeline.getKeyFrames().add(frame);
		return timeline;
	}
	
	public void stopBtnClicked(ActionEvent e) {
		if (timeline != null) {
			timeline.stop();
		}
	}
	
	byte[][] cloneByteArray(byte[][] orig) {
		byte[][] output = new byte[orig.length][orig[0].length];
		for(int i = 0; i < orig.length; i++) {
			for(int j = 0; j < orig[i].length; j++) {
				output[i][j] = orig[i][j];
			}
		}
		return output;
	}
	
	void clearCanvas() {
		GraphicsContext gc = gol_canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, board.length * slider_size.getMax(), board[0].length * slider_size.getMax());
	}
	
	public void clearBtnClicked(ActionEvent e) {
		/*
		 * fjerne eksisterende celler -> blanke ark.
		 * */
		clearCanvas();
		board = cloneByteArray(initialBoard);
	}
	
	public float getCellSize() {
		
		//forandrer lengde og høyde på cellene
		return (float)slider_size.getValue();
		
	}
	
	private float getAnimationSpeed() {
		return (float) slider_speed.getValue();
	}
	
	public void importBtnClicked(ActionEvent e) {
		/*
		 * Kunne importere andre mÃ¸nstre
		 * */
	}
}
