package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


/**
 * Kalles i drawBoard for å tegne celler.
 * Double x, y: Verdier hentet fra forloop, multiplisert med størrelse for aa finne dimensjonene til en ny celle.
 */
public class Cell {

	public double x;
	public double y;
	
	public void draw(GraphicsContext gc, double size1, double size2, byte boardValue, Color[] colors) {
		gc.setFill(colors[boardValue]);
		gc.fillRect(x, y, size1, size2);
	}
	
}
