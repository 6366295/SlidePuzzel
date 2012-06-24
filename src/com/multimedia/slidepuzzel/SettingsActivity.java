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
	int s, diff;
	   @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        setContentView(R.layout.settings);
	        
	        SharedApplication app = (SharedApplication) getApplication();
	        
	        Button back = (Button) findViewById(R.id.back3);
	        back.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	                finish();
	            }
	        });
	        
	        Button reset = (Button) findViewById(R.id.reset);
	        reset.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	            	SharedApplication app = (SharedApplication) getApplication();
	            	
			    	RadioButton easy=(RadioButton)findViewById(R.id.easy);
		        	easy.setChecked(true);
		        	diff = 1;
		        	app.diff = 1;
		        	
			    	RadioButton three=(RadioButton)findViewById(R.id.three);
		        	three.setChecked(true);
		        	s = 3;
		        	app.size = 3;
	            }
	        });
	        
	        RadioGroup rg1=(RadioGroup)findViewById(R.id.radioGroup1);
	        if(app.diff == 1){
	        	diff = 1;
	        	rg1.check(R.id.easy);
	        }
	        else if(app.diff == 2){
	        	diff = 2;
	        	rg1.check(R.id.normal);
	        }
	        else if(app.diff == 3){
	        	diff = 3;
	        	rg1.check(R.id.hard);
	        }
	        
	        rg1.setOnCheckedChangeListener(new android.widget.RadioGroup.OnCheckedChangeListener()
	        {

			   public void onCheckedChanged(RadioGroup arg0, int arg1) {
			    // TODO Auto-generated method stub
				SharedApplication app = (SharedApplication) getApplication();
			    RadioButton rb=(RadioButton)findViewById(arg1);
			    
				if(rb.getText().equals("Easy")){
			    	diff = 1;
			    	app.diff = 1;
			    	RadioButton easy=(RadioButton)findViewById(R.id.easy);
		        	easy.setChecked(true);
				}
			    else if(rb.getText().equals("Normal")){
			    	diff = 2;
			    	app.diff = 2;
			    	RadioButton normal=(RadioButton)findViewById(R.id.normal);
			    	normal.setChecked(true);
			    }
			    else if(rb.getText().equals("Hard")){
			    	diff = 3;
			    	app.diff = 3;
			    	RadioButton hard=(RadioButton)findViewById(R.id.hard);
			    	hard.setChecked(true);
			    }
			   }
	         
	        }
	        );
	        
	        
	        
	        
	        
	        RadioGroup rg2=(RadioGroup)findViewById(R.id.radioGroup2);
	        if(app.size == 3){
	        	s = 3;
	        	rg2.check(R.id.three);
	        }
	        else if(app.size == 4){
	        	s = 4;
	        	rg2.check(R.id.four);
	        }
	        
	        rg2.setOnCheckedChangeListener(new android.widget.RadioGroup.OnCheckedChangeListener()
	        {

			   public void onCheckedChanged(RadioGroup arg0, int arg1) {
			    // TODO Auto-generated method stub
				SharedApplication app = (SharedApplication) getApplication();
			    RadioButton rb=(RadioButton)findViewById(arg1);
			    
				if(rb.getText().equals("4 x 4")){
			    	s = 4;
			    	app.size = 4;
			    	RadioButton four=(RadioButton)findViewById(R.id.four);
		        	four.setChecked(true);
				}
			    else{
			    	s = 3;
			    	app.size = 3;
			    	RadioButton three=(RadioButton)findViewById(R.id.three);
			    	three.setChecked(true);
			    }
			   }
	         
	        }
	        );
	        
	        
	   }
}

