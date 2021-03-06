package com.multimedia.slidepuzzel.gamelogic;

public class Field{
	/*
	 * Default 3x3: 
	 * 8 7 6
	 * 5 4 3
	 * 2 1 0
	 */
	private byte[][] tileIdx;		// Tiles index 0-8. The tile with index 0 is the empty one. 
	private byte nullX;					// X position of the blank tile (index 0)
	private byte nullY;					// Y position of the blank tile
	
	public Field(int dimension){
		tileIdx = new byte[dimension][dimension];
		
		// Fill with default
		byte i = (byte) (dimension * dimension - 1);
		for(int x = 0; x < dimension; x++){
			for(int y = 0; y < dimension; y++){
				tileIdx[x][y] = i--;	
				if(i < 0){
					nullX = (byte) x;
					nullY = (byte) y;
				}
			}
		}
	}
	
	/*
	 * Swap tile at index idx with the empty.
	 * It will swap even if it is not a valid move;
	 */
	public void swapTile(int x, int y){
		android.util.Log.d("DontCrash", "Swap to " + x + ", " + y + " from " + nullX + ", " + nullY);
		tileIdx[nullX][nullY] = tileIdx[x][y];
		tileIdx[x][y] = 0;
		nullX = (byte) x;
		nullY = (byte) y;
	}
	public int [][] getArray(){
            int newtiles [][] = new int [tileIdx.length][tileIdx.length];
            for(int y = 0;y<(int)tileIdx.length;y++){
            	for(int x = 0;x<(int)tileIdx.length;x++){
              	  newtiles[x][y]=(int)tileIdx[x][y];
            	}
            }
            return newtiles;
    	}
	
	/*
	 * This method checks if the tile at x,y can
	 * be swapped with the blank tile in a valid move.
	 */
	public boolean validSwap(int x, int y){
		if(x == nullX && y == nullY){
			// Swap blank tile with blank tile
			return false;
		}
		
		if(x >= tileIdx.length || x < 0 || y >= tileIdx.length || y < 0){
			// Out of bounds
			return false;
		}
		
		int d = Math.abs(nullX - x) + Math.abs(nullY - y);
		if(d != 1){
			// Invalid move: diagonal or tile between blank and the tile.
			return false;
		}
		
		return true;
	}
	
	public int getTileIdx(int x, int y){
		return tileIdx[x][y];
	}
	
	public int getNullX(){
		return nullX;
	}

	public int getNullY(){
		return nullY;
	}
	
	public boolean equals(Field d){
		if(d.getNullX() != nullX || d.getNullY() != nullY){
			return false;
		}
		
		for(int x = 0; x < tileIdx.length; x++){
			for(int y = 0; y < tileIdx.length; y++){
				if(d.getTileIdx(x, y) != tileIdx[x][y]){
					return false;
				}
			}
		}
		return true;
	}
}
