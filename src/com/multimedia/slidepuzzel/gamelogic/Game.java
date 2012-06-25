package com.multimedia.slidepuzzel.gamelogic;

import java.util.Random;

import com.multimedia.slidepuzzel.sound.SoundManager;

import android.graphics.Rect;

public class Game{
	public static final long ROTATE_DELAY = 10000;
	
	public enum Difficulty{
		EASY, NORMAL, HARD;
	}
	
	private int size;				// Field size (size x size)
	private Rect[] tiles;			// Tiles containing rgb data
	private Field curField; 		// Current field as seen by the player 
	private Field defField;			// Default field (solved)
	private Difficulty difficulty;	// Difficulty for this game
	private SoundManager sound;
	private StopWatch gameTimer;
	private boolean solved;
	private GameRotation rotation;
	//TODO: add shuffle history
	
	public Game(Difficulty d, int s, SoundManager sound){
		this.sound = sound;
		size = s;
		difficulty = d;
		curField = new Field(size);
		defField = new Field(size);
		tiles = new Rect[size * size];
		solved = false;
		gameTimer = new StopWatch();
		rotation = new GameRotation(this); 
		gameTimer.start();
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
	
	public SoundManager getSound(){
		return sound;
	}
	
	public boolean checkPuzzleSolved(){
		if(!solved && curField.equals(defField)){
			solved = true;
			gameTimer.stop();
		}
		
		return solved;
	}
	
	public boolean isSolved(){
		return solved;
	}
	
	public StopWatch getGameTime(){
		return gameTimer;
	}
	
	public GameRotation getRotation(){
		return rotation;
	}
	
	public Game shuffler(){
		boolean loop = true;
		boolean up, down, left, right;
		int rand;
		Game game = this;
		Random generator = new Random();
		
		while(loop){
			for(int i = 0; i < 10000; i ++){
				left = game.getField().validSwap(game.getField().getNullX() - 1, game.getField().getNullY());
				right = game.getField().validSwap(game.getField().getNullX() + 1, game.getField().getNullY());
				up = game.getField().validSwap(game.getField().getNullX(), game.getField().getNullY() - 1);
				down = game.getField().validSwap(game.getField().getNullX(), game.getField().getNullY() + 1);
				
				rand = generator.nextInt();
				rand = rand % 4;
				if(rand == 0 && left)
					game.getField().swapTile(game.getField().getNullX() - 1, game.getField().getNullY());
				if(rand == 1 && right)
					game.getField().swapTile(game.getField().getNullX() + 1, game.getField().getNullY());
				if(rand == 2 && up)
					game.getField().swapTile(game.getField().getNullX(), game.getField().getNullY() - 1);
				if(rand == 3 && down)
					game.getField().swapTile(game.getField().getNullX(), game.getField().getNullY() + 1);

			}
			if(!game.checkPuzzleSolved())
				loop = false;
		}
		
		return game;
	}
}
