package com.multimedia.slidepuzzel;

import com.multimedia.slidepuzzel.gamelogic.Game;
import com.multimedia.slidepuzzel.sound.SoundManager;

import android.app.Activity;
import android.os.Bundle;

public class GameActivity extends Activity {
    private CameraView mCameraView;
    private Game game;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        
        game = new Game(Game.Difficulty.EASY, 4, new SoundManager(getBaseContext()));
        
        // get handles to the CameraView from XML
        mCameraView = (CameraView) findViewById(R.id.cameraView1);
        mCameraView.setActivity(this);
    }
    
    public Game getGame(){
    	return game;
    }
}
