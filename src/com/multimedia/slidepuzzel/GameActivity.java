package com.multimedia.slidepuzzel;

import com.multimedia.slidepuzzel.gamelogic.Game;
import com.multimedia.slidepuzzel.sound.SoundManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        game = new Game(Game.Difficulty.EASY, 4, new SoundManager(getBaseContext()));
        
        // get handles to the CameraView from XML
        mCameraView = (CameraView) findViewById(R.id.cameraView1);
        mCameraView.setActivity(this);
    }
    
    public Game getGame(){
    	return game;
    }
}
