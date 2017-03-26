

import java.sql.ResultSet;
import java.util.GregorianCalendar;

public class UserKarten extends DBQuery
{

    UserKarten()
    {
    }

    public String GetRestzeit(String iduser)
    {
        ResultSet rs = DoQuery("SELECT * FROM UserKarten WHERE IDUser='"+iduser+"'");
        String ret = "";
        try
        {
            rs.first();
            do
                if(rs.getString(4).compareTo("0") != 0)
                    ret = rs.getString(4);
            while(rs.next() && ret == "");
        }
        catch(Exception exception) { }
        return ret;
    }

    public String GetCode(String iduser)
    {
        ResultSet rs = DoQuery("SELECT * FROM UserKarten WHERE IDUser='"+iduser+"'");
        String ret = "";
        try
        {
            rs.first();
            do
                if(rs.getString(4).compareTo("0") != 0)
                    ret = rs.getString(7);
            while(rs.next() && ret == "");
        }
        catch(Exception exception) { }
        return ret;
    }

    public void InsertNew(String iduser, String idkarte, String Code)
    {
        String gueltigbis = GetGueltigBis(idkarte);
        String restzeit = GetMenge(idkarte);
        DoQuery("INSERT INTO UserKarten(IDUser, IDKarte, Restzeit, GueltigBis, Code)VALUES ('"+iduser+"','"+idkarte+"','"+restzeit+"','"+gueltigbis+"','"+Code+"')");
    }

    public boolean CheckCard(String iduser)
    {
        boolean found = false;
        ResultSet rs = DoQuery("SELECT * FROM UserKarten WHERE IDUser='"+iduser+"'");
        try
        {
            if(rs.first())
                do
                {
                    Karten mKarten = new Karten(rs.getString(3));
                    if(mKarten.IsFlatrate())
                    {
                        GregorianCalendar cal = new GregorianCalendar();
                        long now = cal.getTimeInMillis();
                        long gueltigbis = rs.getLong(5);
                        if(gueltigbis - now > 0L)
                            found = true;
                    } else
                    if(rs.getInt(4) > 0)
                        found = true;
                } while(rs.next());
        }
        catch(Exception exception) { }
        return found;
    }

    public String GetIDKarte(String iduser)
    {
        ResultSet rs = DoQuery("SELECT * FROM UserKarten WHERE IDUser='"+iduser+"'");
        String ret = "";
        try
        {
            rs.first();
            do
                if(rs.getString(4).compareTo("0") != 0)
                    ret = rs.getString(3);
            while(rs.next() && ret == "");
        }
        catch(Exception exception) { }
        return ret;
    }

    private String GetGueltigBis(String idkarten)
    {
        Karten mKarten = new Karten(idkarten);
        String ret;
        if(mKarten.IsFlatrate())
        {
            String menge = mKarten.GetMenge();
            GregorianCalendar cal = new GregorianCalendar();
            cal.set(6, cal.get(6) + Integer.parseInt(menge));
            long timestamp = cal.getTimeInMillis();
            ret = Long.toString(timestamp);
        } else
        {
            ret = "0";
        }
        return ret;
    }

    public String GetMenge(String idkarten)
    {
        Karten mKarten = new Karten(idkarten);
        String ret;
        if(!mKarten.IsFlatrate())
            ret = mKarten.GetMenge();
        else
            ret = "0";
        return ret;
    }
}
