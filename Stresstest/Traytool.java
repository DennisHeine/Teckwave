// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: fullnames fieldsfirst 
// Source File Name:   Traytool.java

import com.teckwave.Status.Login;

public class Traytool
{

    public Traytool()
    {
    }

    public static void main(java.lang.String args[])
    {
        		com.teckwave.Protokoll.Protokoll p=new com.teckwave.Protokoll.Protokoll("dehe","deskjet");				while(true)		{			try			{				System.out.println(p.Anmelden());				Thread.sleep(100);				/*				for(int i=1;i<=600;i++)				{					Object aobj[]=null;					try{					System.out.println(p.getStatus(aobj));					}catch(Exception e){}					Thread.sleep(100);				}*/			}catch(Exception e){}		}				
        
    }
}
