package com.hand.Exam2;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class PDFServer 
{
    public static void main( String[] args )
    {
    		try {
				ServerSocket ss = new ServerSocket(23456);
				Socket s = ss.accept();
				OutputStream os = s.getOutputStream();
				BufferedOutputStream bos = new BufferedOutputStream(os);
				FileInputStream fis = new FileInputStream(new File("../Exam1/target.pdf"));
				byte[] b = new byte[512];
				int len=0;
				while((len=fis.read(b))!=-1)
				{
					bos.write(b,0,len);
					bos.flush();
				}
				s.close();
    		} catch (IOException e) {
				e.printStackTrace();
			}
    }
}
