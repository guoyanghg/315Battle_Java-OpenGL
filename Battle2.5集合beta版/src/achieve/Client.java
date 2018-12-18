package achieve;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	String ip;
	Socket s;
	int port;
	Mythread t;
	public static boolean flag=false;
	public boolean first = true;
	
	public Client(String i,int p) {
		ip=i;
		port=p;
	}
	public void connect() {
		try {
			s= new Socket(ip, port);
		} catch (UnknownHostException e) {
			System.out.println("无主机");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GLRender.issingle=false;		
		PaintReady.np= netplayer.get();
		flag=true;
		t= new Mythread();
		t.start();
		PaintReady.link = true;
		playground.ct.show(playground.get().getContentPane(), "gamepanel");
	}
	public void fresh()
	{
		playertest.get().refresh();
		netplayer.get().refresh();
	}
	public class Mythread extends Thread{
		//BufferedReader in;
		//PrintWriter out;
		DataInputStream in;
		DataOutputStream out;	
		float[] buffer= new float[10];
		int bnum=0;
		public Mythread() {
			try {
				in = new DataInputStream(s.getInputStream());
				out= new DataOutputStream(s.getOutputStream());
				//in=new BufferedReader(new InputStreamReader(s.getInputStream()));
				//out= new PrintWriter((s.getOutputStream(), true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		@Override
		public void run() {
			while(flag){
				     //System.out.println("传送一次数据");
				 	 
				 try {
				
						out.writeFloat(playertest.get().g_Angle);
						out.writeFloat(playertest.get().x);
						out.writeFloat(playertest.get().y);
						out.writeFloat(playertest.get().z);	
						if(playertest.get().attack)
						{ 
							out.writeFloat(1.0f);
							playertest.get().attack=false;
						}
						else
							out.writeFloat(0.0f);
						out.writeFloat(playertest.get().Wc.power);
						out.writeFloat(playertest.get().Wc.tx);
						out.writeFloat(playertest.get().Wc.ty);
						if(playertest.get().wait_die)
						out.writeFloat(1.0f);
						else
						out.writeFloat(0.0f);
						
						out.writeFloat(PaintReady.restart);
					
					
					for(;bnum<10;bnum++){
						buffer[bnum]=in.readFloat();
						System.out.println(buffer[bnum]);
					}
					
					if(bnum==10)
					{
						if(buffer[4]==1)
							netplayer.get().attack();
						
						PaintReady.np.set(-buffer[1],buffer[2],-buffer[3],buffer[0]+180,buffer[5],-buffer[6],buffer[7],buffer[8]);//设置坐标
						PaintReady.np_restart=(int) buffer[9];
						bnum=0;
					}
					if(PaintReady.restart==3&&PaintReady.np_restart==3&&first)
										{
										  fresh();first=false;
										}
										if(!first){
										   PaintReady.restart=2;
										   PaintReady.np_restart=2;
										   first=true;
										}
					
                    // out.writeFloat(3);
					 //System.out.println(in.readFloat());
				
								
					
				 }catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				s.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
}	
	
	
	


