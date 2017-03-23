package model;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

import java.util.List;
import java.util.Random;
import javafx.scene.paint.Color;

public class cellinsp {
	
		public double x;
		public double y;
		
		public int cols;
		public int rows;
		
	public static int initialBoard[][]; //= new int [][] 	{
//														
//														{0,0,0,0,0},
//														{0,0,0,0,0},
//														{0,0,0,0,0},
//														{0,0,0,0,0},
//														{0,0,0,0,0},
//														
//													}
		
		
		
		public cellinsp() {
			
			cols = initialBoard.length;
			rows = initialBoard.length;
			
			initialBoard = new int [cols][rows];
			
			init();
			
		}
		
		public void init() {
			
			for (int i=1; i<cols-1; i++) {
				
				for(int j=1; j<rows-1; j++) {
					
					int random = (int) (Math.random() * 1+0);
					
					initialBoard[i][j] = random;
					
				}
				
			}
			
		}
		
		public void generate() {
			
			int[][] next = new int[cols][rows];
			
			for(int x = 1; x < cols-1; x++) {
				
				for(int y = 1; y < rows-1; y++) {
					
					int neighbours = 0;
					
					for(int i = -1; i <= 1; i++) {
						
						for(int j = -1; j <= 1; j++) {
							
							neighbours += initialBoard[x+i][y+j];
							
						}
						
					}
					
					neighbours -= initialBoard[x][y];
					
					if 		((initialBoard[x][y] == 1) && (neighbours < 2)) next [x][y] = 0;
					else if ((initialBoard[x][y] == 1) && (neighbours > 3)) next [x][y] = 0;
					else if ((initialBoard[x][y] == 0) && (neighbours == 3))next [x][y] = 1;
					else 													next [x][y] = initialBoard[x][y];
					
				}
				
			}
			
		}
		
	}
