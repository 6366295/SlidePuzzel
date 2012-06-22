package com.multimedia.slidepuzzel;

public class Game{
	public enum Difficulty{
		EASY, NORMAL, HARD;
	}
	
	private int size;				// Field size (size x size)
	private Tile[] tiles;			// Tiles containing rgb data
	private Field curField; 		// Current field as seen by the player 
	private Difficulty difficulty;	// Difficulty for this game
	//TODO: add shuffle history
	
	public Game(Difficulty d, int s, int totalWidth){
		size = s;
		difficulty = d;
		curField = new Field(size);
		tiles = new Tile[size * size];
		
		for(int i = 0; i < 9; i++){
			tiles[i] = new Tile(totalWidth / size);
		}
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
}
