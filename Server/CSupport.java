import java.util.*;

import java.sql.*;

class CSupport {

 CSupport() {}

 public static void GenSession(String IDSupporter, String IDUser) {
  DBQuery qry = new DBQuery();
  GregorianCalendar cal = new GregorianCalendar();
  long timestamp = cal.getTimeInMillis();
  qry.DoQuery("INSERT INTO SupportSessions (TimestampStart,IDSupporter,IDUser,ActiveJN) VALUES('" + Long.toString(timestamp) + "','" + IDSupporter + "','" + IDUser + "','J')");
 }

 public static void SetSolved(String ID) {
  DBQuery qry = new DBQuery();
  qry.DoQuery("UPDATE SupportSessions SET SolvedJN='J' WHERE ID='" + ID + "'");
 }

 public static void SetRueckfrage(String ID, boolean Setzen) {
  DBQuery qry = new DBQuery();
  qry.DoQuery("UPDATE SupportSessions SET RueckfrageJN='" + (Setzen ? "J" : "N") + "' WHERE ID='" + ID + "'");
 }

 public static void SetKommentar(String ID, String Kommentar) {
  DBQuery qry = new DBQuery();
  qry.DoQuery("UPDATE SupportSessions SET Kommentar='" + Kommentar + "' WHERE ID='" + ID + "'");
 }

 public static void CloseSession(String ID) {
  DBQuery qry = new DBQuery();
  GregorianCalendar cal = new GregorianCalendar();
  long timestamp = cal.getTimeInMillis();

  qry.DoQuery("UPDATE SupportSessions SET TimestampStop='" + Long.toString(timestamp) + "' WHERE ID='" + ID + "'");
  qry.DoQuery("UPDATE SupportSessions SET Active='N' WHERE ID='" + ID + "'");

 }

 public static void InsertText(String IDUser, boolean Supporter, String Text) {
  DBQuery qry = new DBQuery();
  GregorianCalendar cal = new GregorianCalendar();
  long timestamp = cal.getTimeInMillis();
  System.out.println("InsertText vor Abfrage");

  ResultSet rs = qry.DoQuery("SELECT * FROM SupportSessions WHERE IDUser='" + IDUser + "' AND ActiveJN='J'");
  try {
   rs.first();
   String IDSupportSession = rs.getString("ID");
   System.out.println("IDSupportSession: " + IDSupportSession);
   qry.DoQuery("INSERT INTO SupportChat (IDSupportSession,SupporterClient,Text,Timestamp) VALUES ('" + IDSupportSession + "','" + (Supporter ? "S" : "C") + "','" + Text + "','" + Long.toString(timestamp) + "')");
  } catch (Exception e) {
   System.out.println(e.toString());
  }

 }

 public static boolean IsSupportAvailable() {
  DBQuery qry = new DBQuery();
  ResultSet rs = qry.DoQuery("SELECT * FROM Supporter WHERE OnlineJN='J'");
  boolean ret = false;

  try {
   ret = rs.first();
  } catch (Exception e) {};
  return ret;
 }


 public static boolean IsSupporterFree() {
  DBQuery qry = new DBQuery();
  ResultSet rs = qry.DoQuery("SELECT * FROM Supporter WHERE BusyJN='N'");
  boolean ret = false;

  try {
   ret = rs.first();
  } catch (Exception e) {
   System.out.println(e.toString());
  }
  return ret;

 }

 public static void InsertIntoWarteschlange(String IDUser) {
  DBQuery qry = new DBQuery();
  qry.DoQuery("INSERT INTO Warteschlange (IDUser) VALUES ('" + IDUser + "')");
 }

 public static int[] GetWarteschlangenPos(String IDUser) {
  DBQuery qry = new DBQuery();
  ResultSet rs = qry.DoQuery("SELECT * FROM Warteschlange");
  int[] ret = new int[2];
  ret[0] = 0;
  ret[1] = 0;

  try {
   rs.first();
   int pos = 1;
   int currpos = 0;
   do {
    String ID = rs.getString("ID");
    if (ID.equals(IDUser)) {
     currpos = pos + 1;
    } else {
     pos++;
    }
   } while (rs.next());
   ret[0] = currpos;
   ret[1] = pos;
  } catch (Exception e) {}
  return ret;
 }

 public static int GetWarteschlangenCount() {
  DBQuery qry = new DBQuery();
  ResultSet rs = qry.DoQuery("SELECT * FROM Warteschlange");
  int pos = 0;
  try {
   if (rs.first()) {
    do {
     pos++;
    } while (rs.next());
    return pos;
   }
  } catch (Exception e) {}
  return pos;
 }

 public static String WSToSP(String IDUser) {
  DBQuery qry = new DBQuery();
  String ret = "";
  ret = "NSA";
  try {
   if (IsSupporterFree()) {
    qry.DoQuery("DELETE FROM Warteschlange WHERE IDUser='" + IDUser + "'");
    ResultSet rs = qry.DoQuery("SELECT * FROM Supporter WHERE OnlineJN='J' AND BusyJN='N'");
    rs.first();
    String IDSupporter = rs.getString("ID");
    qry.DoQuery("UPDATE Supporter SET BusyJN='J' WHERE ID='" + IDSupporter + "'");
    GenSession(IDSupporter, IDUser);
    String IDSession = SessionGetIDSession(IDUser);

    ret = "SC;" + IDSession + ";";
   } else {
    if (IsSupportAvailable())
     ret = "NSF";
   }
  } catch (Exception e) {}
  return ret;
 }

 public static String SessionGetIDUserBySupporter(String IDSupporter) {
  DBQuery qry = new DBQuery();
  String IDUser = "";
  try {
   ResultSet rs = qry.DoQuery("SELECT * FROM SupportSessions WHERE IDSupporter='" + IDSupporter + "' AND ActiveJN='J'");
   rs.first();
   IDUser = rs.getString("IDUser");
  } catch (Exception e) {}

  return IDUser;
 }


 public static String SessionGetIDUserBySession(String IDSession) {
  DBQuery qry = new DBQuery();
  ResultSet rs = qry.DoQuery("SELECT * FROM SupportSessions WHERE ID='" + IDSession + "' AND ActiveJN='J'");
  String IDUser = "";
  try {
   rs.first();
   IDUser = rs.getString("IDUser");
  } catch (Exception e) {}

  return IDUser;
 }

 public static String SessionGetIDSession(String IDUser) {
  DBQuery qry = new DBQuery();
  String IDSupporter = new String("");
  ResultSet rs = qry.DoQuery("SELECT * FROM SupportSessions WHERE IDUser='" + IDUser + "' AND ActiveJN='J'");
  try {
   rs.first();
   IDSupporter = rs.getString("ID");
  } catch (Exception e) {}
  return IDSupporter;
 }

 public static String SessionGetIDSessionBySupporter(String IDSupporter) {
  DBQuery qry = new DBQuery();
  String IDSession = "";
  ResultSet rs = qry.DoQuery("SELECT * FROM SupportSessions WHERE IDSupporter='" + IDSupporter + "' AND ActiveJN='J'");
  try {
   rs.first();
   IDSession = rs.getString("ID");
  } catch (Exception e) {}
  return IDSession;
 }



 public static void SetSupporterBusy(String IDSupporter, boolean Free) {
  DBQuery qry = new DBQuery();
  qry.DoQuery("UPDATE Supporter SET BusyJN='" + (Free ? "N" : "J") + "' WHERE ID='" + IDSupporter + "'");
 }

 public static void SetSupporterOnline(String IDSupporter, boolean Online) {
  DBQuery qry = new DBQuery();
  qry.DoQuery("UPDATE Supporter SET OnlineJN='" + (Online ? "J" : "N") + "' WHERE ID='" + IDSupporter + "'");
 }

 public static String GetFreeSupporter() {
  DBQuery qry = new DBQuery();
  ResultSet rs = qry.DoQuery("SELECT * FROM Supporter WHERE OnlineJN='J' AND BusyJN='N'");
  String IDSupporter = "";
  try {
   rs.first();
   IDSupporter = rs.getString("ID");
   qry.DoQuery("UPDATE Supporter SET BusyJN='J' WHERE ID='" + IDSupporter + "'");
  } catch (Exception e) {}
  return IDSupporter;
 }

 public static void CancelWait(String IDUser) {
  DBQuery qry = new DBQuery();
  qry.DoQuery("DELETE FROM Warteschlange WHERE IDUser='" + IDUser + "'");
 }

 public static String GetSupporterName(String IDSession) {
  DBQuery qry = new DBQuery();
  String Name = "";
  try {
   ResultSet rs = qry.DoQuery("SELECT * FROM SupportSessions WHERE ID='" + IDSession + "'");
   rs.first();
   String IDSupporter = rs.getString("IDSupporter");
   rs = qry.DoQuery("SELECT * FROM Supporter WHERE ID='" + IDSupporter + "'");
   rs.first();
   Name = rs.getString("Name");
  } catch (Exception e) {
   System.out.println(e.toString());
  }
  return Name;
 }

 public static String GetUserName(String IDSession) {
  String IDUser = SessionGetIDUserBySession(IDSession);
  String Username = "";
  DBQuery qry = new DBQuery();
  try {
   ResultSet rs = qry.DoQuery("SELECT * FROM Users WHERE ID='" + IDUser + "'");
   rs.first();
   Username = rs.getString("Username");
  } catch (Exception e) {}
  return Username;
 }

 public static boolean IsSupporterInConversation(String IDSupporter) {
  DBQuery qry = new DBQuery();
  boolean ret = false;

  try {
   ResultSet rs = qry.DoQuery("SELECT * FROM SupportSessions WHERE Active='J' AND IDSupporter='" + IDSupporter + "'");
   ret = rs.first();
  } catch (Exception e) {}
  return ret;
 }

 public static String[] GetConversation(String IDSession) {
  String[] ConvTmp = new String[1];
  String[] Conv = new String[1];
  Conv = new String[1];
  Conv[0] = "";
  String Line = "";
  int length = 0;
  int IDConversation = 0;

  String Username = GetUserName(IDSession);
  String Supportername = GetSupporterName(IDSession);

  DBQuery qry = new DBQuery();
  System.out.println(IDSession);
  try {
   ResultSet rs = qry.DoQuery("SELECT * FROM SupportChat WHERE IDSupportSession='" + IDSession + "'");

   if (rs.first()) {
    Conv[0] = "Willkommen beim Teckwave Support. Sie sprechen mit " + Supportername;
    do {
     System.out.println(rs.getString("Text"));
     length++;
     ConvTmp = new String[length];
     for (int i = 0; i < length; i++)
      ConvTmp[i] = Conv[i];

     Conv = new String[length + 1];
     for (int i = 0; i < length; i++)
      Conv[i] = ConvTmp[i];

     Conv[length] = (rs.getString("SupporterClient").equals("S") ? Supportername : Username) + ": " + rs.getString("Text");
    } while (rs.next());

   } else {
    length = 1;
    Conv[0] = ";NS;";
   }
  } catch (Exception e) {
   System.out.println(e.toString());
  }
  return Conv;
 }

 public static void EnterBug(String IDSupporter, String IDSession, String Ueberschrift, String Bereich, String Anwendung, String Prioritaet, String Beschreibung) {
  DBQuery qry = new DBQuery();
  qry.DoQuery("INSERT INTO Bugs(IDSupporter,IDSession,Ueberschrift,Bereich,Anwendung,Prioritaet,Beschreibung) VALUE('" + IDSupporter + "','" + IDSession + "','" + Ueberschrift + "','" + Bereich + "','" + Anwendung + "','" + Prioritaet + "','" + Beschreibung + "')");
 }

 public static String GetRueckfrage(String IDSupporter) {
  String Timestamp = "";
  String IDUser = "";
  String Solved = "";
  String Kommentar = "";
  String Username;
  DBQuery qry = new DBQuery();
  String ret = "NR";

  try {
   ResultSet rs = qry.DoQuery("SELECT * FROM SupportSessions WHERE Rueckfrage='J' AND IDSupporter='" + IDSupporter + "'");

   if (rs.first()) {
    ret = "RF;";
    do {
     Timestamp = rs.getString("Timestamp");
     IDUser = rs.getString("IDUser");
     Solved = rs.getString("SolvedJN");
     Kommentar = rs.getString("Kommentar");
     Users mUsers = new Users("");
     Username = mUsers.GetByID(IDUser);
     ret = ret + Timestamp + ";" + Username + ";" + Kommentar + ";" + Solved + ";";
    } while (rs.next());
   }
  } catch (Exception e) {}
  return ret;
 }

 public static String GetIDSupporter(String Username) {
  DBQuery qry = new DBQuery();
  String ret = "0";
  try {
   ResultSet rs = qry.DoQuery("SELECT * FROM Supporter WHERE Username='" + Username + "'");
   if (rs.first())
    return rs.getString("ID");
   else
    return "0";
  } catch (Exception e) {}
  return ret;
 }

}