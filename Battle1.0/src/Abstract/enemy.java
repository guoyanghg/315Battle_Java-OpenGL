package Abstract;

import javax.media.opengl.GL2;

public abstract class enemy extends basecomponent{
	float x,y,z;
    float rad_xz;                            //xz�Ƕ�(������)
    float rad_yz;                            //yz�Ƕȣ������ƣ�
    float g_Angle;                           //����ת��
    float g_elev;                            //������
	
	
	public enemy() {
		 g_Angle = 0;      //��λ��
	     g_elev = 0;       //������
	}
}
