// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 28.06.2007 13:51:36

// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!

// Decompiler options: packimports(3) 

// Source File Name:   Util.java



package com.teckwave.Protokoll;



import java.io.*;

import java.net.*;



public class Util

{



    Util()

    {

    }



    public static String GetMyPattern()

    {

        String s = new String("");

        try

        {

            FileReader filereader = new FileReader("user.key");

            int i;

            while((i = filereader.read()) != -1) 

                s = s+Character.toString((char)i);

        }

        catch(Exception exception) { }

        return s;

    }



    private static int[] StringToIntArr(String s)

    {

        int ai[] = new int[s.length()];

        for(int i = 0; i <= s.length() - 1; i++)

            ai[i] = s.charAt(i);



        return ai;

    }



    public static byte[] XorPattern(byte abyte0[], byte abyte1[])

    {

        int i = abyte0.length >= abyte1.length ? abyte1.length : abyte0.length;

        byte abyte2[] = new byte[i];

        for(int j = 0; j < i; j++)

            abyte2[j] = (byte)((abyte0[j] ^ abyte1[j]) & 0xff);



        return abyte2;

    }



    public static byte[] Crypt(String s, byte abyte0[])

    {

        byte abyte1[] = XorPattern(StrToByteArr(s), abyte0);

        return abyte1;

    }



    public static byte[] StrToByteArr(String s)

    {

        byte abyte0[] = new byte[s.length()];

        int i = 65280;

        char c = '\377';

        boolean flag = false;

        for(int j = 0; j <= s.length() - 1; j++)

            abyte0[j] = (byte)s.charAt(j);



        return abyte0;

    }



    public static int[] ByteToInt(byte abyte0[])

    {

        int ai[] = new int[abyte0.length / 2];

        int i = 0;

        for(int j = 0; j < abyte0.length - 1; j += 2)

        {

            ai[i] = abyte0[j];

            ai[i] <<= 8;

            ai[i] &= abyte0[j + 1];

            i++;

        }



        return ai;

    }



    public static String IntArrToString(int ai[])

    {

        String s = "";

        for(int i = 0; i < ai.length; i++)

            s = s+Character.toString((char)ai[i]);



        return s;

    }



    public static String DownloadURL(String s)

    {

        String s1 = "0";

        try

        {

            InputStream inputstream = null;

            URL url = new URL(s);

            inputstream = url.openStream();

            DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(inputstream));

            String s2;

            while((s2 = datainputstream.readLine()) != null) 

                s1 = s1+s2;

        }

        catch(Exception exception)

        {

            s1 = "0";

        }

        return s1;

    }
    
    public static String[] push(String[] input, String newS)
    {
    	String[] temp=null;
    	
    	temp=input;
    	input=new String[input.length+1];
    	
    	for(int i=0;i<temp.length;i++)
    	{
    		input[i]=temp[i];
    	}
    	input[temp.length]=newS;
    	return input;
    }
    

    
    public static String[] DownloadURL(String url, String spWebParameters, String Method)
    {
    
	String[] ret=new String[0];
    	try
    	{    	
		URL mURL=new URL(url);
		HttpURLConnection conn=(HttpURLConnection)mURL.openConnection();		
		conn.setDoOutput(true);
		conn.setUseCaches (false);
		conn.setRequestMethod(Method);
		
		if(spWebParameters.length()>0)
		{		
			OutputStream os = conn.getOutputStream();		
			DataOutputStream ow = new DataOutputStream(os);		
				
			ow.writeBytes(spWebParameters);
			ow.close();		
		}
		
				
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
	
	
		while ((line = in.readLine()) != null)
		{
			ret=push(ret,line);
		}
		
		in.close();
		conn.disconnect();		
	}catch(Exception e){}
    	return ret;
    }

}