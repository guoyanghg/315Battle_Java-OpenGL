package achieve;

import Abstract.bullet;
import Abstract.enemy;
import Abstract.player;
import common.TextureReader;

import java.io.IOException;
import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import com.jogamp.opengl.util.gl2.GLUT;


//**主图形画图类*/
public class PaintReady {
	double jishu=0;           float yg[] = new float[4];
	float radar_rotate = 0;
	static float ht=0.1f,hb=-0.5f;
	static final int nof = 10;
	public static final float MAP = 800.0f;
	
    int[] g_cactus = new int[1];               
    GLUquadric g_text;
    GL2 gl;
    
    static int eye_distance =10;
    static float eyepx;
    static float eyepy;
    static float eyepz;
    
    GLU glu;
    player p_test = playertest.get();
    Weaponcontrol Wc = Weaponcontrol.getWc();

    String[] filename;
    int[] textureArr;
    //check
    public static ArrayList<bullet> b_list = new ArrayList<bullet>();
    public static ArrayList<bullet> mb_list = new ArrayList<bullet>();
    public static ArrayList<wave> road = new ArrayList<wave>();
    public static ArrayList<enemy> e_list = new ArrayList<enemy>();
    
    public PaintReady(GL2 gl) {
        this.gl = gl;
        glu = new GLU();
        gl.glShadeModel(GL2.GL_SMOOTH);              // Enable Smooth Shading
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);    // Black Background
        gl.glClearDepth(1.0f);                      // Depth Buffer Setup
        gl.glEnable(GL.GL_DEPTH_TEST);							// Enables Depth Testing
        gl.glDepthFunc(GL.GL_LEQUAL);								// The Type Of Depth Testing To Do
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_FASTEST);	 // 真正精细的透视修正


        g_text = glu.gluNewQuadric();

        eyepx = (float) (p_test.x-this.eye_distance*Math.sin(p_test.g_Angle/180*Math.PI));
        eyepy = p_test.y+2;
        eyepz = (float) (p_test.z-this.eye_distance*Math.cos(p_test.g_Angle/180*Math.PI));
        
        filename = new String[]{
                    "image//background.jpg"
                };

        textureArr = new int[filename.length+1];

        loadT8(filename, textureArr); 

       //*****************************************set the playground
        init();
    }
    
    void init(){
    	for(int i=0;i<8;i++){
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
       
        glu.gluLookAt(eyepx, eyepy, eyepz,
                p_test.x,p_test.y+0.5,p_test.z,
                0.0, 1.0, 0.0);
        return true;
    }

    void paintplayer(){
    	if(p_test.life)p_test.draw(gl);
    }
//***************************************   
    final float w_size = 0.001f;
	public float tx = 0,ty = 0;
	public float power = 1;
	final float power_max = 4f;
    void paintWeaponcontrol(){
       Wc.draw(gl);
    }
//*******************************************
    void paintenemy(){
    	for(int i=0;i<e_list.size();i++){
    		if(e_list.get(i).life)e_list.get(i).draw(gl);
    		else e_list.remove(i);
    	}
    }
    
    void Drwaback(){
    	gl.glPushMatrix();//压入堆栈
        gl.glBindTexture(GL.GL_TEXTURE_2D, 1);
        gl.glEnable(GL.GL_TEXTURE_2D);		//使用纹理
        gl.glTranslatef(0,100,0);
        gl.glBegin(GL2.GL_QUADS);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-MAP, -100, MAP);// 前
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(MAP, -100, MAP);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(MAP, 100, MAP);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-MAP, 100, MAP);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(-MAP, -100, -MAP);// 后
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(-MAP, 100, -MAP);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(MAP, 100, -MAP);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(MAP, -100, -MAP);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-MAP, 100, -MAP);// 上
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-MAP, 100, MAP);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(MAP, 100, MAP);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(MAP, 100, -MAP);
     /*  gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(-x, -y, -z);// 下
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(x, -y, -z);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(x, -y, z);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(-x, -y, z);*/ 
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(MAP, -100, -MAP);// 左
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(MAP, 100, -MAP);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(MAP, 100, MAP);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(MAP, -100, MAP);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-MAP, -100, -MAP);// 右
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
    	gl.glBlendFunc(gl.GL_SRC_ALPHA,gl.GL_ONE_MINUS_SRC_ALPHA);
        gl.glPushAttrib(GL2.GL_CURRENT_BIT);//保存现有颜色属实性
        gl.glPushMatrix();
        gl.glColor4f(0.0f, 0.0f, 1.0f,0.5f);//设置蓝色
        gl.glTranslatef(-MAP,-2*MAP,MAP);		//平台的定位
        int size0 = (int) (MAP * 2);
        gl.glLineWidth(1.0f);
        
        gl.glBegin(GL2.GL_QUAD_STRIP);// 填充凸多边形
		gl.glVertex3f(0.0f, 0.0f, 0.0f);// a0点
		gl.glVertex3f(0.0f, size0, 0.0f);// a1点
		gl.glVertex3f(size0, 0.0f, 0.0f);// b0点
		gl.glVertex3f(size0, size0, 0.0f);// b1点
		gl.glVertex3f(size0, 0.0f, -size0);// c0点
		gl.glVertex3f(size0, size0, -size0);// c1点
		gl.glVertex3f(0.0f, 0.0f, -size0);// d0点
		gl.glVertex3f(0.0f, size0, -size0);// d1点
		gl.glVertex3f(0.0f, 0.0f, 0.0f);// a0点
		gl.glVertex3f(0.0f, size0, 0.0f);// a1点
		gl.glEnd();
		// 现在这个正方体还缺上下两个面。应该补上。
		gl.glBegin(GL2.GL_QUADS);// 填充凸多边形

		gl.glVertex3f(0.0f, 0.0f, 0.0f);// a0点
		gl.glVertex3f(size0, 0.0f, 0.0f);// b0点
		gl.glVertex3f(size0, 0.0f, -size0);// c0点
		gl.glVertex3f(0.0f, 0.0f, -size0);// d0点
		gl.glVertex3f(0.0f, size0, 0.0f);// a1点
		gl.glVertex3f(size0, size0, 0.0f);// b1点
		gl.glVertex3f(size0, size0, -size0);// c1点
		gl.glVertex3f(0.0f, size0, -size0);// d1点
		gl.glEnd();
        gl.glPopMatrix();
        gl.glPopAttrib();//恢复前一属性
    }
    
    public void drawbullet(){
    	for(int i=0;i<b_list.size();i++){
    		if(b_list.get(i).life&&b_list.get(i).y>-10)b_list.get(i).draw(gl);
    		else b_list.remove(i);
    	}
    	for(int i=0;i<mb_list.size();i++){
    		if(mb_list.get(i).life&&mb_list.get(i).y>-10)mb_list.get(i).draw(gl);
    		else mb_list.remove(i);
    	}
    }
    public void drawwave(){
    	for(int i=0;i<road.size();i++){
    		if(!road.get(i).life)road.remove(i);
    		else road.get(i).draw(gl);
    	}
    }
    
    public void updata(){
    	for(bullet b:b_list){
    		if(p_test.checkcrock(b)){
    			b.life = false;
    			p_test.lifepoint-=b.damage;
    			p_test.damage_al = true;
    			if(p_test.lifepoint<=0)
    			p_test.wait_die = true;
    			
    		}
    	}
    	for(int i=0;i<e_list.size();i++)
    		for(bullet b:mb_list){
    			if(e_list.get(i).checkcrock(b)){
    			b.life = false;
    			e_list.get(i).lifepoint-=b.damage;
    			e_list.get(i).damage_al = true;
    			if(e_list.get(i).lifepoint<=0)
				e_list.get(i).wait_die = true;		
			}
    		}
    }
    
    void picter(float x, float y, float z) {
        GLUT glut = new GLUT();
        
        gl.glPushAttrib(GL2.GL_CURRENT_BIT);//保存现有颜色属实性
        gl.glPushMatrix();//平台==============================
        gl.glTranslatef(x, y + 0.5f, z);		//平台的定位
        gl.glColor3f(0.0f, 1.0f, 0.2f);		//绿色
        glut.glutSolidCube(1.3f);				//方台(高)
        gl.glTranslatef(0.0f, 0.8f, 0.0f);	//架的位置调整,上升0.8
        gl.glColor3f(0.0f, 0.0f, 1.0f);		//蓝色
        glut.glutSolidCube(0.3f);		//长方架(宽、高、长)
        gl.glPopMatrix();
        gl.glPushMatrix();//雷达==============================
        
        gl.glTranslatef(x, y + 2.1f, z);		//雷达的定位1

        gl.glRotatef(radar_rotate - 90.0f, 0.0f, 1.0f, 0.0f);	//雷达旋转2
        //=======================================
        
        gl.glColor3f(1.0f, 1.0f, 1.0f);		//白色
        //missile(0,0,0);
        gl.glRotatef(50.0f, 1.0f, 0.0f, 0.0f);	//盘的角度调整，仰30度   
        glut.glutWireCone(1.5,0.6,20,20);			//线园锥盘(底半径、高)
        //=======================================
        gl.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);	//杆的角度调整,反方向转
        gl.glTranslatef(0.0f, 0.0f, -0.7f);  //杆的位置调整,缩进一点
        glut.glutWireCone(0.2,2.0,20,20);				//园锥杆(底半径、高)
        gl.glColor3f((float) Math.random(), 0, 0);			//随机红色
        gl.glTranslatef(0.0f, 0.0f, 2.0f);	//杆的位置调整,缩进一点
        glut.glutSolidSphere(0.3, 10, 10);			//园(半径)
        gl.glPopMatrix();

        
        gl.glPopMatrix();
        gl.glPopAttrib();//恢复前一属性 
        
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

   
    private void box(float x, float y, float z,int texture,float px,float py,float pz) {
    	
        gl.glPushMatrix();//压入堆栈
        gl.glBindTexture(GL.GL_TEXTURE_2D, texture);
        gl.glEnable(GL.GL_TEXTURE_2D);		//使用纹理
        gl.glTranslatef(px, py, pz);
        gl.glBegin(GL2.GL_QUADS);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-x, -y, z);// 前
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(x, -y, z);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(x, y, z);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-x, y, z);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(-x, -y, -z);// 后
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(-x, y, -z);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(x, y, -z);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(x, -y, -z);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-x, y, -z);// 上
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-x, y, z);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(x, y, z);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(x, y, -z);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(-x, -y, -z);// 下
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(x, -y, -z);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(x, -y, z);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(-x, -y, z);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(x, -y, -z);// 左
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(x, y, -z);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(x, y, z);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(x, -y, z);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-x, -y, -z);// 右
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

    private void makeRGBTexture(GL gl, GLU glu, TextureReader.Texture img, int target, boolean mipmapped) {
        if (mipmapped) {

            glu.gluBuild2DMipmaps(target, GL.GL_RGB8, img.getWidth(), img.getHeight(), GL.GL_RGB, GL.GL_UNSIGNED_BYTE, img.getPixels());
        } else {


            /*target为GL_TEXTURE，参数“0”代表图像的详细程度，通常就由它为零去了。参数三是数据的成分数。因为图像是由红色数据，绿色数据，蓝色数据三种组分组成。
            img.getWidth() 是纹理的宽度。如果您知道宽度，您可以在这里填入，但计算机
            可以很容易的为您指出此值。 img.getHeight() 是纹理的高度。参数零是边框的
            值，一般就是“0”。 GL_RGB 告诉OpenGL图像数据由红、绿、蓝三色数据组成。
            GL_UNSIGNED_BYTE 意味着组成图像的数据是无符号字节类型的。最后... img.getPixels()
            告诉OpenGL纹理数据的来源。*/
            gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
            gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
            gl.glTexImage2D(target, 0,3, img.getWidth(), img.getHeight(), 0, GL.GL_RGB, GL.GL_UNSIGNED_BYTE, img.getPixels());

        }
    }


    private boolean loadT8(String[] filenames, int[] textureArr) {

        gl.glEnable(GL.GL_TEXTURE_2D);

        gl.glGenTextures(filenames.length, textureArr,0);         //纹理数目，数组，类型设置0为png,1为bmg

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
