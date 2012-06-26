package com.multimedia.slidepuzzel;

import com.multimedia.slidepuzzel.application.SharedApplication;
import com.multimedia.slidepuzzel.data.HighscoreEntry;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ScoresEasy2 extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scores2);
		
		TableLayout layout = (TableLayout) findViewById(R.id.tableLayout1);
		
		LayoutInflater inflater = getLayoutInflater();
		
		HighscoreEntry[] entries = ((SharedApplication) getApplication()).dataManager.getHighscore("EASY", 5, "size = 4");
		for(int i = 0; i < entries.length; i++){
			if(entries[i] == null)break;
			int time = entries[i].getTime();
			int sec = time % 60;
			int min = time / 60;
			
			TableRow row = (TableRow)inflater.inflate(R.layout.tablerow, layout, false);
			
			TextView id = (TextView) row.findViewById(R.id.table_id);
			id.setText("" + (i + 1));

			TextView name = (TextView) row.findViewById(R.id.table_name);
			name.setText(entries[i].getName());
			
			TextView timeView = (TextView) row.findViewById(R.id.table_time);
			if(sec < 10)
				timeView.setText(min + ":0" + sec);
			else
				timeView.setText(min + ":" + sec);
			
			layout.addView(row);
		}
	}
}
