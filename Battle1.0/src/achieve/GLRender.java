package achieve;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.GL2;

import com.jogamp.opengl.util.gl2.GLUT;

public class GLRender implements GLEventListener{

    private float r;
    private final float MAP = 40.0f;
    boolean isspacepress;
    protected PaintReady pr;   
    
    

    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);// 刷新背景
        gl.glLoadIdentity();								 // 重置当前的模型观察矩阵
        gl.glEnable(GL.GL_BLEND);
       
        
        pr.DisplayScene();
        pr.light(0, 10.0f, 10.0f, 1.0f);
        pr.paintplayer();
        pr.DrawGround();
        
        GLUT g = new GLUT();
  //      g.glutSolidCube(10);
        
        
        pr.paintWeaponcontrol();
        
        gl.glFlush();									// 更新窗口

    }

    public void dispose(GLAutoDrawable drawable) {

    }

    public void init(GLAutoDrawable drawable) {
        // TODO Auto-generated method stub

        GL2 gl = drawable.getGL().getGL2();      
        GLU glu = new GLU();
        gl.glViewport(0, 0, 800, 600);			// 设置OpenGL视口大小。
        gl.glMatrixMode(GL2.GL_PROJECTION);			// 设置当前矩阵为投影矩阵。
        gl.glLoadIdentity();						// 重置当前指定的矩阵为单位矩阵
        glu.gluPerspective // 设置透视图
                (45.0f, // 透视角设置为 45 度
                (float) 800 / (float) 600, // 窗口的宽与高比
                0.1f, // 视野透视深度:近点1.0f
                3000.0f // 视野透视深度:始点0.1f远点1000.0f
                );
        // 这和照象机很类似，第一个参数设置镜头广角度，第二个参数是长宽比，后面是远近剪切。
        gl.glMatrixMode(GL2.GL_MODELVIEW);				// 设置当前矩阵为模型视图矩阵
        
        //gl.glLoadIdentity();
        pr = new PaintReady(gl);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int w,
            int h) {
        // TODO Auto-generated method stub
    }

   

  
}

