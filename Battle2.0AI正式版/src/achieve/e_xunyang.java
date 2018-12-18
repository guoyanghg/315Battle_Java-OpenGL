package achieve;

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.gl2.GLUT;

import Abstract.enemy;

public class e_xunyang extends enemy{
	int h_count=0,find_count=0,fire_count = 0;
	public e_xunyang(float x,float y,float z){
		this.x = x;
		this.y = y;
		this.z = z;
		this.in = playground.m_pocket.getpula();
		this.v = 0.3f;
		this.life = true;
		this.wait_die = false;
		this.check_r = 2.5f;
		this.find_r = 400;
		this.fire_r = 300;
		lifepoint = 500;
	}
	public void draw(GL2 gl) {
		this.updata();
		if(!wait_die);
		else {
			y-=0.01f;
			count++;
			if(count>10){
				storeroad();
				count = 0;
			}
			if(y<=-4)life = false;
			if(!boom_al)boom(gl);
		}
		if(damage_al)this.damage(gl);
		
		gl.glPushAttrib(GL2.GL_CURRENT_BIT);
		gl.glPushMatrix();
		gl.glTranslatef(x, y, z);
	    gl.glColor3f(0.5f,0.5f, 0);                                
		gl.glRotatef(g_Angle, 0, 1.0f,0.0f);
        in.draw(gl,texture);
        
		gl.glPopAttrib();
		gl.glPopMatrix();
	}
	
	public void damage(GL2 gl){
		GLUT glut = new GLUT();
		gl.glPushAttrib(gl.GL_CURRENT_BIT);
		gl.glPushMatrix();
		gl.glColor4f(1,0,0,color);
		gl.glTranslatef(x,-0.1f, z);
		gl.glRotatef(-90, 1, 0, 0);
		glut.glutWireCone(3, r, 20, 20);
		gl.glPopAttrib();
		gl.glPopMatrix();
		
		if(r<10&&damage_al){
			r+=0.5;
		}
		else {
			r = 0;
			damage_al = false;
		}
		color-=0.001f;
	}
	
	public void boom(GL2 gl){
		GLUT glut = new GLUT();
		gl.glPushAttrib(gl.GL_CURRENT_BIT);
		gl.glPushMatrix();
		gl.glColor4f(1,0,0,color);
		gl.glTranslatef(x, 0.5f, z);
		glut.glutSolidSphere(r,20, 20);
		gl.glTranslatef(0, 0, 1);
		glut.glutSolidSphere(r,20, 20);
		gl.glPopAttrib();
		gl.glPopMatrix();
		
		if(r<10&&!boom_al){
			r+=0.1;
		}
		else {
			r = 0;
			boom_al = true;
		}

		color-=0.001f;
	}
	@Override
	public void changeweapon(int n) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updata() {
		if(wait_die);
		else{
			super.updata();
			h_count++;
			if(h_count>2){
				h_count=0;
				this.move();
			}
			find_count++;
			if(find_count>20){
				find_count=0;
				this.checktarget(playertest.get());
			}
			fire_count++;
			if(fire_count>60){
				fire_count = 0;
				this.checkfire(playertest.get());
			}
		}
	}
	public void attack(){
		Shell s = new Shell(fx,fy,fz,b_vx,b_vy,b_vz);
		PaintReady.b_list.add(s);
		Shell s1 = new Shell(fx,fy,fz,b_vx+0.1f,b_vy,b_vz);
		PaintReady.b_list.add(s1);
		Shell s2 = new Shell(fx,fy,fz,b_vx-0.1f,b_vy,b_vz);
		PaintReady.b_list.add(s2);
		Shell s3 = new Shell(fx,fy,fz,b_vx,b_vy,b_vz+0.1f);
		PaintReady.b_list.add(s3);
		Shell s4 = new Shell(fx,fy,fz,b_vx,b_vy,b_vz-0.1f);
		PaintReady.b_list.add(s4);
	}
}
