package achieve;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;



public class EventListener implements MouseListener,MouseMotionListener,KeyListener,MouseWheelListener{
	   playertest p = playertest.get();
	   Weaponcontrol wc = Weaponcontrol.getWc();
	    int initialpx=0;
	    int initialpy=0;
	    int lengthx=0;
	    int lengthy=0;
	    int lx;
		int ly;
		int lz;
		static boolean left=false;
		static boolean right=false;
		double initheta=Math.PI;
		double inifi=1.5;
		
		 public void update_eyep(){
		    	PaintReady.eyepz=(float) (PaintReady.eye_distance*Math.cos(initheta)*Math.sin(inifi))+p.z;
		    	PaintReady.eyepx=(float) (PaintReady.eye_distance*Math.sin(initheta)*Math.sin(inifi))+p.x;
		    	PaintReady.eyepy=(float) (PaintReady.eye_distance*Math.cos(inifi))+0.5f;
		    }
		
	    public void change(double angletheta,double anglefi){
	    	if(p.wait_die);
	    	else{
	    	initheta=initheta+angletheta; 	
	    	//playertest.rotatheta=(float) (initheta*180/Math.PI);
	    	inifi=inifi-anglefi;
	    	if(inifi<0.001)
	    		inifi=0.001;
	    	else if(inifi>Math.PI-0.001)
	    		inifi=Math.PI-0.001;
	    	update_eyep();
	    	}
	    }
	       
	    public void changeandturn(double angletheta,double anglefi){
	    	if(p.wait_die);
	    	else{
	    	initheta=initheta+angletheta; 	
	    	p.g_Angle=(float) (initheta*180/Math.PI)-180;
	    	inifi=1.5;
	    	if(inifi<0.001)
	    		inifi=0.001;
	    	else if(inifi>Math.PI-0.001)
	    		inifi=Math.PI-0.001;
	    	update_eyep();
	    	}
	    }
	 
		public void mouseDragged(MouseEvent e){
			int x= e.getX();int y= e.getY();
			if(Math.abs(x-initialpx)<lengthx)
				initialpx=x;
			if(Math.abs(y-initialpy)<lengthy)
					initialpy=y;		
			if(left==true)
			change((initialpx-x)*Math.PI/10000,(y-initialpy)*Math.PI/10000);
			if(right==true)
			changeandturn((initialpx-x)*Math.PI/10000, (y-initialpy)*Math.PI/10000);	
			lengthx=Math.abs(x-initialpx);
			lengthy=Math.abs(y-initialpy);
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			initialpx=e.getX();
			initialpy=e.getY();
			if(e.getButton()==e.BUTTON1)left=true;	
			if(e.getButton()==e.BUTTON3) right=true;		
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		   if(e.getButton()==e.BUTTON1)left=false;		
		   if(e.getButton()==e.BUTTON3)right=false;
	}			


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	int temp;
	boolean change = false;
	@Override
	public void keyPressed(KeyEvent e) {
		int key;
		if(!p.wait_die)
		key = e.getKeyCode();
		else key = -1;
		if(key == KeyEvent.VK_W){
			p.move(true);
			//PaintReady.needcheck=true;//原来的绑定
			update_eyep();
		}
		if(key == KeyEvent.VK_S){
			p.move(false);
			update_eyep();
		}
		if(key == KeyEvent.VK_A){
			wc.ty-=0.0005f;
			if(wc.ty<-0.009)wc.ty = -0.009f;
		}
		if(key == KeyEvent.VK_D){
			wc.ty+=0.0005f;
			if(wc.ty>0.009)wc.ty = 0.009f;
		}
		if(key == KeyEvent.VK_Q){
			wc.tx-=0.0005f;
			if(wc.tx<-0.009)wc.tx = -0.009f;
		}
		if(key == KeyEvent.VK_E){
			wc.tx+=0.0005f;
			if(wc.tx>0.009)wc.tx = 0.009f;
		}
		
		if(key == KeyEvent.VK_R){
			wc.power+=0.1f;
			if(wc.power>2*wc.power_max)wc.power = 2*wc.power_max;
		}
		if(key == KeyEvent.VK_F){
			wc.power-=0.1f;
			if(wc.power<0.1)wc.power = 0.1f;
		}
		if(key == 32){
			p.attack();
		}
		if(key == KeyEvent.VK_C){
			wc.tx = 0;
		}
		if(key == KeyEvent.VK_X){
			if(!change){
				temp = PaintReady.eye_distance;
			    PaintReady.eye_distance = 100;
			    change = true;
			}
			else {
				PaintReady.eye_distance = temp;
				change = false;
			}
		}
		if(key == KeyEvent.VK_M){
			System.out.println(p.x+" "+p.z);
		}
		if(key == KeyEvent.VK_N){
			
		}
		if(key == KeyEvent.VK_P){
			System.out.println();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseWheelMoved(MouseWheelEvent e) {
		  lx=(int) (p.x-PaintReady.eyepx);
		  ly=(int) (p.y-PaintReady.eyepy);
		  lz=(int) (p.z-PaintReady.eyepz);
		  double cosx=lx/Math.sqrt(lx*lx+ly*ly+lz*lz);
		  double cosy=ly/Math.sqrt(lx*lx+ly*ly+lz*lz);
		  double cosz=lz/Math.sqrt(lx*lx+ly*ly+lz*lz);
		 int r= e.getWheelRotation();
		 PaintReady.eyepx=(float) (PaintReady.eyepx-r*cosx);
		 PaintReady.eyepy=(float) (PaintReady.eyepy-r*cosy);
		 PaintReady.eyepz=(float) (PaintReady.eyepz-r*cosz);
		 PaintReady.eye_distance=PaintReady.eye_distance+r;			
		}

}
