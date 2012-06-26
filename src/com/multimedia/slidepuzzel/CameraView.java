package com.multimedia.slidepuzzel;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraView extends SurfaceView implements SurfaceHolder.Callback{
	private Activity activity;
	private SurfaceHolder mHolder;		// The holder of this SurfaceView object
	private SurfaceView cameraShow; 	// The surface android draws on (required to get preview)
	private Parameters parameters;		// The camera settings
	private int creationPhase = 0;		// Keep track of number surfaces created
	private DrawGame drawControl;		// The objects that draws our game
	
	private Camera camera;
	
	public CameraView(Context context, AttributeSet attrs){
		super(context, attrs);

		// register our interest in hearing about changes to our surface
		mHolder = getHolder();
		mHolder.addCallback(this);
		
		setFocusable(true); // make sure we get key events(unneeded?)
		setKeepScreenOn(true); // Prevent phone from sleeping
	}
	
	public void openCamera(){
		camera = Camera.open();
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){
	}

	public void surfaceCreated(SurfaceHolder holder){
		if(++creationPhase == 2)finalInitialize();
	}
	
	public void surfaceDestroyed(SurfaceHolder holder){
		if(camera != null){
			camera.setPreviewCallback(null);
			camera.stopPreview();
			camera.release();
			camera = null;
		}
	}
		

	private void finalInitialize(){
		// Create our DrawCamera object
		drawControl = new DrawGame(activity);
		
		try{
			openCamera();

			///initialize the variables  
			parameters = camera.getParameters();   
			camera.setPreviewDisplay(cameraShow.getHolder());
			parameters.setPreviewFrameRate(10); // Not all phones listen to this
			List<Size> sizes = parameters.getSupportedPreviewSizes();
			
			// Find the preview resolution closest to our canvas width
			int goalh = getWidth();
			int goalw = getHeight();
			float lowestDistance = Float.MAX_VALUE; 
			// Grab the one closest to our preview size
			Size current = sizes.get(0);
			for(Size c : sizes){
				float distance = FloatMath.sqrt(
						(c.width - goalw) * (c.width - goalw)
						+ (c.height - goalh) * (c.height - goalh)
					);
				if(distance < lowestDistance){
					lowestDistance = distance;
					current = c;
				}
			}
			
			parameters.setPreviewSize(current.width, current.height);
			
			camera.setParameters(parameters);
			drawControl.imageSize = camera.getParameters().getPreviewSize(); 
			camera.setPreviewCallback(new PreviewFramer());

			camera.startPreview();
		}catch(RuntimeException e){
			Log.e("CameraView", "Camera not available", e);
		}catch(IOException e){
			Log.e("CameraView", "Can't start preview", e);
		}
	}

	public void setActivity(GameActivity activity){
		this.activity = activity;
		cameraShow =(SurfaceView) activity.findViewById(R.id.surfaceView1);
		// Type PUSH_BUFERS required, otherwise android will not draw
		cameraShow.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		cameraShow.getHolder().addCallback(this);
	}
	
	public boolean onTouchEvent(MotionEvent event){
		// Pass on touch event to our drawing control
		drawControl.onTouchEvent(event);
		return super.onTouchEvent(event);
	}
	
	public DrawGame getDrawControl() {
		return drawControl;
	}

	class PreviewFramer implements Camera.PreviewCallback{
		public void onPreviewFrame(byte[] data, Camera camera){
			if(mHolder == null){
				return;
			}

			if(creationPhase == 2){
				Canvas c = null;
				try{
					drawControl.imageReceived(data);
					
					synchronized(mHolder){
						c = mHolder.lockCanvas(null);
						drawControl.draw(c);
					}
				}finally{
					// do this in a finally so that if an exception is thrown
					// during the above, we don't leave the Surface in an
					// inconsistent state
					if(c != null){
						mHolder.unlockCanvasAndPost(c);
					}
				}
			}
		}
	}
}