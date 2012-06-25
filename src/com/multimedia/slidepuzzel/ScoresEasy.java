package com.multimedia.slidepuzzel;

import com.multimedia.slidepuzzel.application.SharedApplication;
import com.multimedia.slidepuzzel.data.HighscoreEntry;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ScoresEasy extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scores2);
        
        TableLayout layout = (TableLayout) findViewById(R.id.tableLayout1);
        
        HighscoreEntry[] entries = ((SharedApplication) getApplication()).dataManager.getHighscore("EASY", 5);
        for(int i = 0; i < entries.length; i++){
        	if(entries[i] == null)break;
        	Log.d("Highscore", "Add 1");
        	TableRow row = new TableRow(this);
        	
        	TextView id = new TextView(this);
        	TextView name = new TextView(this);
        	TextView time = new TextView(this);
        	
        	id.setText("" + (i + 1));
        	name.setText(entries[i].getName());
        	time.setText("" + entries[i].getTime());
        	
        	id.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 0.2f));
        	name.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 0.5f));
        	time.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 0.3f));
        	
        	row.addView(id);
        	row.addView(name);
        	row.addView(time);
        	
        	layout.addView(row, new TableLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        }
    }
}
