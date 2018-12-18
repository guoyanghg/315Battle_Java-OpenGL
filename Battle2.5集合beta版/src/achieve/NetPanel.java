package achieve;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.jogamp.opengl.util.FPSAnimator;

public class NetPanel extends JPanel implements ActionListener{
	ImageIcon bg = new ImageIcon("image/bg.jpg");
	ImageIcon icon1 = new ImageIcon("image/zhuji.gif");
	ImageIcon icon2 = new ImageIcon("image/ip.gif");
	int count=0;
	MyListener1 l= new MyListener1();
	static JTextField jf= new JTextField(10);
	static JButton jb= new JButton();
	static JButton jb2= new JButton();
	static JButton jb3= new JButton();
	String IP;
	

	public NetPanel() {
		// TODO Auto-generated constructor stub
		
		this.setLayout(null);
		this.addMouseListener(l);
		this.addMouseMotionListener(l);
		jf.setBounds(500,490, 120,20);
		//jb.setBounds(100,100, 120,20);
		//jb2.setBounds(200,200, 120,20);
		//jb3.setBounds(300,300, 120,20);
		//jb.setActionCommand("create");
		//jb2.setActionCommand("connect");
		//jb3.setActionCommand("single");
		jb.setText("建立主机");
		jb2.setText("连接");
		jb3.setText("单机");	
		jb.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		this.add(jb);
		this.add(jb2);
		this.add(jb3);
		this.add(jf);
		this.requestFocus();
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		//g.drawImage(icon.getImage(),0, 0, this.getWidth(), this.getHeight(), null);
		//g.drawImage(icons.getImage(),500, 450, 120, 50, null);
		g.drawImage(bg.getImage(), 0, 0, 800, 600, null);
		g.drawImage(icon1.getImage(), 810-count, 450, 200, 20, null);
		g.drawImage(icon2.getImage(), 800-count, 490, 200, 20, null);
		if(800-count!=290)
		count=count+2;
		//g.drawString("请输入要连接的ip:", 500, 410);
		
		repaint();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("create")){
			 new ACthread().start();
			 System.out.println("建立服务器，等待连接中。。。");
		}
		if(e.getActionCommand().equals("connect")){	
			  ;
				try {
					if(jf.getText().equals("")||jf.getText()==null)
					playground.c= new Client(InetAddress.getLocalHost().getHostAddress(), 11500);
					else{
						IP = jf.getText().trim();
						playground.c= new Client(IP, 11500);
					}
					playground.c.connect();
					netplayer.get();
					System.out.println("尝试连接一次");
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
		}
		if(e.getActionCommand().equals("single")){
			 PaintReady.link = false;
			 playground.ct.show(playground.get().getContentPane(), "gamepanel");	
		}	
	}
	public class MyListener1 implements MouseMotionListener,MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			Point a = e.getPoint();

			if (a.x > 300 && a.x < 500 && a.y > 450 && a.y < 470) {
				 new ACthread().start();
				 System.out.println("建立服务器，等待连接中。。。");			
			} ;

			if (a.x > 290 && a.x < 490 && a.y > 490 && a.y < 510) {
				
				try {
					if(jf.getText().equals("")||jf.getText()==null)
					playground.c= new Client(InetAddress.getLocalHost().getHostAddress(), 11500);
					else{
						IP = jf.getText().trim();
						playground.c= new Client(IP, 11500);
					}
					playground.c.connect();
					netplayer.get();
					System.out.println("尝试连接一次");
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					

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
}
