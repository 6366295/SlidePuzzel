package com.multimedia.slidepuzzel.gamelogic;

public class StopWatch{
	private long start;
	private long end;
	
	public StopWatch(){
		end = 0;
		start = 0;
	}
	
	public void start(){
		end = 0;
		start = System.currentTimeMillis();
	}
	
	public long stop(){
		end = System.currentTimeMillis();
		return end - start;
	}

	public long getTimeElapsed(){
		if(end == 0){
			end = System.currentTimeMillis();
		}
		
		return end - start;
	}
}
