package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class MtlReader {
	int mtlnumber=0;
	float ns[];//反射指数
	float ni[];//折射值
	float d[];//渐隐指数
	float tr[];//
	float[][] tf;//滤光透射率
	int illum[];//光照模型
	float[][] ka;//环境反射 
	float[][] kd;//漫反射
	float[][] ks;//镜面反射
	float[][] ke;//
	String url;
	FileReader fr;
	FileReader fr2;
	BufferedReader br;
	BufferedReader br2;
	File file;
	public MtlReader(String filename) throws FileNotFoundException {
		 url=filename;
		 file= new File(url);
		 fr= new FileReader(file);
		 fr2= new FileReader(file);
		 br= new BufferedReader(fr); 
		 br2= new BufferedReader(fr2); 	
		
	}
	public void Findnum() throws IOException{
		String temp;
		temp = br.readLine();
		 while(temp!=null){
			 if(temp.length()<1||temp.charAt(0)=='#')//过滤 不知道为什么就成功了 读到空白行不是空啊？ 还有这样就成功了
			 {}	 
		     else if(temp.charAt(0)=='n'&&temp.charAt(1)=='e'&&temp.charAt(2)=='w')
				 mtlnumber++;
			
			 temp=br.readLine();			
		 }
		 ns= new float[mtlnumber];
		 ni= new float[mtlnumber];
		 d= new float[mtlnumber];
		 tr= new float[mtlnumber];
		 tf= new float[mtlnumber][3];
		 illum= new int[mtlnumber];
		 ka= new float[mtlnumber][3];
		 kd= new float[mtlnumber][3];
		 ks= new float[mtlnumber][3];
		 ke= new float[mtlnumber][3];
	}
	public void read() throws IOException{
		 Findnum();
		 String temp;
		 int i=0,j=0,k=0,a=0,b=0,c=0,dog=0,e=0,f=0,g=0;
		 temp = br2.readLine();
			 while(temp!=null){
				
				 if(temp.length()<1||temp.charAt(0)=='#')//过滤 不知道为什么就成功了 读到空白行不是空啊？ 还有这样就成功了
				 {}	 
			     else if(temp.charAt(1)=='N'&&temp.charAt(2)=='s')
			     {  
			    	String[] str =temp.split(" ");
			    	ns[i]=Float.parseFloat(str[1]);
			    	i++;
			     }
				 else if(temp.charAt(1)=='N'&&temp.charAt(2)=='i')
				 {   
					 String[] str =temp.split(" ");
				     ni[j]=Float.parseFloat(str[1]);
				     j++;
					 					
				 }
				 else if(temp.charAt(1)=='d')
				 {
					 String[] str =temp.split(" ");
				     d[k]=Float.parseFloat(str[1]);
				     k++;
					 
				 }
				 else if(temp.charAt(1)=='T'&&temp.charAt(2)=='r')
				 {  
					 String[] str =temp.split(" ");
				     tr[a]=Float.parseFloat(str[1]);
				     a++;
					 
				 }
				 else if(temp.charAt(1)=='T'&&temp.charAt(2)=='f')
				 {   
					 String[] str =temp.split(" ");
					 for(int w=1;w<str.length;w++){
						 tf[b][w-1]=Float.parseFloat(str[w]);
					 }
					 b++;					 
				 }
				 else if(temp.charAt(1)=='i')
				 {
					 String[] str =temp.split(" ");
				     illum[c]=Integer.parseInt(str[1]);
				     c++;
					 
				 }
				 else if(temp.charAt(1)=='K'&&temp.charAt(2)=='a')
			     {  
					 String[] str =temp.split(" ");
					 for(int w=1;w<str.length;w++){
						 ka[dog][w-1]=Float.parseFloat(str[w]);
					 }
					 dog++;			
			    	
			     }
				 else if(temp.charAt(1)=='K'&&temp.charAt(2)=='d')
			     {  
					 String[] str =temp.split(" ");
					 for(int w=1;w<str.length;w++){
						 kd[e][w-1]=Float.parseFloat(str[w]);
					 }
					 e++;			
			    	
			     }
				 else if(temp.charAt(1)=='K'&&temp.charAt(2)=='s')
			     {  
					 String[] str =temp.split(" ");
					 for(int w=1;w<str.length;w++){
						 ks[f][w-1]=Float.parseFloat(str[w]);
					 }
					 f++;			
			    	
			     }
				 else if(temp.charAt(1)=='K'&&temp.charAt(2)=='e')
			     {  
					 String[] str =temp.split(" ");
					 for(int w=1;w<str.length;w++){
						 ke[g][w-1]=Float.parseFloat(str[w]);
					 }
					 g++;			
			    	
			     }
				 temp=br2.readLine();			
			 }
	}
	

}
