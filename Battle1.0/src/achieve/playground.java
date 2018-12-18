package achieve;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;
import javax.media.opengl.*;

import Abstract.modelpocket;

import com.jogamp.opengl.util.FPSAnimator;

import javax.media.opengl.awt.GLCanvas;


/**
 * @欢迎来到子瑜IT博客空间
 * 主程序窗体类<p>
 *
 *调用GLRender类，定义listener对象
 * 定义FPSAnimator动画线程
 *
 */
public class playground extends JFrame {

    public  GLRender listener;
    public static modelpocket m_pocket;
    EventListener action;
    static FPSAnimator animator = null;


    
    
    /**构造方法<p>
     *
     * 设置窗体大小<p>
     * 添加关闭事件<p>
     * 构造GLCapabilities类<p>
     * 构造GLCanvas类，对象化<p>
     * GLCanvas对象添加GLEventListener<p>
     * 给窗体添加GLCanvas对象<p>
     * 为GLCanvas对象实例化FPSAnimator动画线程<p>
     * 居中窗体<p>
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

    public static void main(String[] args) {
        final playground app = new playground();
        // 显示窗体
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                app.setVisible(true);
            }
        });
        // 动画线程开始
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                animator.start();
            }
        });
    }
}
