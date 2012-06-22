package com.multimedia.slidepuzzel;

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
    }
    
    public void gameActivity(View view) {
    	Intent intent = new Intent(this, gameActivity.class);
    	startActivity(intent);
    }
}