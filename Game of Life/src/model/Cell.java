package model;

import java.awt.MouseInfo;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Cell {
		
//		@FXML public Slider slider_size;
		public double size = 50;
//		public double OVHj�rne,OHHj�rne,NHHj�rne,NVHj�rne;
		public double initialx,initialy;
		
		public void drawInitialCells(GraphicsContext gc) {

			gc.setFill(Color.PURPLE);
			gc.fillRect(initialx, initialy, size, size);
		}
			
	}
