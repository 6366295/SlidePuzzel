package com.multimedia.slidepuzzel.gamelogic;

public class Tile{
	public int[] rgb;
	
	private int size;
	
	public Tile(int s){
		size = s;
		rgb = new int[size * size];
	}
	
	public int rgbIdx(int x, int y){
		return y * size + x;
	}
	
	public int getSize(){
		return size;
	}
}
