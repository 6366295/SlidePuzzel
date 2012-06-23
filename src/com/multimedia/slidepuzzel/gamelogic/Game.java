package com.multimedia.slidepuzzel.gamelogic;

import android.graphics.Rect;

public class Game{
	public enum Difficulty{
		EASY, NORMAL, HARD;
	}
	
	private int size;				// Field size (size x size)
	private Rect[] tiles;			// Tiles containing rgb data
	private Field curField; 		// Current field as seen by the player 
	private Field defField;
	private Difficulty difficulty;	// Difficulty for this game
	//TODO: add shuffle history
	
	public Game(Difficulty d, int s, int totalWidth){
		size = s;
		difficulty = d;
		curField = new Field(size);
		defField = new Field(size);
		tiles = new Rect[size * size];
	}
	
	public boolean useHints(){
		return (difficulty == Difficulty.EASY);
	}
	
	public boolean useRotations(){
		return (difficulty == Difficulty.HARD);
	}
	
	public Field getField(){
		return curField;
	}
	
	public Field getDefaultField(){
		return defField;
	}
	
	public Rect getTile(int i){
		return tiles[i];
	}
	
	public void setTile(Rect t, int i){
		tiles[i] = t;
	}
	
	public int getSize(){
		return size;
	}
}
