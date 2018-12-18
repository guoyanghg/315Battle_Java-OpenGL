package achieve;

import Abstract.player;
import common.TextureReader;

import java.io.IOException;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import com.jogamp.opengl.util.gl2.GLUT;


//**��ͼ�λ�ͼ��*/
public class PaintReady {
	double jishu=0;           float yg[] = new float[4];
	float radar_rotate = 0;
	static float ht=0.1f,hb=-0.5f;
	static final int nof = 10;
	private final float MAP = 400.0f;
	
    int[] g_cactus = new int[1];               
    GLUquadric g_text;
    GL2 gl;
    
    
    double[] g_eye = new double[3];

    int eye_distance =10;
    GLU glu; 
    player p_test = playertest.get();
    Weaponcontrol Wc = Weaponcontrol.getWc();

    public PaintReady(GL2 gl) {
        this.gl = gl;
        glu = new GLU();
        gl.glShadeModel(GL2.GL_SMOOTH);              // Enable Smooth Shading
        gl.glClearColor(0.0f, 1.0f, 0.0f, 1.0f);    // Black Background
        gl.glClearDepth(1.0f);                      // Depth Buffer Setup
        gl.glEnable(GL.GL_DEPTH_TEST);							// Enables Depth Testing
        gl.glDepthFunc(GL.GL_LEQUAL);								// The Type Of Depth Testing To Do
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_FASTEST);	 // ������ϸ��͸������


        g_eye[0] = MAP;   //
        g_eye[2] = -MAP;  //


        g_text = glu.gluNewQuadric();

 /*     filename = new String[]{
                    "image//test.png","image//test.png"
                };

        textureArr = new int[filename.length+1];

        loadT8(filename, textureArr);*/  

        
    }

    boolean DisplayScene() {
        
        if (g_eye[0] < -(MAP * 2 - 20)) {
            g_eye[0] = -(MAP * 2 - 20); //�ӵ��X��������
        }
        if (g_eye[0] > (MAP * 2 - 20)) {
            g_eye[0] = (MAP * 2 - 20);
        }
        if (g_eye[2] < -(MAP * 2 - 20)) {
            g_eye[2] = -(MAP * 2 - 20); //�ӵ��Z��������
        }
        if (g_eye[2] > (MAP * 2 - 20)) {
            g_eye[2] = (MAP * 2 - 20);
        }

        g_eye[0] = p_test.x-this.eye_distance*Math.sin(p_test.g_Angle/180*Math.PI);
        g_eye[1] = p_test.y+2;
        g_eye[2] = p_test.z-this.eye_distance*Math.cos(p_test.g_Angle/180*Math.PI);

        //������ķ���

        glu.gluLookAt(g_eye[0], g_eye[1], g_eye[2],
                p_test.x,p_test.y+0.5,p_test.z,
                0.0, 1.0, 0.0);
        return true;
    }

    void paintplayer(){
    	p_test.draw(gl);
    }
    
    
    
    
    final float w_size = 0.001f;
	public float tx = 0,ty = 0;
	public float power = 1;
	final float power_max = 4f;
    void paintWeaponcontrol(){
       Wc.draw(gl);
    }
   
    void DrawGround() {
    	gl.glBlendFunc(gl.GL_SRC_ALPHA,gl.GL_ONE_MINUS_SRC_ALPHA);
        gl.glPushAttrib(GL2.GL_CURRENT_BIT);//����������ɫ��ʵ��
        gl.glPushMatrix();
        gl.glColor4f(0.0f, 0.0f, 1.0f,0.5f);//������ɫ
        gl.glTranslatef(-MAP,-2*MAP,MAP);		//ƽ̨�Ķ�λ
        int size0 = (int) (MAP * 2);
        gl.glLineWidth(1.0f);
        
        gl.glBegin(GL2.GL_QUAD_STRIP);// ���͹�����
		gl.glVertex3f(0.0f, 0.0f, 0.0f);// a0��
		gl.glVertex3f(0.0f, size0, 0.0f);// a1��
		gl.glVertex3f(size0, 0.0f, 0.0f);// b0��
		gl.glVertex3f(size0, size0, 0.0f);// b1��
		gl.glVertex3f(size0, 0.0f, -size0);// c0��
		gl.glVertex3f(size0, size0, -size0);// c1��
		gl.glVertex3f(0.0f, 0.0f, -size0);// d0��
		gl.glVertex3f(0.0f, size0, -size0);// d1��
		gl.glVertex3f(0.0f, 0.0f, 0.0f);// a0��
		gl.glVertex3f(0.0f, size0, 0.0f);// a1��
		gl.glEnd();
		// ������������廹ȱ���������档Ӧ�ò��ϡ�
		gl.glBegin(GL2.GL_QUADS);// ���͹�����

		gl.glVertex3f(0.0f, 0.0f, 0.0f);// a0��
		gl.glVertex3f(size0, 0.0f, 0.0f);// b0��
		gl.glVertex3f(size0, 0.0f, -size0);// c0��
		gl.glVertex3f(0.0f, 0.0f, -size0);// d0��
		gl.glVertex3f(0.0f, size0, 0.0f);// a1��
		gl.glVertex3f(size0, size0, 0.0f);// b1��
		gl.glVertex3f(size0, size0, -size0);// c1��
		gl.glVertex3f(0.0f, size0, -size0);// d1��
		gl.glEnd();
        gl.glPopMatrix();
        gl.glPopAttrib();//�ָ�ǰһ����
    }
  
    void picter(float x, float y, float z) {
        GLUT glut = new GLUT();
        
        gl.glPushAttrib(GL2.GL_CURRENT_BIT);//����������ɫ��ʵ��
        gl.glPushMatrix();//ƽ̨==============================
        gl.glTranslatef(x, y + 0.5f, z);		//ƽ̨�Ķ�λ
        gl.glColor3f(0.0f, 1.0f, 0.2f);		//��ɫ
        glut.glutSolidCube(1.3f);				//��̨(��)
        gl.glTranslatef(0.0f, 0.8f, 0.0f);	//�ܵ�λ�õ���,����0.8
        gl.glColor3f(0.0f, 0.0f, 1.0f);		//��ɫ
        glut.glutSolidCube(0.3f);		//������(���ߡ���)
        gl.glPopMatrix();
        gl.glPushMatrix();//�״�==============================
        
        gl.glTranslatef(x, y + 2.1f, z);		//�״�Ķ�λ1

        gl.glRotatef(radar_rotate - 90.0f, 0.0f, 1.0f, 0.0f);	//�״���ת2
        //=======================================
        
        gl.glColor3f(1.0f, 1.0f, 1.0f);		//��ɫ
        //missile(0,0,0);
        gl.glRotatef(50.0f, 1.0f, 0.0f, 0.0f);	//�̵ĽǶȵ�������30��   
        glut.glutWireCone(1.5,0.6,20,20);			//��԰׶��(�װ뾶����)
        //=======================================
        gl.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);	//�˵ĽǶȵ���,������ת
        gl.glTranslatef(0.0f, 0.0f, -0.7f);  //�˵�λ�õ���,����һ��
        glut.glutWireCone(0.2,2.0,20,20);				//԰׶��(�װ뾶����)
        gl.glColor3f((float) Math.random(), 0, 0);			//�����ɫ
        gl.glTranslatef(0.0f, 0.0f, 2.0f);	//�˵�λ�õ���,����һ��
        glut.glutSolidSphere(0.3, 10, 10);			//԰(�뾶)
        gl.glPopMatrix();

        
        gl.glPopMatrix();
        gl.glPopAttrib();//�ָ�ǰһ���� 
        
        radar_rotate+=0.5f;
        if(radar_rotate>=360)radar_rotate = 0;
    }
   
    void light(float x, float y, float z, float a) {
        float light_position[] = {x, y, z, a};
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, light_position, 0);
        float diffuse[] = {1.0f, 1.0f, 1.0f, 1.0f};
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, diffuse, 0);
        float ambient[] = {0.8f, 0.9f, 0.9f, 1.0f};
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, ambient, 0);
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT0);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glEnable(GL2.GL_COLOR_MATERIAL);
    }

   
    private void box(float x, float y, float z,int texture) {
    	
        gl.glPushMatrix();//ѹ���ջ
        gl.glScalef(x, y, z);
        gl.glBindTexture(GL.GL_TEXTURE_2D, texture);
        gl.glEnable(GL.GL_TEXTURE_2D);		//ʹ������
        gl.glBegin(GL2.GL_QUADS);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);// ǰ
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, 1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, 1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);// ��
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);// ��
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, 1.0f, 1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);// ��
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, 1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);// ��
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, 1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);// ��
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, 1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glDisable(GL.GL_TEXTURE_2D);
        gl.glPopMatrix();
    }

    private void makeRGBTexture(GL gl, GLU glu, TextureReader.Texture img, int target, boolean mipmapped) {
        if (mipmapped) {

            glu.gluBuild2DMipmaps(target, GL.GL_RGB8, img.getWidth(), img.getHeight(), GL.GL_RGB, GL.GL_UNSIGNED_BYTE, img.getPixels());
        } else {


            /*targetΪGL_TEXTURE��������0������ͼ�����ϸ�̶ȣ�ͨ��������Ϊ��ȥ�ˡ������������ݵĳɷ�������Ϊͼ�����ɺ�ɫ���ݣ���ɫ���ݣ���ɫ�������������ɡ�
            img.getWidth() ������Ŀ�ȡ������֪����ȣ����������������룬�������
            ���Ժ����׵�Ϊ��ָ����ֵ�� img.getHeight() ������ĸ߶ȡ��������Ǳ߿��
            ֵ��һ����ǡ�0���� GL_RGB ����OpenGLͼ�������ɺ졢�̡�����ɫ������ɡ�
            GL_UNSIGNED_BYTE ��ζ�����ͼ����������޷����ֽ����͵ġ����... img.getPixels()
            ����OpenGL�������ݵ���Դ��*/
            gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
            gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
            gl.glTexImage2D(target, 0,3, img.getWidth(), img.getHeight(), 0, GL.GL_RGB, GL.GL_UNSIGNED_BYTE, img.getPixels());

        }
    }


    private boolean loadT8(String[] filenames, int[] textureArr) {

        gl.glEnable(GL.GL_TEXTURE_2D);

        gl.glGenTextures(filenames.length, textureArr,0);         //������Ŀ�����飬��������0Ϊpng,1Ϊbmg

        for (int i = 0; i < filenames.length; i++) {
            TextureReader.Texture texture1 = null;
            try {
                
                texture1 = TextureReader.readTexture(filenames[i]);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }

            if (texture1 == null) {
                return false;
            }



            gl.glBindTexture(GL.GL_TEXTURE_2D, textureArr[i]);


            makeRGBTexture(gl, glu, texture1, GL.GL_TEXTURE_2D, false);;


        }

        gl.glDisable(GL.GL_TEXTURE_2D);
        return true;

    }
}
