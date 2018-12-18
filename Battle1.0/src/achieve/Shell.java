package achieve;

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.gl2.GLUT;

import Abstract.bullet;

public class Shell extends bullet {

	public Shell(float v,float x,float y,float z,float tx,float ty,float tz){
		this.v = v;
		this.x = x;
		this.y = y;
		this.z = z;
		this.settarget(tx, ty, tz);	
		                  //                System.out.println(this.x+"%"+this.y+"@"+this.z);
	}
	@Override
	public void draw(GL2 gl) {
		super.updata();
		// TODO Auto-generated method stub
		GLUT glut = new GLUT();
		gl.glPushAttrib(GL2.GL_CURRENT_BIT);
		gl.glPushMatrix();// »ð¼ý=============================
		gl.glColor3f(1.0f, 0.0f, 0.0f); // ºìÉ«
		gl.glTranslatef(x, y, z); // »ð¼ýµÄ¶¨Î»
		// =============================================
	//	gl.glRotatef((float)((Math.PI*3/2+this.angle_y)/(2*Math.PI)*360.0f),1, 0, 0);
	//	gl.glRotatef((float)((Math.PI*3/2+this.angle_y)/(2*Math.PI)*360.0f),0, 0, 1);
		gl.glRotatef((float)((rd.angle_law/Math.PI)*180),rd.r_x,rd.r_y,rd.r_z);
		gl.glColor3f(1.0f, 0.0f, 0.0f);// ºìÉ«
		glut.glutSolidCone(0.2f, 0.6f, 10, 10); // Ô°×¶(µ×°ë¾¶¡¢¸ß)
		// =============================================
		gl.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
		gl.glColor3f(1.0f, 1.0f, 1.0f); // °×É«
		glut.glutSolidCylinder(0.2, 1, 10, 10); // Ô°Öù(°ë¾¶¡¢¸ß)
		gl.glColor4f(1.0f, 0, 0.0f,0.5f);
		gl.glTranslatef(0.0f, 0.0f, 1.0f);
		glut.glutSolidCone(0.2, 1.5, 10, 10);
		gl.glPopMatrix();
		gl.glPopAttrib();
		

	}

}
