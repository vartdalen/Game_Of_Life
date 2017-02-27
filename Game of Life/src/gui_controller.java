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

	private byte [][] initialBoard = { //Mønster
			{1,0,0,1}, 
			{0,1,1,0}, 
			{0,1,1,0}, 
			{1,0,0,1}
		   };
	
	private byte [][] board = cloneByteArray(initialBoard);
	private double size = 70.0; //Cell-size
	/*
	 * Vi bruker verdiene i Board til å avgjøre hvilken farge som blir brukt. Verdi 0 i Board = White fordi White 
	 * er plassert på index [0] i "colors" tabellen under; Verdi 1 i board = black fordi den er plassert på index[1].
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
	 * Utfører spill-regelen: Hvis celle er "død"- vekk den til live (ved å gi den "1" verdi), og drep celle dersom
	 * den er i live (ved å sette verdien dens til 0).
	 */
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
	
	private float getAnimationSpeed() {
		return (float) slider_speed.getValue();
	}
	
	public void initialize(java.net.URL location,
            java.util.ResourceBundle resources) {
		slider_size.setValue(5.0);
		slider_speed.setValue((slider_speed.getMin() + slider_speed.getMax()) / 2);
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
	}
	
//	private void draw() { //tegner opp mønster
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
				double x = j*size;
				double y = i*size;
				byte boardValue = board[i][j];
				gc.setFill(colors[boardValue]); 
				gc.fillRect(x, y, size, size);
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
		
		drawBoard(); //tegner en gang først for å få riktig start.
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
		gc.clearRect(0, 0, board.length * size, board[0].length * size);
	}
	
	public void clearBtnClicked(ActionEvent e) {
		/*
		 * fjerne eksisterende celler -> blanke ark.
		 * */
		clearCanvas();
		board = cloneByteArray(initialBoard);
	}
	
	public void importBtnClicked(ActionEvent e) {
		/*
		 * Kunne importere andre mønstre
		 * */
	}
}
