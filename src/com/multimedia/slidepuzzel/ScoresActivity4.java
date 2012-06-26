package com.multimedia.slidepuzzel;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class ScoresActivity4 extends TabActivity {
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			
			setContentView(R.layout.scores10);
			
		TabHost tabHost = getTabHost();
 
		TabSpec three = tabHost.newTabSpec("3x3");
		three.setIndicator("3x3");
		Intent threeIntent = new Intent(this, ScoresHard.class);
		three.setContent(threeIntent);
 
		TabSpec four = tabHost.newTabSpec("4x4");
		four.setIndicator("4x4");
		Intent fourIntent = new Intent(this, ScoresHard2.class);
		four.setContent(fourIntent);
 
		tabHost.addTab(three);
		tabHost.addTab(four);
		}
}
