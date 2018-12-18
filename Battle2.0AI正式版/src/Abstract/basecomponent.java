package Abstract;

import java.io.IOException;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.gl2.GLUT;
import common.TextureReader;

public abstract class basecomponent {
                            
    public float x=0;                               //绘制目标的坐标（世界坐标系）
    public float y=0;
    public float z=0;  
    public float check_r;
    public  int lifepoint;
    public boolean life = true;
    
    public double countdistance(basecomponent b){
    	double out = Math.sqrt( (this.x-b.x)* (this.x-b.x)+ (this.y-b.y)* (this.y-b.y)+ (this.z-b.z)* (this.z-b.z));
    	return out;
    }
}



