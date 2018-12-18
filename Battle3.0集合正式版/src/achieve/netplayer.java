package achieve;

import Abstract.player;

public class netplayer extends player {
	    public float tx=0,ty=0;
	    public float power;
		private static netplayer p1;
		public float distance=30;
		private netplayer() {
			in = playground.m_pocket.get022();
			y = 0.5f;
			v = 1;
			check_r = 1.5f;
		}

		public static netplayer get() {
			if (p1 == null) {
				p1 = new netplayer();
			}
			return p1;
		}
		public boolean lifechange(){
			return true;	
		}
		public void set(float px,float py,float pz,float angle,float pw,float tx,float ty,float wait){
			
			this.x=px;
			this.y=py;
			this.z=pz;
			this.g_Angle=angle;
			this.power=pw;
			this.tx=tx;
			this.ty=ty;
			if(wait==1)
				wait_die=true;
			
				
		}

		@Override
		public void changeweapon(int n) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void attack() {
			if (life) {
				Shell s = new Shell(power, x,y,z,x
						+ (float) (5 * Math.sin(this.g_Angle / 180 * Math.PI))
						- tx * distance*15, y + ty * distance, z
						- (float) (5* Math.cos(this.g_Angle / 180 * Math.PI)));
				PaintReady.b_list.add(s);
			}
			
		}

}
