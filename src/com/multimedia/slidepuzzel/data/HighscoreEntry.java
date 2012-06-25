package com.multimedia.slidepuzzel.data;

public class HighscoreEntry {
	private Settings s;
	private int time;
	private String name;
	
	public Settings getSettings(){
		return s;
	}
	public void setSettings(Settings s){
		this.s = s;
	}
	public int getTime(){
		return time;
	}
	public void setTime(int time){
		this.time = time;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
}
