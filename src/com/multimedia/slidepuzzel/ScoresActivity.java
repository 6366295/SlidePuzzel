package com.multimedia.slidepuzzel;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class ScoresActivity extends TabActivity {
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
			
		TabHost tabHost = getTabHost();
 
		TabSpec scoreseasy = tabHost.newTabSpec("Easy");
		scoreseasy.setIndicator("Easy");
		Intent easyIntent = new Intent(this, ScoresActivity2.class);
		scoreseasy.setContent(easyIntent);
 
		TabSpec scoresnormal = tabHost.newTabSpec("Normal");
		scoresnormal.setIndicator("Normal");
		Intent normalIntent = new Intent(this, ScoresActivity3.class);
		scoresnormal.setContent(normalIntent);
 
		TabSpec scoreshard = tabHost.newTabSpec("Hard");
		scoreshard.setIndicator("Hard");
		Intent hardIntent = new Intent(this, ScoresActivity4.class);
		scoreshard.setContent(hardIntent);
 
		tabHost.addTab(scoreseasy);
		tabHost.addTab(scoresnormal);
		tabHost.addTab(scoreshard);
		}
}
