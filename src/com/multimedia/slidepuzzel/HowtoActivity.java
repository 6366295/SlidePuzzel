package com.multimedia.slidepuzzel;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HowtoActivity extends Activity {
	   @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);

	        setContentView(R.layout.howto);
	        
	        Button back = (Button) findViewById(R.id.back1);
	        back.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	                finish();
	            }
	        });
	   }
}
