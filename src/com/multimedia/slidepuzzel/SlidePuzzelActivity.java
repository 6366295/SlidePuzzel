package com.multimedia.slidepuzzel;

import com.multimedia.slidepuzzel.application.SharedApplication;
import com.multimedia.slidepuzzel.data.DataManager;
import com.multimedia.slidepuzzel.data.Settings;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

public class SlidePuzzelActivity extends Activity {	
	private SharedApplication app;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		app = (SharedApplication) getApplication();
		app.dataManager = new DataManager(getBaseContext());
		Settings settings = app.dataManager.getSettings();
		app.diff = settings.getDifficulty();
		app.size = settings.getSize();
		app.mode = settings.getMode();
	}
	
	public void gameActivity(View view) {
		if(app.mode == Settings.MODE_IMAGE){
			Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
			photoPickerIntent.setType("image/*");
			startActivityForResult(photoPickerIntent, 1);
		}else{
			Intent intent = new Intent(this, GameActivity.class);
			startActivity(intent);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent){
		super.onActivityResult(requestCode, resultCode, intent);
		if(resultCode == RESULT_OK){
			Uri photoUri = intent.getData();
			
			if(photoUri != null){
				android.util.Log.d("Picker", photoUri.toString());
				/*try{
					Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUri);
				}catch (Exception e){
					e.printStackTrace();
				}*/
			}
		}
		
		Intent i = new Intent(this, GameActivity.class);
		startActivity(i);
	}
	
	public void settingsActivity(View view) {
		Intent intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
	}
	
	public void scoresActivity(View view) {
		Intent intent = new Intent(this, ScoresActivity.class);
		startActivity(intent);
	}
	
	public void howtoActivity(View view) {
		Intent intent = new Intent(this, HowtoActivity.class);
		startActivity(intent);
	}
}