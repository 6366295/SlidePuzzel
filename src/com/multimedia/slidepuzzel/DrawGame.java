package com.multimedia.slidepuzzel;


import com.multimedia.slidepuzzel.gamelogic.Game;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Camera.Size;
import android.util.Log;

public class DrawGame{
	public Size imageSize;

	private Game game;
	private int tileSize;
	private int[] rgb;			// the array of integers
	private Bitmap bitmap;
	private Paint p;
	private Rect[][] defaultRect;

	public DrawGame(Activity activity){
		game = ((GameActivity) activity).getGame();
		game.getField().swapTile(2, 1);
		p = new Paint();
	}

	public void imageReceived(byte[] data) {
		// Allocate the image as an array of integers if needed.
		// Then, decode the raw image data in YUV420SP format into a red-green-blue array (rgb array)
		// Note that per pixel the RGB values are packed into an integer. See the methods r(), g() and b().
		
		if(rgb == null){
			int arraySize = imageSize.width*imageSize.height;
			rgb = new int[arraySize];
			tileSize = imageSize.height / game.getSize();
			Log.d("Tilesize", "" + tileSize + " height: " + imageSize.height + " width: " + imageSize.width);
		
			defaultRect = new Rect[game.getSize()][game.getSize()];
			
			for(int x = 0; x < game.getSize(); x++){
				for(int y = 0; y < game.getSize(); y++){
					defaultRect[x][y] = new Rect(
							(x * tileSize), (y * tileSize), 
							((x + 1) * tileSize), ((y + 1) * tileSize)
						);
					
					game.setTile(defaultRect[x][y], game.getDefaultField().getTileIdx(x, y));
				}
			}
		}
		decodeYUV420SP(rgb, data);
		
		// Create bitmap
		// TODO reuse bitmap
		bitmap = Bitmap.createBitmap(rgb, imageSize.width, imageSize.height, Bitmap.Config.ARGB_8888);
	}

	public void draw(Canvas c) {
		p.setColor(combine(0, 0, 0));
		
		//float scalefactor = (float) c.getHeight() / (float) (game.getSize() * tileSize);
		//c.scale(scalefactor, scalefactor, 0, 0);
		
		Rect t;
		int tIdx;
		for(int x = 0; x < game.getSize(); x++){
			for(int y = 0; y < game.getSize(); y++){
				tIdx = game.getField().getTileIdx(x, y);
				
				if(tIdx == 0){
					c.drawRect(defaultRect[x][y], p);
					continue;
				}
				t = game.getTile(tIdx);
				// Draw rectangle t of the bitmap into rectangle defaultRectangle[x][y]
				c.drawBitmap(bitmap, t, defaultRect[x][y], null);		
				
				// Draw surrounding lines
				c.drawLine((x * tileSize), (y * tileSize), ((x + 1) * tileSize), (y * tileSize), p);
				c.drawLine((x * tileSize), ((y + 1) * tileSize), ((x + 1) * tileSize), ((y + 1) * tileSize), p);
				
				c.drawLine((x * tileSize), (y * tileSize), (x * tileSize), ((y + 1) * tileSize), p);
				c.drawLine(((x + 1) * tileSize), (y * tileSize), ((x + 1) * tileSize), ((y + 1) * tileSize), p);
				
			}
		}
	}

	/*
	 * Below are some convenience methods,
	 * like grabbing colors and decoding.
	 */
    
	/*// Extract the red element from the given color
    private int r(int rgb) {
    	return (rgb & 0xff0000) >> 16;
    }

	// Extract the green element from the given color
    private int g(int rgb) {
    	return (rgb & 0x00ff00) >> 8;
    }

	// Extract the blue element from the given color
    private int b(int rgb) {
    	return (rgb & 0x0000ff);
    }*/
    
    // Combine red, green and blue into a single color int
    private int combine(int r, int g, int b) {
    	 return 0xff000000 | (r << 16) | (g << 8) | b;
    }
    
    /*
     * Decode the incoming data (YUV format) to a red-green-blue format
     */
	private void decodeYUV420SP(int[] rgb, byte[] yuv420sp) {
		final int width = imageSize.width;
		final int height = imageSize.height;
    	final int frameSize = width * height;
    	
    	for (int j = 0, yp = 0; j < height; j++) {
    		int uvp = frameSize + (j >> 1) * width, u = 0, v = 0;
    		for (int i = 0; i < width; i++, yp++) {
    			int y = (0xff & ((int) yuv420sp[yp])) - 16;
    			if (y < 0) y = 0;
    			if ((i & 1) == 0) {
    				v = (0xff & yuv420sp[uvp++]) - 128;
    				u = (0xff & yuv420sp[uvp++]) - 128;
    			}
    			
    			int y1192 = 1192 * y;
    			int r = (y1192 + 1634 * v);
    			int g = (y1192 - 833 * v - 400 * u);
    			int b = (y1192 + 2066 * u);
    			
    			if (r < 0) r = 0; else if (r > 262143) r = 262143;
    			if (g < 0) g = 0; else if (g > 262143) g = 262143;
    			if (b < 0) b = 0; else if (b > 262143) b = 262143;
    			
    			rgb[yp] = 0xff000000 | ((r << 6) & 0xff0000) | ((g >> 2) & 0xff00) | ((b >> 10) & 0xff);
    		}
    	}
    }
}