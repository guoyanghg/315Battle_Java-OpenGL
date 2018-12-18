package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

public class ObjReader {
	public int numv = 0;
	public int numvn = 0;
	public int numvt = 0;
	public int numf = 0;
	public float[][] listv;// points
	public float[][] listvn;// law
	public float[][] listvt;// texture
	public int[][] fv;// indexof points
	public int[][] fvn;// indexof law
	public int[][] fvt;// indexof texture
	String url = null;
	private float size;
	File file;
	FileReader fr;
	FileReader fr2;
	BufferedReader br;
	BufferedReader br2;

	public ObjReader(String str,float percent) throws FileNotFoundException {
		size=percent/100;
		url = str;
		file = new File(url);
		fr = new FileReader(file);
		fr2 = new FileReader(file);
		br = new BufferedReader(fr);
		br2 = new BufferedReader(fr2);
	}

	public void Findnum() throws IOException {
		String temp;
		temp = br.readLine();
		while (temp != null) {
			if (temp.length() < 1 || temp.charAt(0) == '#')// 过滤 不知道为什么就成功了
															// 读到空白行不是空啊？
															// 还有这样就成功了
			{
			} else if (temp.charAt(0) == 'v' && temp.charAt(1) != 't'
					&& temp.charAt(1) != 'n')
				numv++;
			else if (temp.charAt(1) == 'n')
				numvn++;
			else if (temp.charAt(0) == 'v' && temp.charAt(1) == 't') {
				numvt++;
			} else if (temp.charAt(0) == 'f')
				numf++;
			temp = br.readLine();
		}
		listv = new float[numv][3];
		listvn = new float[numvn][3];
		listvt = new float[numvt][3];
		fv = new int[numf][3];
		fvn = new int[numf][3];
		fvt = new int[numf][3];
	}

	public void read() throws IOException {
		Findnum();
		String temp;
		int i = 0, j = 0, k = 0, f = 0;
		temp = br2.readLine();
		while (temp != null) {

			if (temp.length() <= 1
					|| (temp.length() > 10 && temp.charAt(0) == '#' && temp
							.charAt(9) == 'B')) {
			} else if (temp.length() > 10 && temp.charAt(0) == '#'
					&& temp.charAt(9) == 'B') {
				// 过滤骨骼面
				break;
			} else if (temp.charAt(0) == 'v' && temp.charAt(1) != 't'
					&& temp.charAt(1) != 'n') {
				String[] str = temp.split(" ");
				for (int w = 2; w < str.length; w++) {
					if (w == 2)
						listv[i][w - 2] = Float.parseFloat(str[w])*size;
					else
						listv[i][w - 2] = Float.parseFloat(str[w])*size;
				}
				i++;
			} else if (temp.charAt(1) == 'n') {
				String[] str = temp.split(" ");
				for (int w = 1; w < str.length; w++) {

					listvn[j][w - 1] = Float.parseFloat(str[w]);
				}
				j++;
			} else if (temp.charAt(0) == 'v' && temp.charAt(1) == 't') {
				String[] str = temp.split(" ");
				for (int w = 1; w < str.length; w++) {

					listvt[k][w - 1] = Float.parseFloat(str[w]);
				}
				k++;

			} else if (temp.charAt(0) == 'f') {
				// System.out.println(temp);
				String[] str = temp.split(" ");
				//for(int w=0;w<str.length;w++){
					//System.out.println(str[w]);
				//}
				String[] str1 = str[1].split("/");
				String[] str2 = str[2].split("/");
				String[] str3 = str[3].split("/");

				fv[f][0] = Integer.parseInt(str1[0]);
				fv[f][1] = Integer.parseInt(str2[0]);
				fv[f][2] = Integer.parseInt(str3[0]);

				fvn[f][0] = Integer.parseInt(str1[2]);
				fvn[f][1] = Integer.parseInt(str2[2]);
				fvn[f][2] = Integer.parseInt(str3[2]);

				if (!str1[1].equals("")) {

					fvt[f][0] = Integer.parseInt(str1[1]);
					fvt[f][1] = Integer.parseInt(str2[1]);
					fvt[f][2] = Integer.parseInt(str3[1]);
				} else {
					fvt[f][0] = -1;
					fvt[f][1] = -1;
					fvt[f][2] = -1;
				}
				f++;
			}
			temp = br2.readLine();
		}
	}

	public void draw(GL2 gl, int texture) {
		gl.glPushMatrix();
		gl.glBegin(gl.GL_TRIANGLES);
		gl.glBindTexture(gl.GL_TEXTURE_2D, texture);
		gl.glEnable(gl.GL_TEXTURE_2D);

		for (int i = 0; i < numf; i++) {
			// 测试用品 还未完成纹理和材质

			gl.glNormal3f(listvn[fvn[i][0] - 1][0], listvn[fvn[i][0] - 1][1],
					listvn[fvn[i][0] - 1][2]);
			gl.glVertex3f(listv[fv[i][0] - 1][0], listv[fv[i][0] - 1][1],
					listv[fv[i][0] - 1][2]);
			if (fvt[i][0] != -1)// 无纹理直接用
				gl.glTexCoord2f(listvt[fvt[i][0] - 1][0], listvt[fvt[i][0]-1][1]);

			gl.glNormal3f(listvn[fvn[i][1] - 1][0], listvn[fvn[i][1] - 1][1],
					listvn[fvn[i][1] - 1][2]);

			gl.glVertex3f(listv[fv[i][1] - 1][0], listv[fv[i][1] - 1][1],
					listv[fv[i][1] - 1][2]);
			if (fvt[i][0] != -1)
				gl.glTexCoord2f(listvt[fvt[i][1] - 1][0],
						listvt[fvt[i][1] - 1][1]);

			gl.glNormal3f(listvn[fvn[i][2] - 1][0], listvn[fvn[i][2] - 1][1],
					listvn[fvn[i][2] - 1][2]);
			gl.glVertex3f(listv[fv[i][2] - 1][0], listv[fv[i][2] - 1][1],
					listv[fv[i][2] - 1][2]);
			if (fvt[i][0] != -1)
				gl.glTexCoord2f(listvt[fvt[i][2] - 1][0], listvt[fvt[i][2]-1][1]);

		}
		gl.glPopMatrix();
		gl.glEnd();
	}
}
