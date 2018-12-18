package achieve;

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.gl2.GLUT;

public class boomwave extends wave{

	float length=0,l_plus=0.3f,l_pv = -0.005f;boolean rem = false;
	public boomwave(float x, float y, float z) {
		super(x, y, z);
		// TODO Auto-generated constructor stub
	}
	public void draw(GL2 gl){
			GLUT glut = new GLUT();
			gl.glPushAttrib(GL2.GL_CURRENT_BIT);
			gl.glPushMatrix();
			gl.glTranslatef(x, 0, z);
			gl.glRotatef(-90, 1, 0, 0);
			glut.glutWireCylinder(1, length, 20, 20);
			gl.glPopMatrix();
			gl.glPopAttrib();
			length+=l_plus;
			l_plus+=this.l_pv;		
			if(length<-0.1)life = false;
	}

}
