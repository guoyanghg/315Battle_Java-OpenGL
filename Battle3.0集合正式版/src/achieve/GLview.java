package achieve;

import java.awt.Font;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.gl2.GLUT;

public class GLview implements GLEventListener{
	GLU glu;
	private final int ground = 1000,star = 1000;
	int position[][];
	float eye[] = new float[3];                                  TextRenderer tx;float txc = 1;
	float v = 1,a = 0.1f;
	boolean changemodel = false,slow = false;
	float rotate_r=0, color = 0;
	int ro=0;float col[] = new float[3];
	public void display(GLAutoDrawable drawable){
		
		 GL2 gl = drawable.getGL().getGL2();
	     gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);// 刷新背景
	     gl.glLoadIdentity();								 // 重置当前的模型观察矩阵
	     gl.glEnable(GL.GL_BLEND);
	     GLUT glut = new GLUT();
	     glu.gluLookAt(eye[0],eye[1],eye[2],ground,ground,ground,0,1,0);
	     if(!changemodel){
	    	 this.changelook1(gl);
		   /**/gl.glColor3d(1, 1, 1);
		     for(int i=0;i<star;i++){
		    	 gl.glPushMatrix();
		    	 gl.glTranslatef(position[i][0], position[i][1],position[i][2]);
		    	 glut.glutSolidSphere(1, 6, 6);
		    	 gl.glPopMatrix();
		     } 
	     }
	     gl.glPushMatrix();
    	 gl.glTranslatef(ground,ground,ground);
    	 gl.glColor3f(col[0], col[1], col[2]);
    	 if(changemodel){
    		 gl.glRotatef(rotate_r, 0, 0, 1);
    	 if(this.rotate_r>360){
    		 this.rotate_r = 0;
    	 }
    	 rotate_r++;
    	 gl.glClearColor(color, color, color, 1);
    	 color+=0.01;
    	 txc-=0.01;
    	 gl.glPushAttrib(GL2.GL_CURRENT_BIT);
    	 tx.setColor(col[0],col[1],col[2],1);
    	 gl.glPopAttrib();
    	 this.drawtext(drawable, "THE BATTLE3.0 LINKSTART",0,500);
    	 this.drawtext(drawable, "by YANG Liqun&GUO Yang", 0, 50);
    	 if(color>=1){
    		 color=1;
    		 switch(ro){
    		 case 0:col[0]+=0.01;if(col[0]>1){
    			 col[0]=1;ro++;
    		 }break;
    		 case 1:col[1]+=0.01;if(col[1]>1){
    			 col[1]=1;ro++;
    		 }break;
    		 case 2:col[2]+=0.01;if(col[2]>1){
    			 col[2]=1;ro++;
    		 }break;
    		 }
    	 }
    	 }
    	 
    	 gl.glPushAttrib(GL2.GL_CURRENT_BIT);
    	 gl.glPushMatrix();
    	 gl.glColor3f(col[0],col[1],col[2]);
    	 gl.glRotated(2*rotate_r,0,0,-1);
    	 gl.glTranslatef(3,0, 0);
    	 glut.glutWireSphere(0.5, 10, 10);
    	 gl.glTranslatef(-6,0,0);

    	 glut.glutWireSphere(0.5, 10, 10);
    	 gl.glPopAttrib();
    	 gl.glPopMatrix();
    	 
    	 glut.glutSolidCone(2, 2, 10, 10);
    	 gl.glPopMatrix();
    	 
    	 if(ro>2){
    		 playground.get().changelistener(0);
    		 this.primary();
    	 }
    	 
	}
	
	public void drawtext(GLAutoDrawable drawable,String a,int x,int y){
		GL2 gl = drawable.getGL().getGL2();
	    gl.glPushAttrib(GL2.GL_CURRENT_BIT);
   	    tx.beginRendering(drawable.getSurfaceWidth(), drawable.getSurfaceHeight());
   	    tx.draw(a,x,y);
   	    tx.endRendering();
   	    gl.glPopAttrib();
	}
	public void changelook1(GL2 gl){
		 v+=a;
	     if(eye[0]>500&&!slow){
	    	 a = -a;
	    	 slow = true;
	     }
	     if(990<=eye[0]){
	    	 v = 0;
	    	 changemodel = true;
	     }     
	     for(int i=0;i<3;i++){
	    	 eye[i]+=v;
	     }
	}
	
	 public void init(GLAutoDrawable drawable) {
	        // TODO Auto-generated method stub

	        GL2 gl = drawable.getGL().getGL2();      
	        glu = new GLU();
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
	        gl.glClearColor(0, 0, 0, 1);
	        
	        position = new int[star][3];
	        for(int i=0;i<star;i++){
	        	for(int i1=0;i1<3;i1++){
	        		position[i][i1] = (int) (ground*Math.random());
	        	}
	        }

	        tx = new TextRenderer(new Font("SansSerif", Font.BOLD, 30),false,false);
	        gl.glClearColor(0,0,0,1);
	        
	        
	    }
	 
	public void primary(){
		v = 1;
        a = 0.1f;
    	changemodel = false;
    	slow = false;
    	rotate_r=0;
    	color = 0;
        ro=0;
    	for(int i=0;i<3;i++){
    		col[i] = 0;
    		eye[i] = 0;
    	}
    	
	}
	public void dispose(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}
}
