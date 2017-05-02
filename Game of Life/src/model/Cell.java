package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/**
 * Mal for cellene.
 * double x, y: Verdier hentet fra forloop, multiplisert med storrelse for aa finne dimensjonene til en ny celle.
 * void draw(): Kalles i drawBoard for aa tegne celler.
 */
public class Cell {
	public double x;
	public double y;
	
	public void draw(GraphicsContext gc, double size, byte boardValue, Color[] colors) {
		gc.setFill(colors[boardValue]);
		gc.fillRect(x, y, size, size);
		
	}
	
}
