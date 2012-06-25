package com.multimedia.slidepuzzel;

import com.multimedia.slidepuzzel.application.SharedApplication;
import com.multimedia.slidepuzzel.data.DataManager;
import com.multimedia.slidepuzzel.data.Settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SlidePuzzelActivity extends Activity {    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        SharedApplication app = (SharedApplication) getApplication();
        app.dataManager = new DataManager(getBaseContext());
        Settings settings = app.dataManager.getSettings();
        app.diff = settings.getDifficulty();
        app.size = settings.getSize();
    }
    
    public void gameActivity(View view) {
    	Intent intent = new Intent(this, GameActivity.class);
    	startActivity(intent);
    }
    
    public void settingsActivity(View view) {
    	Intent intent = new Intent(this, SettingsActivity.class);
    	startActivity(intent);
    }
    
    public void scoresActivity(View view) {
    	Intent intent = new Intent(this, ScoresActivity.class);
    	startActivity(intent);
    }
    
    public void howtoActivity(View view) {
    	Intent intent = new Intent(this, HowtoActivity.class);
    	startActivity(intent);
    }
}