package achieve;

import Abstract.bullet;
import Abstract.enemy;
import Abstract.player;
import common.TextureReader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import com.jogamp.opengl.util.gl2.GLUT;

/*��ͼ�λ�ͼ��*/
public class PaintReady {
	public static boolean link;//make it sure that if there is another player;
	double jishu = 0;
	float yg[] = new float[4];
	float radar_rotate = 0;
	static float ht = 0.1f, hb = -0.5f;
	static final int nof = 10;
	public static final float MAP = 800.0f;

	int[] g_cactus = new int[1];
	GLUquadric g_text;
	GL2 gl;

	static int eye_distance = 10;
	static float eyepx;
	static float eyepy;
	static float eyepz;

	GLU glu;
	player p_test = playertest.get();
	Weaponcontrol Wc = Weaponcontrol.getWc();
	public static netplayer np;

	String[] filename;
	int[] textureArr;
	// check
	public static ArrayList<bullet> b_list = new ArrayList<bullet>();
	public static ArrayList<bullet> mb_list = new ArrayList<bullet>();
	public static ArrayList<wave> road = new ArrayList<wave>();
	public static ArrayList<enemy> e_list = new ArrayList<enemy>();

	public static PaintReady pr;
	
	public static PaintReady getPaintReady(GL2 gl){
		if(pr==null)pr = new PaintReady(gl);
		return pr;
	}
	public static PaintReady getPaintReady(){
		return pr;
	}
	private PaintReady(GL2 gl) {
		this.gl = gl;
		glu = new GLU();
		gl.glShadeModel(GL2.GL_SMOOTH); // Enable Smooth Shading
		gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f); // Black Background
		gl.glClearDepth(1.0f); // Depth Buffer Setup
		gl.glEnable(GL.GL_DEPTH_TEST); // Enables Depth Testing
		gl.glDepthFunc(GL.GL_LEQUAL); // The Type Of Depth Testing To Do
		gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_FASTEST); // ������ϸ��͸������

		g_text = glu.gluNewQuadric();

		eyepx = (float) (p_test.x - this.eye_distance
				* Math.sin(p_test.g_Angle / 180 * Math.PI));
		eyepy = p_test.y + 2;
		eyepz = (float) (p_test.z - this.eye_distance
				* Math.cos(p_test.g_Angle / 180 * Math.PI));

		filename = new String[] { "image//background.jpg" };

		textureArr = new int[filename.length + 1];

		loadT8(filename, textureArr);

		// *****************************************set the playground
		init();
	}

	void init() {
		p_test.life = true;
    	p_test.wait_die = false;
    	p_test.y = 0.5f;
    	p_test.lifepoint = 200;
    	e_list.removeAll(e_list);
    	b_list.removeAll(e_list);
    	change1 = false;
    	cutlink = false;
    	restart = 2;
    	np_restart = 2;
    	for(int i=0;i<4;i++){
    		e_quzhu xo = new e_quzhu(-60+i*30,0.5f,-250);
    		xo.setAngle(180);
    		e_list.add(xo);
    	}
    	for(int i=0;i<2;i++){
    		e_xunyang xo = new e_xunyang(-80+i*100,0.5f,-250);
    		e_list.add(xo);
    	}
	}

	boolean DisplayScene() {
		glu.gluLookAt(eyepx, eyepy, eyepz, p_test.x, p_test.y + 0.5, p_test.z,
				0.0, 1.0, 0.0);
		 if(!p_test.life)this.gameover("022ͧ��������Ͷ��ս����");
	       if(e_list.isEmpty())this.gameover("ǰ�ߵ�����ȫ�����壬�Ƿ����׷����");
	     //if(!np.life)this.gameover("");
		return true;
	}

	void paintplayer() {
		if (p_test.life)
			p_test.draw(gl);
	}

	void paintnetplayer() {
		if (np != null && np.life)
			np.draw(gl);
	}
	
	
	boolean change1 = false;
	public static int restart = 2;//2:wait;3:restart;4:quit
	public static int np_restart=2;
	boolean cutlink = false;
	public void gameover(String a){
		if(!change1){
			if(eyepx>1)eyepx--;
	    	if(eyepx<-1)eyepx++;
	    	if(eyepy>2)eyepy--;
	    	if(eyepy<1)eyepy++;
	    	if(eyepz>1)eyepz--;
	    	if(eyepz<-1)eyepz++;
	    	if(eyepx*eyepx<1&&eyepy*eyepy<4&&eyepz*eyepz<1){
	    		change1 = true;
	    		eyepx = 0;
	    		eyepy = 2f;
	    		eyepz = 0;
	    	}
		}
		else{
			if(p_test.x>1)p_test.x--;
	    	if(p_test.x<-1)p_test.x++;
	    	if(p_test.z>1)p_test.z--;
	    	if(p_test.z<-1)p_test.z++;
	    	
	    	if(p_test.x*p_test.x<1&&p_test.z*p_test.z<1){
	    		p_test.x = 0;
	    		p_test.z = 0;

	    		String[] options = { "�������˵�","������Ϸ","�˳���Ϸ" };
	    		int i = JOptionPane.showOptionDialog(null, "��Ϸ����",a,
	    				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
	    				options, options[0]);
	    		if(i==0){
	    			playground.get().changelistener(1);
	    			this.init();
	    			Client.flag=false;
					Server.flag=false;
					playground.c.fresh();
					playground.s.fresh();
					playground.ct.show(playground.get().getContentPane(),"startpanel");
	    		}
	    		if(i==1){
	    			if(!link)this.init();
	    			else {
	    				restart = 3;	
	    			}
	    		}
	    		if(i==2)System.exit(0);
	    	}
		}
	//����ģʽ�����˫����ͬ�����¿�ʼ�����¿�ʼ������������¿�ʼ��һ����ȴ�30s�����30s�ڶԷ���ʼ����Ϸ��ʼ�����������ص�������	
		if(link&&this.restart==3){
			
			while(this.np_restart==2){
				Timer t = new Timer(30000,new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						cutlink = true;
					}
				});
				if(np_restart==3)
					break;
					
				if(cutlink)
				  {
					Client.flag=false;
					Server.flag=false;
					playground.get().changelistener(1);
					playground.ct.show(playground.get().getContentPane(),"startpanel");
					break;
				  }
			}
			
		}
    }
	// ***************************************

	void paintWeaponcontrol() {
		Wc.draw(gl);
	}

	// *******************************************
	void paintenemy() {
		for (int i = 0; i < e_list.size(); i++) {
			if (e_list.get(i).life)
				e_list.get(i).draw(gl);
			else
				e_list.remove(i);
		}
	}

	void Drwaback() {
		gl.glPushMatrix();// ѹ���ջ
		gl.glBindTexture(GL.GL_TEXTURE_2D, textureArr[0]);
		gl.glEnable(GL.GL_TEXTURE_2D); // ʹ������
		gl.glTranslatef(0, 100, 0);
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2f(0.0f, 0.0f);
		gl.glVertex3f(-MAP, -100, MAP);// ǰ
		gl.glTexCoord2f(1.0f, 0.0f);
		gl.glVertex3f(MAP, -100, MAP);
		gl.glTexCoord2f(1.0f, 1.0f);
		gl.glVertex3f(MAP, 100, MAP);
		gl.glTexCoord2f(0.0f, 1.0f);
		gl.glVertex3f(-MAP, 100, MAP);
		gl.glTexCoord2f(1.0f, 0.0f);
		gl.glVertex3f(-MAP, -100, -MAP);// ��
		gl.glTexCoord2f(1.0f, 1.0f);
		gl.glVertex3f(-MAP, 100, -MAP);
		gl.glTexCoord2f(0.0f, 1.0f);
		gl.glVertex3f(MAP, 100, -MAP);
		gl.glTexCoord2f(0.0f, 0.0f);
		gl.glVertex3f(MAP, -100, -MAP);
		gl.glTexCoord2f(0.0f, 1.0f);
		gl.glVertex3f(-MAP, 100, -MAP);// ��
		gl.glTexCoord2f(0.0f, 0.0f);
		gl.glVertex3f(-MAP, 100, MAP);
		gl.glTexCoord2f(1.0f, 0.0f);
		gl.glVertex3f(MAP, 100, MAP);
		gl.glTexCoord2f(1.0f, 1.0f);
		gl.glVertex3f(MAP, 100, -MAP);
		/*
		 * gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-x, -y, -z);// ��
		 * gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(x, -y, -z);
		 * gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(x, -y, z);
		 * gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-x, -y, z);
		 */
		gl.glTexCoord2f(1.0f, 0.0f);
		gl.glVertex3f(MAP, -100, -MAP);// ��
		gl.glTexCoord2f(1.0f, 1.0f);
		gl.glVertex3f(MAP, 100, -MAP);
		gl.glTexCoord2f(0.0f, 1.0f);
		gl.glVertex3f(MAP, 100, MAP);
		gl.glTexCoord2f(0.0f, 0.0f);
		gl.glVertex3f(MAP, -100, MAP);
		gl.glTexCoord2f(0.0f, 0.0f);
		gl.glVertex3f(-MAP, -100, -MAP);// ��
		gl.glTexCoord2f(1.0f, 0.0f);
		gl.glVertex3f(-MAP, -100, MAP);
		gl.glTexCoord2f(1.0f, 1.0f);
		gl.glVertex3f(-MAP, 100, MAP);
		gl.glTexCoord2f(0.0f, 1.0f);
		gl.glVertex3f(-MAP, 100, -MAP);
		gl.glEnd();
		gl.glDisable(GL.GL_TEXTURE_2D);
		gl.glPopMatrix();
	}

	void DrawGround() {
		gl.glBlendFunc(gl.GL_SRC_ALPHA, gl.GL_ONE_MINUS_SRC_ALPHA);
		gl.glPushAttrib(GL2.GL_CURRENT_BIT);// ����������ɫ��ʵ��
		gl.glPushMatrix();
		gl.glColor4f(0.0f, 0.0f, 1.0f, 0.5f);// ������ɫ
		gl.glTranslatef(-MAP, -2 * MAP, MAP); // ƽ̨�Ķ�λ
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
		gl.glPopAttrib();// �ָ�ǰһ����
	}

	public void drawbullet() {
		for (int i = 0; i < b_list.size(); i++) {
			if (b_list.get(i).life && b_list.get(i).y > -10)
				b_list.get(i).draw(gl);
			else
				b_list.remove(i);
		}
		for (int i = 0; i < mb_list.size(); i++) {
			if (mb_list.get(i).life && mb_list.get(i).y > -10)
				mb_list.get(i).draw(gl);
			else
				mb_list.remove(i);
		}
	}

	public void drawwave() {
		for (int i = 0; i < road.size(); i++) {
			if (!road.get(i).life)
				road.remove(i);
			else
				road.get(i).draw(gl);
		}
	}

	public void updata() {
		for (bullet b : b_list) {
			if (p_test.checkcrock(b)) {
				b.life = false;
				p_test.lifepoint -= b.damage;
				p_test.damage_al = true;
				if (p_test.lifepoint <= 0)
					p_test.wait_die = true;

			}
		}
		if (np != null && !np.wait_die) {
			for (int i = 0; i < e_list.size(); i++)
				for (bullet b : mb_list) {
					if (np.checkcrock(b)) {
						b.life = false;
						np.lifepoint -= b.damage;
						np.damage_al = true;
						// if(e_list.get(i).lifepoint<=0)
						// e_list.get(i).wait_die = true;
					}
			}
		}
		for (int i = 0; i < e_list.size(); i++)
			for (bullet b : mb_list) {
				if (e_list.get(i).checkcrock(b)) {
					b.life = false;
					e_list.get(i).lifepoint -= b.damage;
					e_list.get(i).damage_al = true;
					if (e_list.get(i).lifepoint <= 0)
						e_list.get(i).wait_die = true;
				}
			}
	}

	void picter(float x, float y, float z) {
		GLUT glut = new GLUT();

		gl.glPushAttrib(GL2.GL_CURRENT_BIT);// ����������ɫ��ʵ��
		gl.glPushMatrix();// ƽ̨==============================
		gl.glTranslatef(x, y + 0.5f, z); // ƽ̨�Ķ�λ
		gl.glColor3f(0.0f, 1.0f, 0.2f); // ��ɫ
		glut.glutSolidCube(1.3f); // ��̨(��)
		gl.glTranslatef(0.0f, 0.8f, 0.0f); // �ܵ�λ�õ���,����0.8
		gl.glColor3f(0.0f, 0.0f, 1.0f); // ��ɫ
		glut.glutSolidCube(0.3f); // ������(���ߡ���)
		gl.glPopMatrix();
		gl.glPushMatrix();// �״�==============================

		gl.glTranslatef(x, y + 2.1f, z); // �״�Ķ�λ1

		gl.glRotatef(radar_rotate - 90.0f, 0.0f, 1.0f, 0.0f); // �״���ת2
		// =======================================

		gl.glColor3f(1.0f, 1.0f, 1.0f); // ��ɫ
		// missile(0,0,0);
		gl.glRotatef(50.0f, 1.0f, 0.0f, 0.0f); // �̵ĽǶȵ�������30��
		glut.glutWireCone(1.5, 0.6, 20, 20); // ��԰׶��(�װ뾶����)
		// =======================================
		gl.glRotatef(180.0f, 1.0f, 0.0f, 0.0f); // �˵ĽǶȵ���,������ת
		gl.glTranslatef(0.0f, 0.0f, -0.7f); // �˵�λ�õ���,����һ��
		glut.glutWireCone(0.2, 2.0, 20, 20); // ԰׶��(�װ뾶����)
		gl.glColor3f((float) Math.random(), 0, 0); // �����ɫ
		gl.glTranslatef(0.0f, 0.0f, 2.0f); // �˵�λ�õ���,����һ��
		glut.glutSolidSphere(0.3, 10, 10); // ԰(�뾶)
		gl.glPopMatrix();

		gl.glPopMatrix();
		gl.glPopAttrib();// �ָ�ǰһ����

		radar_rotate += 0.5f;
		if (radar_rotate >= 360)
			radar_rotate = 0;
	}

	void light(float x, float y, float z, float a) {
		float light_position[] = { x, y, z, a };
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, light_position, 0);
		float diffuse[] = { 1.0f, 1.0f, 1.0f, 1.0f };
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, diffuse, 0);
		float ambient[] = { 0.8f, 0.9f, 0.9f, 1.0f };
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, ambient, 0);
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_LIGHT0);
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glEnable(GL2.GL_COLOR_MATERIAL);
	}

	public void box(float x, float y, float z, int texture, float px,
			float py, float pz) {

		gl.glPushMatrix();// ѹ���ջ
		gl.glBindTexture(GL.GL_TEXTURE_2D, texture);
		gl.glEnable(GL.GL_TEXTURE_2D); // ʹ������
		gl.glTranslatef(px, py, pz);
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2f(0.0f, 0.0f);
		gl.glVertex3f(-x, -y, z);// ǰ
		gl.glTexCoord2f(1.0f, 0.0f);
		gl.glVertex3f(x, -y, z);
		gl.glTexCoord2f(1.0f, 1.0f);
		gl.glVertex3f(x, y, z);
		gl.glTexCoord2f(0.0f, 1.0f);
		gl.glVertex3f(-x, y, z);
		gl.glTexCoord2f(1.0f, 0.0f);
		gl.glVertex3f(-x, -y, -z);// ��
		gl.glTexCoord2f(1.0f, 1.0f);
		gl.glVertex3f(-x, y, -z);
		gl.glTexCoord2f(0.0f, 1.0f);
		gl.glVertex3f(x, y, -z);
		gl.glTexCoord2f(0.0f, 0.0f);
		gl.glVertex3f(x, -y, -z);
		gl.glTexCoord2f(0.0f, 1.0f);
		gl.glVertex3f(-x, y, -z);// ��
		gl.glTexCoord2f(0.0f, 0.0f);
		gl.glVertex3f(-x, y, z);
		gl.glTexCoord2f(1.0f, 0.0f);
		gl.glVertex3f(x, y, z);
		gl.glTexCoord2f(1.0f, 1.0f);
		gl.glVertex3f(x, y, -z);
		gl.glTexCoord2f(1.0f, 1.0f);
		gl.glVertex3f(-x, -y, -z);// ��
		gl.glTexCoord2f(0.0f, 1.0f);
		gl.glVertex3f(x, -y, -z);
		gl.glTexCoord2f(0.0f, 0.0f);
		gl.glVertex3f(x, -y, z);
		gl.glTexCoord2f(1.0f, 0.0f);
		gl.glVertex3f(-x, -y, z);
		gl.glTexCoord2f(1.0f, 0.0f);
		gl.glVertex3f(x, -y, -z);// ��
		gl.glTexCoord2f(1.0f, 1.0f);
		gl.glVertex3f(x, y, -z);
		gl.glTexCoord2f(0.0f, 1.0f);
		gl.glVertex3f(x, y, z);
		gl.glTexCoord2f(0.0f, 0.0f);
		gl.glVertex3f(x, -y, z);
		gl.glTexCoord2f(0.0f, 0.0f);
		gl.glVertex3f(-x, -y, -z);// ��
		gl.glTexCoord2f(1.0f, 0.0f);
		gl.glVertex3f(-x, -y, z);
		gl.glTexCoord2f(1.0f, 1.0f);
		gl.glVertex3f(-x, y, z);
		gl.glTexCoord2f(0.0f, 1.0f);
		gl.glVertex3f(-x, y, -z);
		gl.glEnd();
		gl.glDisable(GL.GL_TEXTURE_2D);
		gl.glPopMatrix();
	}

	private void makeRGBTexture(GL gl, GLU glu, TextureReader.Texture img,
			int target, boolean mipmapped) {
		if (mipmapped) {

			glu.gluBuild2DMipmaps(target, GL.GL_RGB8, img.getWidth(),
					img.getHeight(), GL.GL_RGB, GL.GL_UNSIGNED_BYTE,
					img.getPixels());
		} else {

			/*
			 * targetΪGL_TEXTURE��������0������ͼ�����ϸ�̶ȣ�ͨ��������Ϊ��ȥ�ˡ������������ݵĳɷ�������Ϊͼ�����ɺ�ɫ���ݣ���ɫ����
			 * ����ɫ�������������ɡ� img.getWidth() ������Ŀ�ȡ������֪����ȣ����������������룬�������
			 * ���Ժ����׵�Ϊ��ָ����ֵ�� img.getHeight() ������ĸ߶ȡ��������Ǳ߿�� ֵ��һ����ǡ�0���� GL_RGB
			 * ����OpenGLͼ�������ɺ졢�̡�����ɫ������ɡ� GL_UNSIGNED_BYTE
			 * ��ζ�����ͼ����������޷����ֽ����͵ġ����... img.getPixels() ����OpenGL�������ݵ���Դ��
			 */
			gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER,
					GL.GL_LINEAR);
			gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER,
					GL.GL_LINEAR);
			gl.glTexImage2D(target, 0, 3, img.getWidth(), img.getHeight(), 0,
					GL.GL_RGB, GL.GL_UNSIGNED_BYTE, img.getPixels());

		}
	}

	private boolean loadT8(String[] filenames, int[] textureArr) {

		gl.glEnable(GL.GL_TEXTURE_2D);

		gl.glGenTextures(filenames.length, textureArr, 0); // ������Ŀ�����飬��������0Ϊpng,1Ϊbmg

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

			makeRGBTexture(gl, glu, texture1, GL.GL_TEXTURE_2D, false);
			;

		}

		gl.glDisable(GL.GL_TEXTURE_2D);
		return true;

	}
}
