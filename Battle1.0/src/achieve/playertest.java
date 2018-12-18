package achieve;

import java.util.ArrayList;

import javax.media.opengl.GL2;

import common.ObjReader;
import Abstract.player;


public class playertest extends player{
	private static playertest p1;
	ObjReader in;
	Weaponcontrol Wc;
	int texture;
	float sv = 10;
	ArrayList<Shell> shell = new ArrayList<Shell>();
	ArrayList<wave> road = new ArrayList<wave>();
	
	private playertest(){
		in = playground.m_pocket.get022();
		Wc = Weaponcontrol.getWc();
		y = 0.5f;
	}
	
	public static playertest get(){
		if(p1==null){
			p1 = new playertest();
		}
		return p1;
	}
	@Override
	public void draw(GL2 gl) {
		if(road.size()>0){
			if(road.get(0).color<=0)road.remove(0);
		}
				
		for(Shell sx:shell){
			sx.draw(gl);
		}

		gl.glPushAttrib(GL2.GL_CURRENT_BIT);
		gl.glPushMatrix();
		gl.glTranslatef(x, y, z);
	    gl.glColor3f(0.5f,0.5f, 0);                                
		gl.glRotatef(180+g_Angle, 0, 1.0f,0.0f);
        in.draw(gl,texture);
               
		gl.glPopAttrib();
		gl.glPopMatrix();
		
		for(wave w:road)w.draw(gl);
		
	}

	@Override
	public void move(boolean stright) {
		storeroad();
		
		if(stright){
			this.x+=Math.sin(g_Angle/180*Math.PI);
		    this.z+=Math.cos(g_Angle/180*Math.PI);
		}
		else{
			this.x-=Math.sin(g_Angle/180*Math.PI);
		    this.z-=Math.cos(g_Angle/180*Math.PI);
		}
		
	}

	public void storeroad(){
		wave temp = new wave(x,y,z);
		road.add(temp);
	}
	
	@Override
	public void changeweapon(int n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack() {
	Shell s = new Shell(Wc.power,x,y,z,x-(float)Math.sin(this.g_Angle/180*Math.PI),y+Wc.ty*30,z-1);
	shell.add(s);                           
	}

}
