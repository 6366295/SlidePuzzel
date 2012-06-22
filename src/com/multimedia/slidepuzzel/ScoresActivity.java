package com.multimedia.slidepuzzel;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ScoresActivity extends Activity {
	   @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        setContentView(R.layout.scores);
	        
	        Button back = (Button) findViewById(R.id.back2);
	        back.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	                finish();
	            }
	        });
	   }
}
