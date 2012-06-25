package com.multimedia.slidepuzzel;

import com.multimedia.slidepuzzel.application.SharedApplication;
import com.multimedia.slidepuzzel.data.HighscoreEntry;
import com.multimedia.slidepuzzel.data.Settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WinActivity extends Activity {

	private SharedApplication app;
	private HighscoreEntry h;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		int totaltime1, totaltime2, sec, min;
		min = 0;
        app = (SharedApplication) getApplication();
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win);
        
        Intent sender=getIntent();
        totaltime1 =sender.getExtras().getInt("time");
        totaltime2 = totaltime1;
        sec = totaltime1 %60;
        while(totaltime1 > 60){
        	totaltime1 = totaltime1 - 60;
        	min++;
        }
        
        TextView wintext = (TextView) findViewById(R.id.wintext);
        if(sec < 10)
        	wintext.setText("Time: " + min + ":0" + sec );
        else
        	wintext.setText("Time: " + min + ":" + sec );
        
        Button back = (Button) findViewById(R.id.back5);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	EditText et = (EditText) findViewById(R.id.entername);
                String theText = et.getText().toString();
            	h.setName(theText);
                app.dataManager.insertHighscore(h);
                finish();
            }
        });
        
        Button newgame = (Button) findViewById(R.id.newgame);
        newgame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	EditText et = (EditText) findViewById(R.id.entername);
                String theText = et.getText().toString();
            	h.setName(theText);
                app.dataManager.insertHighscore(h);
                finish();
                Intent intent = new Intent(WinActivity.this, GameActivity.class);
            	startActivity(intent);
            }
        });
        
        EditText et = (EditText) findViewById(R.id.entername);
        String theText = et.getText().toString();
        
        h = new HighscoreEntry();
        Settings s = new Settings();
        s.setDifficulty(app.diff);
        s.setSize(app.size);
		h.setSettings(s);
        h.setTime(totaltime2);
       
    	//Log.d("Highscore", "Score Added, NAME: " + theText + " TIME: " + totaltime2);
    	//app.dataManager.insertHighscore(h);
        
    }
	
    public void newGame(View view) {
    	Intent intent = new Intent(this, GameActivity.class);
    	startActivity(intent);
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
}
