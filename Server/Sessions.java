import java.sql.ResultSet;
import java.util.GregorianCalendar;

public class Sessions extends DBQuery {
 private String username;
 Sessions(String musername) {
  username = musername;
 }

 Sessions() {}

 public String GetProvider() {
  try {

   ResultSet sessions = DoQuery("SELECT * FROM Sessions WHERE Username='" + username + "'");
   sessions.first();
   return sessions.getString("Provider");

  } catch (Exception e) {
   return "";
  }

 }

 public String GetPattern() {
  try {
   String ret = null;
   ResultSet sessions = DoQuery("SELECT * FROM Sessions WHERE Username='" + username + "'");
   return "";
  } catch (Exception e) {
   return "";
  }
 }

 public String GetIP() {
  ResultSet sessions = DoQuery("SELECT * FROM Sessions WHERE Username='" + username + "'");
  try {
   sessions.first();
   return sessions.getString("IP");
  } catch (Exception e) {
   return "0";
  }
 }

 public String GetIPProvider() {
  ResultSet sessions = DoQuery("SELECT * FROM Sessions WHERE Username='" + username + "'");
  try {
   sessions.first();
   return sessions.getString("RouterIP");
  } catch (Exception e) {
   return "0";
  }
 }


 public boolean CheckSession() {
  ResultSet sessions = DoQuery("SELECT * FROM Sessions WHERE Username='" + username + "'");
  boolean ret;
  try {
   ret = sessions.first();
  } catch (Exception e) {
   ret = false;
  }
  return ret;
 }

 public void InsertSession(String username, String ip, String routerIP, String Provider, String Pattern) {
  GregorianCalendar cal = new GregorianCalendar();
  long ms = cal.getTimeInMillis();
  DoQuery("INSERT INTO Sessions (Username, IP, Active, RouterIP,Zeit, Provider, Pattern) VALUES('" + username + "','" + ip + "','A','" + routerIP + "','" + Long.toString(ms) + "','" + Provider + "','" + Pattern + "')");
 }

 public void DeleteSession() {
  DoQuery("DELETE FROM Sessions WHERE Username='" + username + "'");
 }

 public String[][] CheckInactive() {
  String[][] Zwischen = null;
  String[][] Ret = new String[1][3];

  int count = 0;

  ResultSet sessions = DoQuery("SELECT * FROM Sessions WHERE Active='D'");
  try {
   if (sessions.first()) {

    do {
     Zwischen = new String[count + 1][3];
     if (count > 0) {
      for (int i = 0; i <= count - 1; i++) {
       Zwischen[i][0] = Ret[i][0];
       Zwischen[i][1] = Ret[i][1];
       Zwischen[i][2] = Ret[i][2];
      }
      Ret = new String[count + 1][3];
      for (int i = 0; i <= count - 1; i++) {
       Ret[i][0] = Zwischen[i][0];
       Ret[i][1] = Zwischen[i][1];
       Ret[i][2] = Zwischen[i][2];
      }
      Ret[count][0] = sessions.getString("IP");
      Ret[count][1] = sessions.getString("RouterIP");
      Ret[count][1] = sessions.getString("Provider");
     } else {
      Ret[0][0] = sessions.getString("IP");
      Ret[0][1] = sessions.getString("RouterIP");
      Ret[0][1] = sessions.getString("Provider");
     }
     DoQuery("INSERT INTO Log (IDUser,Provider,Login,Logout,IPUser, IPProvider, IDKarte) VALUES (" + sessions.getString("Username") + "'" + ",'" + sessions.getString("Provider") + "'" + ",'" + sessions.getString("Zeit") + "'" + ",'" + sessions.getString("Endzeit") + "'" + ",'" + sessions.getString("IP") + "'" + ",'" + sessions.getString("RouterIP") + "'" + ",'" + sessions.getString("IDKarte") + "'" + ")");
     //DoQuery("DELETE FROM Sessions WHERE Username='"+sessions.getString("Username")+"'");
     count++;
    } while (sessions.next());
   }

  } catch (Exception e) {}
  DoQuery("UPDATE Sessions SET Active='D' WHERE Active='A'");
  return Ret;
 }


}