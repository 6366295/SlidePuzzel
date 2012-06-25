package com.multimedia.slidepuzzel;

import com.multimedia.slidepuzzel.application.SharedApplication;
import com.multimedia.slidepuzzel.data.HighscoreEntry;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ScoresEasy extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scores2);
        
        TableLayout layout = (TableLayout) findViewById(R.id.tableLayout1);
        
        LayoutInflater inflater = getLayoutInflater();
        
        HighscoreEntry[] entries = ((SharedApplication) getApplication()).dataManager.getHighscore("EASY", 5);
        for(int i = 0; i < entries.length; i++){
        	if(entries[i] == null)break;
        	Log.d("Highscore", "Add 1");
        	TableRow row = (TableRow)inflater.inflate(R.layout.tablerow, layout, false);
        	
        	TextView id = (TextView) row.findViewById(R.id.table_id);
            id.setText("" + (i + 1));

            TextView name = (TextView) row.findViewById(R.id.table_name);
            name.setText(entries[i].getName());
            
            TextView time = (TextView) row.findViewById(R.id.table_time);
            time.setText(entries[i].getTime() + "");
        	
        	layout.addView(row);
        }
    }
}
