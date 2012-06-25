package com.multimedia.slidepuzzel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        
        Button back = (Button) findViewById(R.id.back5);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });
        
        Button newgame = (Button) findViewById(R.id.newgame);
        newgame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(WinActivity.this, GameActivity.class);
            	startActivity(intent);
            }
        });
    }
}
