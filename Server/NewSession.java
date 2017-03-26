


public class NewSession extends DBQuery
{

    NewSession(String musername)
    {
        username = musername;
    }

    public void Delete()
    {
        DoQuery("DELETE FROM NewSession WHERE Username='"+username+"'");
    }

    public void InsertNew(String ip, String routerip, String provider)
    {
        DoQuery("INSERT INTO NewSession (Username, IP, RouterIP,Provider) VALUES('"+username+"','"+ip+"','"+routerip+"','"+routerip+"','"+provider+"')");
    }

    String username;
}
