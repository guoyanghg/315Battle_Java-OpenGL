package Abstract;

import javax.media.opengl.GL2;

import achieve.PaintReady;
import achieve.Shell;

public abstract class enemy extends player{
	
	protected float b_power;
	protected float find_r ,fire_r;
	protected float fx,fy,fz,b_vy,b_vx,b_vz;
	private final float vx_z = 1f;
	private float g_plus = 1f;
	private float g_tar_angle;
	private boolean set_tar = false,meet_b = false;
	
	public  void updata(){
		if((this.g_Angle-this.g_tar_angle)>5){
			this.g_Angle-=g_plus;
		}
		if((this.g_Angle-this.g_tar_angle)<-5){
			this.g_Angle+=g_plus;
		}
	}
	public void draw(GL2 gl){
		updata();
		super.draw(gl);
	}
	public void locktarget(basecomponent check){
		
		double dis = Math.sqrt((this.x-check.x)*(this.x-check.x)+(z-check.z)*(z-check.z));
		double time = dis/vx_z;
		if(time>10){
			b_vy = (float)(0.0025*(time-10));
			b_vx = (float) ((check.x-this.x)/dis*vx_z);
			b_vz = (float) ((check.z-this.z)/dis*vx_z);	
			
			fx = x + b_vx*6;
			fy = y;
			fz = z + b_vz*6;			
			this.attack();
		}
	}
	public void setAngle(float x){
		this.g_Angle = x;
	}
	void moveto(basecomponent t){	
		double dis = Math.sqrt((t.x-this.x)*(t.x-this.x) + (t.z-this.z)*(t.z-this.z));		
		this.g_tar_angle = 180-(float) (Math.acos((this.z-t.z)/dis)/Math.PI*180);
		if((this.x-t.x)>0) this.g_tar_angle = 360-this.g_tar_angle;
		
	}
	protected void move(){
		super.move(true);
		float check = PaintReady.MAP*2/3;
		if(this.x>check){
			x = check;
			this.meet_b = true;
		}
		if(this.x<-check){
			x = -check;
			this.meet_b = true;
		}
		if(this.z>check){
			z = check;
			this.meet_b = true;
		}
		if(this.z<-check){
			z = -check;
			this.meet_b = true;
		}		
		if(meet_b){
			if(!set_tar){
				this.g_tar_angle = 180-this.g_Angle;
				this.set_tar = true;
			}	
		}
		else{
			meet_b = false;
			set_tar = false;
		}
	}
	
	public void checktarget(basecomponent t){
		double dis = this.countdistance(t);
		if(dis<find_r)this.moveto(t);
	}
	public void checkfire(basecomponent t){
		double dis = this.countdistance(t);
		if(dis<fire_r)this.locktarget(t);
	}
	
	public void attack() {
		Shell s = new Shell(fx,fy,fz,b_vx,b_vy,b_vz);
		PaintReady.b_list.add(s);
	}	
}
