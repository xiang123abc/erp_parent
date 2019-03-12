package cn.ecut.test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class TestDemo {
	public static void main(String[] args){
		//
        BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream("/glass.json"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //
        //
        byte[] bytes = new byte[2048];
        String str = null;
        //
        int n = -1;
        //
        try {
			while ((n = in.read(bytes,0,bytes.length)) != -1) {
			    //
			     str = new String(bytes,0,n,"GBK");
			    System.out.println(str);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //
        try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(str);
		//String jsonString = JSON.toJSONString(str, SerializerFeature.DisableCircularReferenceDetect);			
		//System.out.println(jsonString);
	}
}
