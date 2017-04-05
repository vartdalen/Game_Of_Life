package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

<<<<<<< HEAD

/**
 * Kalles i drawBoard for å tegne celler.
 * Double x, y: Verdier hentet fra forloop, multiplisert med størrelse for aa finne dimensjonene til en ny celle.
 */
=======
>>>>>>> dag
public class Cell {

	public double x;
	public double y;
	
<<<<<<< HEAD
=======
	
	
	
>>>>>>> dag
	public void draw(GraphicsContext gc, double size1, double size2, byte boardValue, Color[] colors) {
		gc.setFill(colors[boardValue]);
		gc.fillRect(x, y, size1, size2);
	}
	
<<<<<<< HEAD
=======
	
	

	
>>>>>>> dag
}
