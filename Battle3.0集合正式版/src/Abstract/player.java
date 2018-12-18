package Abstract;

import java.util.ArrayList;

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.gl2.GLUT;

import common.ObjReader;
import achieve.PaintReady;
import achieve.Shell;
import achieve.Weaponcontrol;
import achieve.audio;
import achieve.wave;

public abstract class player extends basecomponent{//姿态参数
    public float rad_xz;                            //xz角度(弧度制)
    public float rad_yz;                            //yz角度（弧度制）
    public float g_Angle;                           //左右转角
    public float g_elev;                            //仰俯角
    public boolean wait_die = false;
    public boolean attack=false;
    //boom control
    protected float count = 0;
	protected float color = 1;
	protected float r = 0;
    public float v;
    
    protected ObjReader in;
	protected int texture;
//	public ArrayList<Shell> shell = new ArrayList<Shell>();
	
	public player() {
        g_Angle = 0;      //方位角
        g_elev = 0;       //俯仰角
        this.lifepoint = 200;
	}
	public void refresh(){
		   x=0;
		   z=0;
		   y = 0.5f;
		   v = 1;
		   this.lifepoint = 200;
		   life = true;
		   wait_die = false;
		   g_Angle = 0;      //方位角
	       g_elev = 0; 
	       
	}
	
	
	public abstract void changeweapon(int n);
	
	public abstract void attack();
	
	public void draw(GL2 gl) {
		if(!wait_die);
		else {
			y-=0.03f;
			count++;
			if(count>10){
				storeroad();
				count = 0;
			}
			if(y<=-6)life = false;
			if(!boom_al)boom(gl);
		}
		
		if(damage_al)this.damage(gl);
		
		
		gl.glPushAttrib(GL2.GL_CURRENT_BIT);
		gl.glPushMatrix();
		gl.glTranslatef(x, y, z);
	    gl.glColor3f(0.5f,0.5f, 0);                                
		gl.glRotatef(180+g_Angle, 0, 1.0f,0.0f);
        in.draw(gl,texture);
        
		gl.glPopAttrib();
		gl.glPopMatrix();
	}

	protected boolean boom_al = false;
	public void boom(GL2 gl){
		GLUT glut = new GLUT();
		gl.glPushAttrib(gl.GL_CURRENT_BIT);
		gl.glPushMatrix();
		gl.glColor4f(1,0,0,color);
		gl.glTranslatef(x, 0.5f, z);
		glut.glutSolidSphere(r,20, 20);
		gl.glPopAttrib();
		gl.glPopMatrix();
		
		if(r<5){
			r+=0.1;
		}
		else {
			r = 0;
			boom_al = true;
			audio.getaudio().getboom().play();
		}
	}
	
	public boolean damage_al = false;
	public void damage(GL2 gl){
		GLUT glut = new GLUT();
		gl.glPushAttrib(gl.GL_CURRENT_BIT);
		gl.glPushMatrix();
		gl.glColor4f(1,0,0,color);
		gl.glTranslatef(x,-0.1f, z);
		gl.glRotatef(-90, 1, 0, 0);
		glut.glutWireCone(2, r, 20, 20);
		gl.glPopAttrib();
		gl.glPopMatrix();
		
		if(r<5&&damage_al){
			r+=0.5;
		}
		else {
			r = 0;
			damage_al = false;
			audio.getaudio().getboom().play();
		}
	}
	
	
	public boolean checkcrock(basecomponent b){
		float d = (this.x-b.x)* (this.x-b.x)+ (this.y-b.y)* (this.y-b.y)+ (this.z-b.z)* (this.z-b.z);
		if(Math.sqrt(d)<=this.check_r+b.check_r)return true;
		else return false;
	}
	
	public void move(boolean stright) {
		if(life){
		storeroad();	
		if(stright){
			this.x+=v*Math.sin(g_Angle/180*Math.PI);
		    this.z+=v*Math.cos(g_Angle/180*Math.PI);
		    if(this.x>PaintReady.MAP*4/5)this.x=PaintReady.MAP*4/5;
		    if(this.z>PaintReady.MAP*4/5)this.z=PaintReady.MAP*4/5;
		    if(this.x<-PaintReady.MAP*4/5)this.x=-PaintReady.MAP*4/5;
		    if(this.z<-PaintReady.MAP*4/5)this.z=-PaintReady.MAP*4/5;
		}
		else{
			this.x-=v*Math.sin(g_Angle/180*Math.PI)/3;
		    this.z-=v*Math.cos(g_Angle/180*Math.PI)/3;
		    if(this.x>PaintReady.MAP*4/5)this.x=PaintReady.MAP*4/5;
		    if(this.z>PaintReady.MAP*4/5)this.z=PaintReady.MAP*4/5;
		    if(this.x<-PaintReady.MAP*4/5)this.x=-PaintReady.MAP*4/5;
		    if(this.z<-PaintReady.MAP*4/5)this.z=-PaintReady.MAP*4/5;
		}
		}
	}
	public void storeroad(){
		wave temp = new wave(x,0.1f,z);
		PaintReady.road.add(temp);
	}	
}
