package achieve;

import Abstract.player;

public class playertest extends player {
	private static playertest p1;
	Weaponcontrol Wc;
	public float distance=30f;
	private playertest() {
		in = playground.m_pocket.get022();
		Wc = Weaponcontrol.getWc();
		y = 0.5f;
		v = 0.5f;
	//	check_r = 1.5f;
	}

	public static playertest get() {
		if (p1 == null) {
			p1 = new playertest();
		}
		return p1;
	}

	@Override
	public void changeweapon(int n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attack() {
		if (life) {
		/*	Shell s = new Shell(Wc.power, x
					+ (float) ((this.check_r+0.1) * Math.sin(this.g_Angle / 180
							* Math.PI)),y, z
					- (float) ((this.check_r+0.1) * Math.cos(this.g_Angle / 180
							* Math.PI)), x
					+ (float) ((this.check_r+0.2f) * Math.sin(this.g_Angle / 180 * Math.PI))
					- Wc.tx * distance, y + Wc.ty * distance, z
					- (float) ((this.check_r+0.2f)* Math.cos(this.g_Angle / 180 * Math.PI)));*/
			
			Shell s = new Shell(Wc.power, x,y,z,x
					+ (float) (5 * Math.sin(this.g_Angle / 180 * Math.PI))
					- Wc.tx * distance*15, y + Wc.ty * distance, z
					- (float) (5* Math.cos(this.g_Angle / 180 * Math.PI)));
			PaintReady.mb_list.add(s);
		}
	}

}
