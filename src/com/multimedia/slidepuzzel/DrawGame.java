package com.multimedia.slidepuzzel;


import com.multimedia.slidepuzzel.gamelogic.Game;
import com.multimedia.slidepuzzel.gamelogic.Tile;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Camera.Size;

public class DrawGame{
	public Size imageSize;

	private Game game;
	private int tileSize;
	private int[] rgb;			// the array of integers
	private Paint p;

	public DrawGame(Activity activity){
		game = ((GameActivity) activity).getGame();
		game.getField().swapTile(0, 0);
		tileSize = game.getTile(0).getSize();
		p = new Paint();
	}

	public void imageReceived(byte[] data) {
		// Allocate the image as an array of integers if needed.
		// Then, decode the raw image data in YUV420SP format into a red-green-blue array (rgb array)
		// Note that per pixel the RGB values are packed into an integer. See the methods r(), g() and b().
		int arraySize = imageSize.width*imageSize.height;
		if(rgb == null)rgb = new int[arraySize];
		decodeYUV420SP(rgb, data);
		
		
		int x, y, tIdx, dx, dy;
		Tile t;
		for(int i = 0; i < rgb.length; i++){
			// x,y in total image
			x = i - (imageSize.width * (i / imageSize.width));
			y = i / imageSize.width;
			
			// x,y of tile
			dx = x / tileSize;
			dy = y / tileSize;
			
			if(dx >= game.getSize() || dy >= game.getSize()){
				continue;
			}
			tIdx = game.getField().getTileIdx(dx, dy);
			
			// x,y in tile
			x -= tileSize * dx;
			y -= tileSize * dy;
			
			t = game.getTile(tIdx);
			// Set tile rgb
			if(tIdx == 0){
				t.rgb[t.rgbIdx(x, y)] = combine(0, 255, 0);
			}else{
				t.rgb[t.rgbIdx(x, y)] = rgb[i];	
			}
		}
	}

	public void draw(Canvas c) {
		p.setColor(combine(0, 0, 0));
		
		float scalefactor = (float) c.getHeight() / (float) (game.getSize() * tileSize);
		c.scale(scalefactor, scalefactor, 0, 0);
		
		Tile t;
		for(int x = 0; x < game.getSize(); x++){
			for(int y = 0; y < game.getSize(); y++){
				t = game.getTile(game.getDefaultField().getTileIdx(x, y));
				c.drawBitmap(t.rgb, 0, tileSize, (x * tileSize), (y * tileSize), tileSize, tileSize, true, null);		
				
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
    
	// Extract the red element from the given color
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
    }
    
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