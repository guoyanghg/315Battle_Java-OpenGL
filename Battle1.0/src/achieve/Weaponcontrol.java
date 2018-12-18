package achieve;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import com.jogamp.opengl.util.gl2.GLUT;

import Abstract.basecomponent;

public class Weaponcontrol extends basecomponent{

	final float w_size = 0.01f;
	static Weaponcontrol Wc;
	public float tx=0,ty=0;
	public float power;
	final float power_max = 4f;
	private Weaponcontrol(){
		this.x = -0.045200218f;
		this.y = -0.031399984f;
		this.power = 2;
	}
	public static Weaponcontrol getWc(){
		if(Wc == null)Wc = new Weaponcontrol();
		return Wc;
	}
	
	void dxt(GL2 gl){
	  
	}
	void draw(GL2 gl){
	GLUT glut = new GLUT();
		
        gl.glPushAttrib(GL2.GL_CURRENT_BIT);
		gl.glPushMatrix();
        gl.glLoadIdentity();
		gl.glTranslatef(x, y, -0.1f);
		gl.glColor3f(0, 0, 0);
		gl.glBegin(gl.GL_QUADS);
		gl.glVertex2f(w_size, w_size);
		gl.glVertex2f(w_size,-w_size);
		gl.glVertex2f(-w_size, -w_size);
		gl.glVertex2f(-w_size, w_size);
		gl.glEnd();
	
		gl.glTranslatef(0.011f,0,0);
		gl.glBegin(gl.GL_QUADS);
		gl.glColor3f(1, 0, 0);
		gl.glVertex2f(w_size/10, power/power_max*w_size-w_size);
		gl.glVertex2f(w_size/10,-w_size);
		gl.glVertex2f(-w_size/10, -w_size);
		gl.glVertex2f(-w_size/10,power/power_max*w_size-w_size);
		gl.glEnd();
		
		gl.glTranslatef(-0.011f,0,0);
		gl.glTranslatef(tx,ty,0);
		gl.glBegin(gl.GL_QUADS);
		gl.glColor3f(0, 1, 0.5f);
		gl.glVertex2f(w_size/10, w_size/10);
		gl.glVertex2f(w_size/10,-w_size/10);
		gl.glVertex2f(-w_size/10, -w_size/10);
		gl.glVertex2f(-w_size/10, w_size/10);
		gl.glEnd();
	
		
		gl.glPopAttrib();
		gl.glPopMatrix();
		
	}
}
