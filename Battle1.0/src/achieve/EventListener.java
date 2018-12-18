package achieve;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;



public class EventListener implements MouseListener,MouseMotionListener,KeyListener{
	playertest p = playertest.get();
	Weaponcontrol W = Weaponcontrol.getWc();

	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
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

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_UP){
			p.move(true);
		}
		if(key == KeyEvent.VK_DOWN){
			p.move(false);
		}
		if(key == KeyEvent.VK_LEFT){
			p.g_Angle++;
		}
		if(key == KeyEvent.VK_RIGHT){
			p.g_Angle--;
		}
		if(e.getKeyChar() == '/'){
			System.out.println(W.tx+" "+W.ty);
		}
		if(e.getKeyChar() == '6'){
			W.tx+=0.001f;			
		}
		if(e.getKeyChar() == '5'){
			p.attack();
		}
		if(e.getKeyChar() == '4'){
			W.tx-=0.001f;
		}
		if(e.getKeyChar() == '8'){
			W.ty+=0.001;
		}
		if(e.getKeyChar() == '2'){
			W.ty-=0.001;
		}
		if(e.getKeyChar() == '7'){
			W.power-=0.1;
		}
		if(e.getKeyChar() == '9'){
			W.power+=0.1;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
