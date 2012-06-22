package com.multimedia.slidepuzzel;

import android.content.Context;
import android.media.SoundPool;
import android.media.AudioManager;

public class SoundManager{
	public int swap;
	public int rotate;
	public int win;
	
	private SoundPool pool;
	
	// Build using getBaseContext()
	public SoundManager(Context c){
		pool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
		swap = pool.load(c, R.raw.swap, 1);
		rotate = pool.load(c, R.raw.rotate, 1);
		win = pool.load(c, R.raw.win, 1);
	}
	
	public int playSound(int soundId){
		return pool.play(soundId, 1f, 1f, 1, 0, 1f);
	}
}
