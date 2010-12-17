package kinectprocessing;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

import org.openkinect.Context;
import org.openkinect.Device;
import org.openkinect.Image;

import processing.core.PApplet;
import processing.core.PImage;
import processing.kinect.DepthImage;
import processing.kinect.RGBImage;


public class TestingOpenKinectProcessing extends PApplet {
	Context context;
	Device device;
	int w = 640;
	int h = 480;

	RGBImage kimg;// = new KImage();
	DepthImage dimg;

	public void setup() {
		size(1280,480);
		context = Context.getContext();
		if(context.devices() < 1)
		{
			System.out.println("No Kinect devices found.");
		}
		device = context.getDevice(0);

		kimg = new RGBImage(this);
		dimg = new DepthImage(this);
		frameRate(30);
		device.color(kimg);
		//device.depth(dimg);

	}

	public void draw() {
		//println(frameRate);
		for (int i = 0; i < 2; i++) {
			boolean b = context.processEvents();
			System.out.println("Process: " + b);
		}
		image(kimg.img,0,0);
		image(dimg.img,640,0);
	}

	public void stop() {
		device.dispose();
		println("DISPOSING");
		super.stop();
	}


	public static void main(String _args[]) {
		PApplet.main(new String[] { kinectprocessing.TestingOpenKinectProcessing.class.getName() });
	}
}
