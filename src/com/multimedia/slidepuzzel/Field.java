package com.multimedia.slidepuzzel;

public class Field{
	/*
	 * Default 3x3: 
	 * 8 7 6
	 * 5 4 3
	 * 2 1 0
	 */
	private byte[][] tileIdx;		// Tiles index 0-8. The tile with index 0 is the empty one. 
	
	public Field(int dimension){
		tileIdx = new byte[dimension][dimension];
		// Fill with default
	}
	
	/*
	 * Swap tile at index idx with the empty one if possible.
	 */
	public void swapTile(int idx){
		// TODO: Implement
	}
	
	public int getTileIdx(int x, int y){
		return tileIdx[x][y];
	}
}
