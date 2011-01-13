package librarytests;

import org.openkinect.processing.Kinect;

import processing.core.PApplet;

public class Kinecting extends PApplet {

	Kinect kinect;
	boolean depth = true;
	boolean rgb = false;
	boolean ir = true;

	public void setup() {
		size(1280,520);
		kinect = new Kinect(this);
		kinect.start();
		kinect.enableDepth(depth);
		kinect.enableRGB(rgb);
		kinect.enableIR(ir);
	}

	public void draw() {
		background(0);

		image(kinect.getVideoImage(),0,0);
		image(kinect.getDepthImage(),640,0);
		fill(255);
		text("RGB/IR FPS: " + kinect.getVideoFPS(),10,495);
		text("DEPTH FPS: " + kinect.getDepthFPS(),640,495);
		text("Press 'd' to enable/disable depth    Press 'r' to enable/disable rgb image   Press 'i' to enable/disable IR image  Framerate: " + frameRate,10,515);
	}

	public void keyPressed() {
		if (key == 'd') {
			depth = !depth;
			kinect.enableDepth(depth);
		} 
		else if (key == 'r') {
			rgb = !rgb;
			if (rgb) ir = false;
			kinect.enableRGB(rgb);
		}
		else if (key == 'i') {
			ir = !ir;
			if (ir) rgb = false;
			kinect.enableIR(ir);
		}
	}

	public void stop() {
		kinect.quit();
		super.stop();
	}

	public static void main(String _args[]) {
		PApplet.main(new String[] { librarytests.Kinecting.class.getName() });
	}



}
