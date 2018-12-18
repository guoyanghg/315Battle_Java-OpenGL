package achieve;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;
import javax.media.opengl.*;

import Abstract.modelpocket;

import com.jogamp.opengl.util.FPSAnimator;

import javax.media.opengl.awt.GLCanvas;


public class playground extends JFrame {
    private static playground p1;
    public  GLRender listener;                        GLview listen_t;
    public static modelpocket m_pocket;
    EventListener action;
    public static FPSAnimator animator = null;
    //JPanel p = new JPanel();
    public static Client c;
    public static Server s=new Server();
    NetPanel pan= new NetPanel();
    public static StartPanel sp= new StartPanel();
    GLCanvas canvas;
    public static CardLayout ct= new CardLayout();


    
    
 
    public playground() {
        super("Battle");
        try {
			m_pocket = new modelpocket();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(ct);
        GLCapabilities glcaps = new GLCapabilities(null);
        canvas = new GLCanvas(glcaps);
        listener = new GLRender();                      listen_t = new GLview();      canvas.addGLEventListener(listen_t);    
     // canvas.addGLEventListener(listener);
        action = new  EventListener();
        canvas.addKeyListener(action);
        canvas.addMouseListener(action);
        canvas.addMouseMotionListener(action);
        canvas.addMouseWheelListener(action);
        getContentPane().add(sp,"startpanel");    
        getContentPane().add(pan,"netpanel");
        getContentPane().add(canvas,"gamepanel"); 
        //getContentPane().add(canvas);
        animator = new FPSAnimator(canvas, 60, true);
        centerWindow(this);
//        this.setVisible(true);
//        animator.start();


    }

    /** 居中窗体*/
    public void centerWindow(Component frame) { // 居中窗体
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        frame.setLocation((screenSize.width - frameSize.width) >> 1,
                (screenSize.height - frameSize.height) >> 1);

    }
    public static playground get() {
		if (p1 == null) {
			p1 = new playground();
		}
		return p1;
	}

    public void changelistener(int i){
    	if(i==0){
    		canvas.removeGLEventListener(listen_t);
    	    canvas.addGLEventListener(listener);
    	}
    	if(i==1){
    		canvas.removeGLEventListener(listener);
    		
    		canvas.addGLEventListener(listen_t);
    	}
    }
    public static void main(String[] args) {
        final playground app = playground.get();
        app.setVisible(true);
        animator.start();
    }     
}
