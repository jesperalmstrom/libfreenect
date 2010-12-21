package org.openkinect.processing;

import java.nio.ByteBuffer;


import processing.core.PApplet;

public class RGBImage extends DepthImage {
	
	public RGBImage(PApplet p) {
		super(p);
	}
	
	public void data(ByteBuffer data) {
		
		//byte[] bytes = data.array();
		
		for(int y=0; y<img.height; y++)
		{
			for(int x=0; x<img.width; x++)
			{
				int offset = 3*(y*img.width+x);

                int r = data.get( offset+2 ) & 0xFF;
                int g = data.get( offset+1 ) & 0xFF;
                int b = data.get( offset+0 ) & 0xFF;

				int pixel = (0xFF) << 24
				| (b & 0xFF) << 16
				| (g & 0xFF) << 8
				| (r & 0xFF) << 0;
				img.pixels[x+img.width*y] = pixel;
			}
		}
		
		img.updatePixels();
		long now = System.currentTimeMillis();
		long passedTime = now-time;
		time = now;
		
		
		float currentFPS = 1.0f / (passedTime / 1000.0f);
		fps = PApplet.lerp(fps,currentFPS,0.1f);
		//System.out.println("RGB FRAMERATE: " + fps);
	}

}
