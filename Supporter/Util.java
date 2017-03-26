
/*
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.net.*;

import java.sql.ResultSet;
import java.util.GregorianCalendar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.net.InetAddress;
import java.net.URL;
import java.sql.ResultSet;
import java.util.GregorianCalendar;
import javax.swing.*;
*/
import java.io.*;
import java.net.URL;

class Util
{

	Util()
	{
	}

	public static String GetMyPattern()
	{
		String Ptrn=new String("");
	
		try
		{
			FileReader fr=new FileReader("user.key");
			int chr;
			while((chr=fr.read())!=-1)
				Ptrn=new StringBuilder(Ptrn).append(Character.toString((char)chr)).toString();

		}
		catch(Exception e)
		{}

		return Ptrn;
	}

	private static int[] StringToIntArr(String Strng)
	{
		int Ret[]=new int[Strng.length()];
		for(int i=0;i<=Strng.length()-1;i++)
		{
			Ret[i]=Strng.charAt(i);
		}
		return Ret;
	}
	
	

	public static byte[] XorPattern(byte[] str1,byte[] str2)
	{
		/*
		int[] ToCypherInt=new int[str1.length()];
		ToCypherInt=StringToIntArr(str1);
		int[] PatternInt=new int[str2.length()];
		PatternInt=StringToIntArr(str2);

				
		

		int to=str1.length()<str2.length()?str1.length():str2.length();
		int DecypheredInt[]=new int[to];

		for(int i=0;i<=to-1;i++)
			DecypheredInt[i]=ToCypherInt[i]^PatternInt[i];
					

		String Decyphered=new String("");
			
		for(int i=0;i<=to-1;i++)
			Decyphered=new StringBuilder(Decyphered).append(Character.toString((char)DecypheredInt[i])).toString();

		return Decyphered;
		*/
		
		int to=str1.length<str2.length?str1.length:str2.length;
		
		byte[] ret=new byte[to];
		for(int i=0;i<to;i++)
			ret[i]=(byte)(((int)str1[i]^(int)str2[i])&0xFF);
		
		return ret;
		
	}

	public static byte[] Crypt(String UserDaten,byte[] MyPattern)
	{
		
		byte[] CryptedText=XorPattern(StrToByteArr(UserDaten),MyPattern);						
		return CryptedText;
	}
	
	/*public byte[] StrToByteArr(String str)
	{
		byte[] ret=new byte[str.length()*2];
		int high=0xFF00;
		int low=0xFF;
		
		int z=0;
		for(int i=0;i<=str.length()-1;i++)
		{
			ret[z]=(byte)(str.charAt(i)&high);
			ret[z+1]=(byte)(str.charAt(i)&low);			
			z+=2;
			System.out.println(Integer.toString((int)ret[z]));
		}
		return ret;
	}*/
	
	public static byte[] StrToByteArr(String str)
	{
		byte[] ret=new byte[str.length()];
		int high=0xFF00;
		int low=0xFF;
		
		int z=0;
		for(int i=0;i<=str.length()-1;i++)
		{
			ret[i]=(byte)str.charAt(i);
		}
		return ret;
	}	
	
	public static int[] ByteToInt(byte[] Input)
	{
		int[] ret=new int[Input.length/2];
		int z=0;
		for(int i=0;i<Input.length-1;i+=2)
		{
			ret[z]=Input[i];
			ret[z]<<=8;
			ret[z]&=Input[i+1];
			z++;
		}
		return ret;
	}
	
	public static String IntArrToString(int[] In)
	{
		String ret="";
		for(int i=0;i<In.length;i++)
		{
			ret=new StringBuilder(ret).append(Character.toString((char)In[i])).toString();
		}
		return ret;
	}

	public static java.lang.String DownloadURL(java.lang.String url)
	{
		java.lang.String ret = "0";
		try
		{
			java.io.InputStream is = null;
			java.net.URL u = new URL(url);
			is = u.openStream();
			java.io.DataInputStream dis = new DataInputStream(new BufferedInputStream(is));
			java.lang.String s;
			while((s = dis.readLine()) != null) 
				ret = (new StringBuilder(java.lang.String.valueOf(ret))).append(s).toString();
		}
		catch(java.lang.Exception e)
		{
			ret = "0";
		}
		return ret;
	}



}