package com.multimedia.slidepuzzel;

import com.multimedia.slidepuzzel.R;
import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;

public class SlidePuzzelActivity extends Activity {

    private CameraView mCameraView;
    
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.GameActivity);
        
        // get handles to the CameraView from XML
        mCameraView = (CameraView) findViewById(R.id.cameraView1);
        
        mCameraView.setActivity(this);
    }
}