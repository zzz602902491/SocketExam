package com.hand.Exam1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class App 
{
    public static void main( String[] args )
    {
    	new Download().start();
    }
}
class  Download extends Thread
{
	@Override
	public void run() {
		try {
			URL url = new URL("http://files.saas.hand-china.com/java/target.pdf");
			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();
			BufferedInputStream br = new BufferedInputStream(is);
			BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream("target.pdf"));
			byte[] c = new byte[512];
			int count=0;
			while((count=br.read(c))!=-1)
			{
				bw.write(c);
				bw.flush();
			}
			bw.close();
			br.close();
			is.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}