package Abstract;

import javax.media.opengl.GL2;

public abstract class enemy extends basecomponent{
	float x,y,z;
    float rad_xz;                            //xz角度(弧度制)
    float rad_yz;                            //yz角度（弧度制）
    float g_Angle;                           //左右转角
    float g_elev;                            //仰俯角
	
	
	public enemy() {
		 g_Angle = 0;      //方位角
	     g_elev = 0;       //俯仰角
	}
}
