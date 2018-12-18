package achieve;

import javax.media.opengl.GL2;

import Abstract.enemy;

public class e_quzhu extends enemy{

	int h_count=0,find_count=0,fire_count=0;
	public e_quzhu(float x,float y,float z){
		this.x = x;
		this.y = y;
		this.z = z;
		this.in = playground.m_pocket.getquzhu();
		this.v = 1f;
		this.find_r = 250;
		this.fire_r = 80;
		this.life = true;
		this.wait_die = false;
		this.check_r = 2;
		this.lifepoint = 200;
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
			if(fire_count>80){
				fire_count = 0;
				this.checkfire(playertest.get());
			}
		}
	}
}
