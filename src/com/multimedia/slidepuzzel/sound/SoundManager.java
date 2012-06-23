package com.multimedia.slidepuzzel.sound;

import com.multimedia.slidepuzzel.R;

import android.content.Context;
import android.media.SoundPool;
import android.media.AudioManager;
import android.util.Log;

public class SoundManager implements SoundPool.OnLoadCompleteListener{
	public int swap;
	public int rotate;
	public int win;
	
	private SoundPool pool;
	
	// Build using getBaseContext()
	public SoundManager(Context c){
		pool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
		pool.setOnLoadCompleteListener(this);
		swap = pool.load(c, R.raw.swap, 1);
		rotate = pool.load(c, R.raw.rotate, 2);
		win = pool.load(c, R.raw.win, 3);
	}
	
	public int playSound(int soundId){
		return pool.play(soundId, 0.5f, 0.5f, 1, 0, 1f);
	}
	
	public void onLoadComplete(SoundPool pool, int soundId, int status){
		Log.d("SoundPool", "Sound " + soundId + " status " + status);
	}
	
	
}
