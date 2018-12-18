package achieve;

import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.GL2;

import Abstract.bullet;
import Abstract.enemy;

import com.jogamp.opengl.util.gl2.GLUT;

public class GLRender implements GLEventListener{

    private float r;
    private final float MAP = 40.0f;
    boolean isspacepress;
    protected PaintReady pr;   
    public static boolean issingle=true;
    boolean change = false;
    float distance = -0.1f;

    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);// ˢ�±���
        gl.glLoadIdentity();// ���õ�ǰ��ģ�͹۲����  
        gl.glEnable(GL.GL_BLEND);
        pr.updata();
        pr.DisplayScene();
        pr.light(0, 10.0f, 10.0f, 1.0f);      
		pr.drawbullet();
        pr.paintplayer();
        if(issingle)
        pr.paintenemy();
        else
        pr.paintnetplayer();
        pr.DrawGround();
        pr.Drwaback();      
        pr.drawwave();        
       
      //  g.glutSolidCube(10);
        
        pr.paintWeaponcontrol();
        
        if(!change)this.changeview(gl);
        gl.glFlush();									// ���´���

        
    }
    public void changeview(GL2 gl){
    	float x = -0.045200218f;
		float y = -0.031399984f;
		float w_size = 4;
    	gl.glLoadIdentity();
    	gl.glEnable(GL.GL_BLEND);
    	distance-=2;
    	if(distance<-80)change=true;
    	gl.glColor3f(1, 1, 1);
    	gl.glLoadIdentity();
 		gl.glTranslatef(x, y, distance);
 		gl.glBegin(gl.GL_QUADS);
 		gl.glVertex2f(w_size, w_size);
 		gl.glVertex2f(w_size,-w_size);
 		gl.glVertex2f(-w_size, -w_size);
 		gl.glVertex2f(-w_size, w_size);
 		gl.glEnd();
    }
    public void dispose(GLAutoDrawable drawable) {

    }

    public void init(GLAutoDrawable drawable) {
        // TODO Auto-generated method stub

        GL2 gl = drawable.getGL().getGL2();      
        GLU glu = new GLU();
        gl.glViewport(0, 0, 800, 600);			// ����OpenGL�ӿڴ�С��
        gl.glMatrixMode(GL2.GL_PROJECTION);			// ���õ�ǰ����ΪͶӰ����
        gl.glLoadIdentity();						// ���õ�ǰָ���ľ���Ϊ��λ����
        glu.gluPerspective // ����͸��ͼ
                (45.0f, // ͸�ӽ�����Ϊ 45 ��
                (float) 800 / (float) 600, // ���ڵĿ���߱�
                0.1f, // ��Ұ͸�����:����1.0f
                3000.0f // ��Ұ͸�����:ʼ��0.1fԶ��1000.0f
                );
        // �������������ƣ���һ���������þ�ͷ��Ƕȣ��ڶ��������ǳ���ȣ�������Զ�����С�
        gl.glMatrixMode(GL2.GL_MODELVIEW);				// ���õ�ǰ����Ϊģ����ͼ����
        
        //gl.glLoadIdentity();
        pr = PaintReady.getPaintReady(gl);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int w,
            int h) {
        // TODO Auto-generated method stub
    }

   

  
}

