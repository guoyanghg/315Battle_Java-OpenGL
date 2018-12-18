package Abstract;

import java.io.FileNotFoundException;
import java.io.IOException;

import common.ObjReader;

public class modelpocket {
	ObjReader quzhu_m;
	ObjReader ship1m_m;
	ObjReader pula_m;
	String ship1m = "ship1m.obj",quzhu= "quzhu.obj",pula= "pula.obj";
	public modelpocket() throws FileNotFoundException{
		ship1m_m = new ObjReader(ship1m,0.5f);
		pula_m = new ObjReader(pula,0.1f);
		quzhu_m = new ObjReader(quzhu,0.1f);	
		try {
			this.ship1m_m.read();
			this.quzhu_m.read();
			this.pula_m.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ObjReader get022(){
		return this.ship1m_m;
	}
	public ObjReader getquzhu(){
		return this.quzhu_m;
	}
	public ObjReader getpula(){
		return this.pula_m;
	}
}
