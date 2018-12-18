package achieve;

import javax.media.opengl.GL2;

import Abstract.basecomponent;

public class wave extends basecomponent {
	float wave_r = 0.05f;
	public float color = 1;
	boolean life = true;

	public wave(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;	
	}

	public void draw(GL2 gl) {
		this.wave_r+=0.1;
		this.color-=0.01;
		if(color<0)life = false;
		gl.glPushAttrib(GL2.GL_CURRENT_BIT);
		gl.glPushMatrix();
		gl.glTranslatef(this.x, this.y, this.z);
		gl.glColor4f(1, 1, 1, color);
		gl.glBegin(GL2.GL_LINE_LOOP);
		for (int i = 0; i < 10; i++) {
			gl.glVertex3f((float) (wave_r * Math.cos(Math.PI / 5 * i)), 0,
					(float) (wave_r * Math.sin(Math.PI / 5 * i)));
		}
		gl.glEnd();
		gl.glPopMatrix();
		gl.glPopAttrib();
	}

}
