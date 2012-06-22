package com.multimedia.slidepuzzel;

import android.app.Activity;
import android.os.Bundle;

public class GameActivity extends Activity {
    private CameraView mCameraView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        
        // get handles to the CameraView from XML
        mCameraView = (CameraView) findViewById(R.id.cameraView1);
        
        mCameraView.setActivity(this);
    }
}
