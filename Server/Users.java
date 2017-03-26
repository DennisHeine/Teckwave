

import java.io.PrintStream;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.util.*;

public class Users extends DBQuery
{
    private String username;
    Users(String musername)
    {
        username = musername;
    }

    public void InsertNew(String username, String passwort, String registriert)
    {
        DoQuery("INSERT INTO Users (Username, Password, Registriert) VALUES('"+username+"','"+passwort+"','"+registriert+"')");
    }
    public String GetByID(String IDUser)
    {
    	ResultSet rs=DoQuery("SELECT * FROM User WHERE IDUser='"+IDUser+"'");
    	String Username="";
    	try
    	{
    		if(rs.first())
    		{
    			Username= rs.getString("Username");
	    	}
    	}
    	catch(Exception e){}
    	return Username;
    }

    public String GetUserID()
    {
        ResultSet rs = DoQuery("SELECT * FROM Users WHERE Username='"+username+"'");
        try
        {
            rs.first();
            return rs.getString(1);
        }
        catch(Exception e)
        {
            return "0";
        }
    }

    public String GetPattern()
    {
	String pattern;
	ResultSet rs = DoQuery("SELECT * FROM Users WHERE Username='"+username+"'");
        try
        {
            rs.first();
	    if(rs.getString("Pattern").length()<3)
	    {
		return "0";
	    }
	    else
	    {
	    	String ret=rs.getString("Pattern");
		System.out.println(ret);
	    	DoQuery("UPDATE Users SET Pattern='0' WHERE Username='"+username+"'");
	    	return ret;
	    }
        }
        catch(Exception e)
        {
            return "0";
        }	
    }

    private String GenPattern()
    {
	String mPattern=new String("");
	Random rnd=new Random();
	byte[] zahl=new byte[1024];
	byte[] zahl1=new byte[1];
	rnd.nextBytes(zahl);
	for(int i=0;i<=1023;i++)
	{	
		while(zahl[i]<65||zahl[i]>90)
		{
			rnd.nextBytes(zahl1);
			zahl[i]=zahl1[0];
		}
		mPattern=mPattern+Character.toString((char)zahl[i]);
	}
	return mPattern;
     }


    public boolean CheckUser(String passwort)
    {
        ResultSet rs;
        StringBuffer hexString;
        rs = null;
        hexString = new StringBuffer();
        try
        {/*
            MessageDigest mdAlgorithm = MessageDigest.getInstance("MD5");
            mdAlgorithm.update(passwort.getBytes());
            byte digest[] = mdAlgorithm.digest();
            for(int i = 0; i < digest.length; i++)
            {
                String plainText = Integer.toHexString(0xff & digest[i]);
                if(plainText.length() < 2)
                    plainText = "0"+plainText;
                hexString.append(plainText);
            }

            System.out.println(hexString.toString());*/
            rs = DoQuery("SELECT * FROM Users WHERE Username='"+username+"' AND Password='"+passwort+"'");
        }
        catch(Exception exception) {exception.printStackTrace(); }	boolean ret=false;
	try{
        	rs.first();		if(rs.getString("Password").equals(passwort) && rs.getString("Username").equals(username))
		{
			ret=true;
			System.out.println("AuthOK");
		}
		else
		{
			System.out.println("AuthFail");
		
			ret=false;
		}
        return ret;
        }
	catch(Exception e)
	{			e.printStackTrace();
        	return false;
	}
    }

}
