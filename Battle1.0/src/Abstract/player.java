package Abstract;

import javax.media.opengl.GL2;

public abstract class player extends basecomponent{//姿态参数
    public float rad_xz;                            //xz角度(弧度制)
    public float rad_yz;                            //yz角度（弧度制）
    public float g_Angle;                           //左右转角
    public float g_elev;                            //仰俯角
	public player() {
        g_Angle = 0;      //方位角
        g_elev = 0;       //俯仰角
	}
	
	public abstract void draw(GL2 gl);
	public abstract void move(boolean stright);
	
	public abstract void changeweapon(int n);
	
	public abstract void attack();
	
}
