package com.multimedia.slidepuzzel.gamelogic;

import android.os.SystemClock;

public class StopWatch{
	private long start;
	private long end;
	
	public StopWatch(){
		end = 0;
		start = 0;
	}
	
	public void start(){
		end = 0;
		start = SystemClock.currentThreadTimeMillis();
	}
	
	public long stop(){
		end = SystemClock.currentThreadTimeMillis();
		return end - start;
	}

	public long getTimeElapsed(){
		if(end == 0){
			end = SystemClock.currentThreadTimeMillis();
		}
		
		return end - start;
	}
}
