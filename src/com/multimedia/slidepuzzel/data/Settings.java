package com.multimedia.slidepuzzel.data;

/* Wrapper class for settings */
public class Settings{
	private int size;
	private String difficulty;
	private int mode;
	
	public int getSize(){
		return size;
	}
	public void setSize(int size){
		this.size = size;
	}
	public String getDifficulty(){
		return difficulty;
	}
	public void setDifficulty(String difficulty){
		this.difficulty = difficulty;
	}
	public void setMode(int mode){
		this.mode = mode;
	}
	public int getMode(){
		return mode;
	}
}
