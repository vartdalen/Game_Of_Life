package model;

public class gameFunctions {
	
	public byte [][] cloneArray(byte[][] initial) {
		
		byte[][] output = new byte[initial.length][initial[0].length];
			for (int i = 0; i < initial.length; i++) {
				for (int j = 0; j < initial[i].length; j++) {
					
					output[i][j] = initial[i][j];
					
				}
			}
		
		return output;
		
	}

}
