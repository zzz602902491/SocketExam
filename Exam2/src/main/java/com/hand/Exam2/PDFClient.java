package com.hand.Exam2;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class PDFClient {

	public static void main(String[] args) {
				try {
					Socket s = new Socket("127.0.0.1",23456);
					FileOutputStream fos = new FileOutputStream(new File("new_target.pdf"));
					InputStream is = s.getInputStream();
					BufferedInputStream bis = new BufferedInputStream(is);
					byte[] b= new byte[512];
					int len=0;
					while((len=bis.read(b))!=-1)
					{
						fos.write(b,0,len);
						fos.flush();
					}
					bis.close();
					is.close();
					fos.close();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
	}

}
