package com.multimedia.slidepuzzel;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.multimedia.slidepuzzel.application.SharedApplication;
import com.multimedia.slidepuzzel.gamelogic.Game;
import com.multimedia.slidepuzzel.sound.SoundManager;

public class GameActivity extends Activity {
    private CameraView mCameraView;
    private Game game;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
       
        Button back = (Button) findViewById(R.id.back4);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });
        
        SharedApplication app = (SharedApplication) getApplication();
        if(app.diff.equals("EASY"))
        	game = new Game(Game.Difficulty.EASY, app.size, new SoundManager(getBaseContext()));
        	
        else if(app.diff.equals("NORMAL")){
        	game = new Game(Game.Difficulty.NORMAL, app.size, new SoundManager(getBaseContext()));
        	View b = findViewById(R.id.hint);
        	b.setVisibility(View.GONE);
        }
        else if(app.diff.equals("HARD")){
        	game = new Game(Game.Difficulty.HARD, app.size, new SoundManager(getBaseContext()));
        	View b = findViewById(R.id.hint);
        	b.setVisibility(View.GONE);
        }
        
        // get handles to the CameraView from XML
        mCameraView = (CameraView) findViewById(R.id.cameraView1);
        mCameraView.setActivity(this);
    }
   
    @Override
    protected void onStop() {
    	super.onStop();
    	finish();
    }
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	finish();
    }
    
    public Context gContext() {
    	return getApplicationContext();
    }
    
    @Override
    public void onPause(){
    	// Pause rotation thread
    	game.getRotation().onPause();
    	super.onPause();
    }
    
    @Override
    public void onResume(){
    	// Resume rotation thread
    	game.getRotation().onResume();
    	super.onResume();
    }
    
    public Game getGame(){
    	return game;
    }
}
