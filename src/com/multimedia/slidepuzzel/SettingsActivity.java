package com.multimedia.slidepuzzel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends Activity {
	   @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        setContentView(R.layout.settings);
	        
	        Button back = (Button) findViewById(R.id.back3);
	        back.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	                finish();
	            }
	        });
	   }
}

