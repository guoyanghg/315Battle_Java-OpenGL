package achieve;

import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import javax.swing.JApplet;

public class audio {

	public static audio ad;
	
	private AudioClip boom;
	private AudioClip fire;
	public static audio getaudio(){
		if(ad==null)
			try {
				ad = new audio();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return ad;
	}
	private audio() throws MalformedURLException{
		boom = JApplet.newAudioClip(new File("boom.wav").toURL());
		fire = JApplet.newAudioClip(new File("ice.wav").toURL());
	}
	
	public AudioClip getboom(){
		return boom;
	}
	public AudioClip getfire(){
		return fire;
	}
}
