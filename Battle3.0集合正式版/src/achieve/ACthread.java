package achieve;

public class ACthread extends Thread{
	@Override
	public void run() {
		 playground.s.accept();
	}

}
