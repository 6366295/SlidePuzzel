package com.multimedia.slidepuzzel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class WinActivity extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
		int totaltime, sec, min;
		min = 0;
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win);
        
        Intent sender=getIntent();
        totaltime =sender.getExtras().getInt("time");
        sec = totaltime %60;
        while(totaltime > 60){
        	totaltime = totaltime - 60;
        	min++;
        }
        
        TextView wintext = (TextView) findViewById(R.id.wintext);
        if(sec < 10)
        	wintext.setText("Time: " + min + ":0" + sec );
        else
        	wintext.setText("Time: " + min + ":" + sec );
    }
}
