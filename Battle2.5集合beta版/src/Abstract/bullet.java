package Abstract;

import javax.media.opengl.GL2;

public abstract class bullet extends basecomponent{

	public class rdata{
		public float angle_law,r_x,r_y,r_z;
		void count(float x1,float y1,float z1,float x,float y,float z){
			double r1 = Math.sqrt(x1*x1+y1*y1+z1*z1);
			double r2 = Math.sqrt(x*x+y*y+z*z);
			double cos = (x1*x+y1*y+z1*z)/r1/r2;
			angle_law = (float)( Math.acos(cos)+Math.PI);
			
			r_x = y1*z-z1*y;
			r_z = x1*y-y1*x;
			r_y = z1*x-x1*z;
		}
		
		
	}
	protected rdata rd = new rdata();
	protected float v,vx,vz,vy=0;
	protected double angle_xoz,angle_y;//ÇòÃæ×ø±ê
	public int damage;
	
	
	public abstract void draw(GL2 gl);
	public void updata(){
		x+=vx;
		z+=vz;
		y+=vy;
		vy-=0.005;
		
		rd.count(0,0,-1,vx, vy, vz);
	//	System.out.println(rd.angle_law+" "+rd.r_x+" "+rd.r_y+" "+rd.r_z);
	}
	
	public void settarget(float x,float y,float z){
		double r_xz = Math.pow((x-this.x), 2) + Math.pow((z-this.z), 2);		
		double r_y = r_xz+Math.pow((y-this.y), 2);
		
		if(r_xz==0){
			angle_xoz=0;
			angle_y = 0;
		}
		else{
			double cxoy = (x-this.x)/Math.sqrt(r_xz);
			this.angle_xoz = Math.acos(cxoy);
			double cz = (y-this.y)/Math.sqrt(r_y);
			this.angle_y = Math.acos(cz);
			if((this.z-z)<0) this.angle_xoz = 2*Math.PI-this.angle_xoz;
		}
		
		vx = (float)(v*Math.sin(angle_y)*Math.cos(angle_xoz));
		vz = (float)(v*Math.sin(angle_y)*Math.sin(angle_xoz));
		vy = (float)(v*Math.cos(angle_y));
				
	}
	
	public void settarget(float xoz,float y){
		this.angle_xoz = xoz;
		this.angle_y = y;
	}
}
