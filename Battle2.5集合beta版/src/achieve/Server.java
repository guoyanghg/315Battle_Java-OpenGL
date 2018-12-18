package achieve;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.omg.CORBA_2_3.portable.InputStream;
import org.omg.CORBA_2_3.portable.OutputStream;

public class Server {
	public ServerSocket host;
	private static int portnumber=11500;
	private Socket s;
	private Mythread t;
	public static boolean flag=false;
	public boolean first = true;
	public Server() {
		try {
			host= new ServerSocket(portnumber);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void accept() {
		 try {
			s = host.accept();
			System.out.println("建立连接");
			PaintReady.np= netplayer.get();
			//然后将netplayer生成
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 GLRender.issingle=false; 
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
				out= new DataOutputStream( s.getOutputStream());
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
				  //判定字节流剩余长度未完成
				
				  try {
					// System.out.println(11);
				
					
					  for(;bnum<10;bnum++){
						buffer[bnum]=in.readFloat();
						//System.out.println(buffer[bnum]);
					 }
		
					if(bnum==10)
					{   
						//System.out.println("set");
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
							
								
						
						
					//System.out.println(in.readFloat());
					//out.println("caonidaye");
					//out.writeFloat(2);
					
				
					
				} catch (IOException e) {
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
