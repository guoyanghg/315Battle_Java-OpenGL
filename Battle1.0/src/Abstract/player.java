package Abstract;

import javax.media.opengl.GL2;

public abstract class player extends basecomponent{//��̬����
    public float rad_xz;                            //xz�Ƕ�(������)
    public float rad_yz;                            //yz�Ƕȣ������ƣ�
    public float g_Angle;                           //����ת��
    public float g_elev;                            //������
	public player() {
        g_Angle = 0;      //��λ��
        g_elev = 0;       //������
	}
	
	public abstract void draw(GL2 gl);
	public abstract void move(boolean stright);
	
	public abstract void changeweapon(int n);
	
	public abstract void attack();
	
}
