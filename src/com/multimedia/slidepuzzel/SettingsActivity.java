package com.multimedia.slidepuzzel;

import com.multimedia.slidepuzzel.application.SharedApplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends Activity {
	int s;
	   @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        setContentView(R.layout.settings);
	        
	        SharedApplication app = (SharedApplication) getApplication();
	        Log.d("SharedApp", app.settings);
	        
	        Button back = (Button) findViewById(R.id.back3);
	        back.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	                finish();
	            }
	        });
	        
	        RadioGroup rg=(RadioGroup)findViewById(R.id.radioGroup2);
	        rg.setOnCheckedChangeListener(new android.widget.RadioGroup.OnCheckedChangeListener()
	        {

			   public void onCheckedChanged(RadioGroup arg0, int arg1) {
			    // TODO Auto-generated method stub
			    RadioButton rb=(RadioButton)findViewById(arg1);
			    
				if(rb.getText().equals("4 x 4"))
			    	s = 4;
			    else
			    	s = 3;
			   }
	         
	        }
	        );
	        
	        
	   }
}

