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
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);// ˢ�±���
        gl.glLoadIdentity();								 // ���õ�ǰ��ģ�͹۲����
        gl.glEnable(GL.GL_BLEND);
       
        
        pr.DisplayScene();
        pr.light(0, 10.0f, 10.0f, 1.0f);
        pr.paintplayer();
        pr.DrawGround();
        
        GLUT g = new GLUT();
  //      g.glutSolidCube(10);
        
        
        pr.paintWeaponcontrol();
        
        gl.glFlush();									// ���´���

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
        pr = new PaintReady(gl);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int w,
            int h) {
        // TODO Auto-generated method stub
    }

   

  
}

