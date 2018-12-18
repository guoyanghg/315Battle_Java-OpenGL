package achieve;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class StartPanel extends JPanel{
	ImageIcon bg = new ImageIcon("image/bg.jpg");
	ImageIcon icon1 = new ImageIcon("image/game.gif");
	ImageIcon icon2 = new ImageIcon("image/game2.gif");
	ImageIcon icon3 = new ImageIcon("image/single.gif");
	ImageIcon icon4 = new ImageIcon("image/multiplay.gif");
	File file = new File("menu.wav");
	MyListener1 l= new MyListener1();
	MyListener2 l2= new MyListener2();
	boolean select=false;
	boolean step2=false;
	int count=0;
	AudioInputStream Stream;
	AudioFormat format;
	DataLine.Info info;
	Clip clip;
	public StartPanel() {
		this.addMouseMotionListener(l);
		this.addMouseListener(l);
		try {
			Stream = AudioSystem.getAudioInputStream(file);
			
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		format = Stream.getFormat();
	
		info = new DataLine.Info(Clip.class, format);
	

		try {
			clip = (Clip) AudioSystem.getLine(info);
			
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			clip.open(Stream);
		

		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(bg.getImage(), 0, 0, 800, 600, null);
		if(!step2){
		if(!select)
		g.drawImage(icon2.getImage(), 300, 450, 200, 30, null);
		else
		g.drawImage(icon1.getImage(), 300, 450, 200, 30, null);
		}
		else{
		g.drawImage(icon3.getImage(), 800-count, 430, 200, 50, null);
		g.drawImage(icon4.getImage(), 810-count, 480, 200, 50, null);
		if(800-count!=290)
		count=count+2;
		
		}
		repaint();		
		
	}
	public class MyListener2 implements MouseMotionListener,MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			Point a = e.getPoint();

			if (a.x > 290 && a.x < 490 && a.y > 430 && a.y < 480) {
				 clip.setFramePosition(0);
				 clip.start();

				 PaintReady.link = false;
				 playground.ct.show(playground.get().getContentPane(), "gamepanel");	

			} 
			if (a.x > 300 && a.x < 500 && a.y > 480 && a.y < 530) {
				 clip.setFramePosition(0);
				 clip.start();

				
				 playground.ct.show(playground.get().getContentPane(), "netpanel");	

			} 
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	public class MyListener1 implements MouseMotionListener,MouseListener{
		public void mouseMoved(MouseEvent e) {
			Point a = e.getPoint();

			if (a.x > 300 && a.x < 500 && a.y > 450 && a.y < 480) {
				
				select = true;
				
			} else {
				select = false;
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			Point a = e.getPoint();

			if (a.x > 300 && a.x < 500 && a.y > 450 && a.y < 480) {
				clip.setFramePosition(0);
				clip.start();
				step2=true;
				playground.sp.removeMouseListener(l);
				playground.sp.removeMouseMotionListener(l);
				playground.sp.addMouseListener(l2);
				playground.sp.addMouseMotionListener(l2);
					

			} 
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		
	}

}
