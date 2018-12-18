package achieve;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;
import javax.media.opengl.*;

import Abstract.modelpocket;

import com.jogamp.opengl.util.FPSAnimator;

import javax.media.opengl.awt.GLCanvas;


/**
 * @��ӭ�������IT���Ϳռ�
 * ����������<p>
 *
 *����GLRender�࣬����listener����
 * ����FPSAnimator�����߳�
 *
 */
public class playground extends JFrame {

    public  GLRender listener;
    public static modelpocket m_pocket;
    EventListener action;
    static FPSAnimator animator = null;


    
    
    /**���췽��<p>
     *
     * ���ô����С<p>
     * ��ӹر��¼�<p>
     * ����GLCapabilities��<p>
     * ����GLCanvas�࣬����<p>
     * GLCanvas�������GLEventListener<p>
     * ���������GLCanvas����<p>
     * ΪGLCanvas����ʵ����FPSAnimator�����߳�<p>
     * ���д���<p>
     *
     */
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
        GLCapabilities glcaps = new GLCapabilities(null);
        GLCanvas canvas = new GLCanvas(glcaps);
 
        listener = new GLRender();                
        canvas.addGLEventListener(listener);
        action = new  EventListener();
        this.addKeyListener(action);
        getContentPane().add(canvas, BorderLayout.CENTER);
        animator = new FPSAnimator(canvas, 60, true);
        centerWindow(this);
//        this.setVisible(true);
//        animator.start();


    }

    /** ���д���*/
    public void centerWindow(Component frame) { // ���д���
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

    public static void main(String[] args) {
        final playground app = new playground();
        // ��ʾ����
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                app.setVisible(true);
            }
        });
        // �����߳̿�ʼ
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                animator.start();
            }
        });
    }
}
