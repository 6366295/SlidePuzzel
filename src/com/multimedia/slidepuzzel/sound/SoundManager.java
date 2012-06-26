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
	
	private boolean loadSwap;
	private boolean loadRotate;
	private boolean loadWin;
	
	
	private SoundPool pool;
	
	public SoundManager(Context c){
		pool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
		pool.setOnLoadCompleteListener(this);
		
		loadSwap = false;
		loadRotate = false;
		loadWin = false;
		
		swap = pool.load(c, R.raw.swap, 1);
		rotate = pool.load(c, R.raw.rotate, 2);
		win = pool.load(c, R.raw.win, 3);
	}
	
	public int playSound(int soundId){
		if(soundId == swap && !loadSwap
				|| (soundId == rotate && !loadRotate)
				|| (soundId == win && !loadWin)
			){
			return -1;
		}
		return pool.play(soundId, 0.5f, 0.5f, 1, 0, 1f);
	}
	
	public void onLoadComplete(SoundPool pool, int soundId, int status){
		if(status != 0){
			Log.e("SoundManager", "Sound " + soundId + " gave status " + status);
			return;
		}
		
		if(soundId == swap){
			loadSwap = true;
		}else if(soundId == rotate){
			loadRotate = true;
		}else if(soundId == win){
			loadWin = true;
		}
	}
	
	
}
